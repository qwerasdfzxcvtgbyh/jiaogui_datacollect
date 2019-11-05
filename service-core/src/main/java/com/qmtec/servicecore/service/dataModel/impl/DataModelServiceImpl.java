package com.qmtec.servicecore.service.dataModel.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.dao.DataModelMapper;
import com.qmtec.servicecore.dao.DataModelRelationColumnMapper;
import com.qmtec.servicecore.entity.ColumnInfo;
import com.qmtec.servicecore.entity.DataModel;
import com.qmtec.servicecore.entity.DataModelRelationColumn;
import com.qmtec.servicecore.entity.example.DataModelExample;
import com.qmtec.servicecore.entity.example.DataModelRelationColumnExample;
import com.qmtec.servicecore.model.dto.ColumInfoDto;
import com.qmtec.servicecore.model.dto.DataModelDto;
import com.qmtec.servicecore.service.colum.ColumService;
import com.qmtec.servicecore.service.dataModel.DataModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class DataModelServiceImpl implements DataModelService {

    @Autowired
    private DataModelMapper dataModelMapper;
    @Autowired
    private ColumService columService;
    @Autowired
    private DataModelRelationColumnMapper dataModelRelationColumnMapper;

    /**
     * 添加dataModel的Dao操作
     */
    private int addDataModelCore(DataModelDto dataModelDto, String id) {
        return dataModelMapper.insertSelective(DataModel
                        .builder()
                        .id(id)
                        .name(dataModelDto.getName())
                        .code(dataModelDto.getCode())
                        .modifyTime(new Date())
                        .remark(dataModelDto.getRemark())
                        .createTime(new Date())
                        .build()
                , DataModel.Column.id
                , DataModel.Column.name
                , DataModel.Column.code
                , DataModel.Column.modifyTime
                , DataModel.Column.remark
                , DataModel.Column.createTime

        );
    }

    /**
     * 修改dataModel的Dao操作
     */
    private int updateDataModelCore(DataModelDto dataModelDto) {
        return dataModelMapper.updateByExampleSelective(DataModel
                        .builder()
                        .name(dataModelDto.getName())
                        .code(dataModelDto.getCode())
                        .modifyTime(new Date())
                        .remark(dataModelDto.getRemark())
                        .build(),
                DataModelExample.newAndCreateCriteria()
                        .andIdEqualTo(dataModelDto.getId()).example()
                , DataModel.Column.name
                , DataModel.Column.code
                , DataModel.Column.modifyTime
                , DataModel.Column.remark
        );
    }

    @Override
    public Boolean addOrUpdateDataMode(String data) {
        Boolean flag = false;
        String id = null;
        try {
            DataModelDto dataModelDto = JSONObject.parseObject(data, DataModelDto.class);
            if (StringUtils.isEmpty(dataModelDto.getId())) {
                DataModel dataModel = this.getDataModelByName(dataModelDto.getName());
                if (dataModel != null) {
                    throw new CustomException("name : " + dataModelDto.getName() + "Existing");
                }
                //进行检查
                List<ColumInfoDto> columInfoDtoList = dataModelDto.getColumInfoDtoList();
                this.checkFiled(columInfoDtoList);

                id = UUID.randomUUID().toString().trim().replaceAll("-", "");
                if (this.addDataModelCore(dataModelDto, id) > 0) {
                    //保存列
                    this.addDataModelRelationColumn(id, columInfoDtoList);
                    flag = true;
                }
            } else {//修改
                DataModel dataModel = this.getDataModelByName(dataModelDto.getName());
                if (dataModel != null && !dataModel.getId().equals(dataModelDto.getId())) {
                    throw new CustomException("name : " + dataModelDto.getName() + "Used");
                }
                //进行检查
                List<ColumInfoDto> columInfoDtoList = dataModelDto.getColumInfoDtoList();
                this.checkFiled(columInfoDtoList);

                id = dataModel.getId();
                if (this.updateDataModelCore(dataModelDto) > 0) {
                    //清空关系
                    dataModelRelationColumnMapper.deleteByExample(DataModelRelationColumnExample
                            .newAndCreateCriteria()
                            .andDatamodelIdEqualTo(id)
                            .example());
                    //保存列
                    this.addDataModelRelationColumn(id, columInfoDtoList);
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        return flag;
    }

    private void checkFiled(List<ColumInfoDto> columInfoDtoList){
        columInfoDtoList.forEach(columInfoDto -> {
            ColumnInfo columnInfo = columService.getColumInfoByName(columInfoDto.getName());
            if (columnInfo != null) {
                if(!columInfoDto.getType().equals(columnInfo.getType())){
                    throw new CustomException("字段："+columInfoDto.getName()+"在列池中存在且类型不相等，请修改后在提交！！！"
                            , HttpCode.CODE_500);
                }
            }
        });
    }


    private void addDataModelRelationColumn(String dataModelId, List<ColumInfoDto> columInfoDtoList) {
        columInfoDtoList.forEach(columInfoDto -> {
            ColumnInfo columnInfo = columService.getColumInfoByName(columInfoDto.getName());
            if (columnInfo != null) {
                if(columInfoDto.getType().equals(columnInfo.getType())){
                    dataModelRelationColumnMapper.insertSelective(DataModelRelationColumn.builder()
                                    .columnId(columnInfo.getId())
                                    .datamodelId(dataModelId)
                                    .build()
                            , DataModelRelationColumn.Column.columnId
                            , DataModelRelationColumn.Column.datamodelId
                    );
                }else{
                    throw new CustomException("字段："+columInfoDto.getName()+"在列池中存在且类型不相等，请修改后在提交！！！"
                            , HttpCode.CODE_500);
                }
            } else {
                Map<String, Object> map = columService.addOrUpdateColumInfo(JSON.toJSONString(columInfoDto));
                if ((Boolean) map.get("flag").equals(true)) {
                    dataModelRelationColumnMapper.insertSelective(DataModelRelationColumn.builder()
                                    .columnId((String) map.get("id"))
                                    .datamodelId(dataModelId)
                                    .build()
                            , DataModelRelationColumn.Column.columnId
                            , DataModelRelationColumn.Column.datamodelId
                    );
                } else {
                    throw new CustomException("add DataModelRelationCloumn fail ", HttpCode.CODE_500);
                }
            }
        });
    }


    private DataModel getDataModelByName(String name) {
        DataModel DataModel = null;

        Optional<DataModel> dataModelOptional = null;
        try {
            dataModelOptional = Optional.ofNullable(dataModelMapper.selectOneByExample(
                    DataModelExample.newAndCreateCriteria()
                            .andNameEqualTo(name)
                            .example()
            ));
            if (dataModelOptional.isPresent()) {
                DataModel = dataModelOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return DataModel;
    }


    @Override
    public ListResult<DataModelDto> listSelectDataMode(String searchParams, int page, int limit) {
        ListResult<DataModelDto> page1 = new ListResult<DataModelDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

        Optional<List<DataModel>> optionalDataModelList = null;

        DataModelExample.Criteria criteria = DataModelExample.newAndCreateCriteria();
        if (paramMap != null) {
            String name = (String) paramMap.get("name");
            if (!StringUtils.isEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }
        }

        DataModelExample dataModelExample = criteria.example();
        dataModelExample.page((page - 1), limit);
        dataModelExample.setOrderByClause("create_time desc");

        optionalDataModelList = Optional.ofNullable(dataModelMapper.selectByExample(dataModelExample));
        if (optionalDataModelList.isPresent()) {

            List<DataModel> dataModelList = optionalDataModelList.get();
            if (dataModelList.size() > 0) {
                long count = dataModelMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<DataModelDto> dataModelDtoArrayList = new ArrayList<DataModelDto>();

                dataModelList.forEach(dataModel -> {
                    DataModelDto dataModelDto = DataModelDto.builder()
                            .id(dataModel.getId())
                            .name(dataModel.getName())
                            .code(dataModel.getCode())
                            .createTime(dataModel.getCreateTime())
                            .build();

                    dataModelDtoArrayList.add(dataModelDto);
                });
                page1.setResult(dataModelDtoArrayList);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    @Override
    public DataModelDto updateDataModeBefore(String id) {
        DataModelDto dataModelDto = null;
        Optional<DataModel> dataModelOptional = null;
        try {
            dataModelOptional = Optional.ofNullable(
                    dataModelMapper.selectOneByExample(
                            DataModelExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (dataModelOptional.isPresent()) {
                DataModel dataModel = dataModelOptional.get();
                dataModelDto = DataModelDto.builder()
                        .id(dataModel.getId())
                        .name(dataModel.getName())
                        .code(dataModel.getCode())
                        .remark(dataModel.getRemark())
                        .createTime(new Date())
                        .build();

                //获取列信息
                List<String> list = new ArrayList<>();
                dataModelRelationColumnMapper.selectByExample(
                        DataModelRelationColumnExample.newAndCreateCriteria()
                                .andDatamodelIdEqualTo(dataModel.getId())
                                .example()).forEach(dataModelRelationColumn -> {
                    list.add(dataModelRelationColumn.getColumnId());
                });
                if(list.size()>0){
                    List<ColumInfoDto> columInfoDtoList  = columService.getColumnInfoByIds(list);
                    dataModelDto.setColumInfoDtoList(columInfoDtoList);
                }
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return dataModelDto;
    }

    @Override
    public Boolean deleteDataMode(String data) {
        Boolean falg = false;
        try {
            List<String> ids = (List<String>) JSONArray.parseObject(data, List.class);
            if (ids.size() > 0) {
                Optional<List<DataModel>> dataModels = Optional.ofNullable(
                        dataModelMapper.selectByExample(
                                DataModelExample.newAndCreateCriteria().andIdIn(ids).example()
                        )
                );

                if (dataModels.isPresent()) {
                    if (dataModelMapper.deleteByExample(
                            DataModelExample.newAndCreateCriteria().andIdIn(ids).example()) > 0) {
                        falg = true;

                        //清除关系
                    }
                } else {
                    throw new CustomException("No data queried ", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            log.debug("delete fail : [{}] ", e.getMessage());
            throw new CustomException("delete fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    @Override
    public DataModelDto getDataModeById(String id) {
        return this.updateDataModeBefore(id);
    }


}
