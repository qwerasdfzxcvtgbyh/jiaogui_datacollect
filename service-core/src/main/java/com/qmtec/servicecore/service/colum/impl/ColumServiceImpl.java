package com.qmtec.servicecore.service.colum.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.dao.ColumnInfoMapper;
import com.qmtec.servicecore.entity.ColumnInfo;
import com.qmtec.servicecore.entity.example.ColumnInfoExample;
import com.qmtec.servicecore.model.dto.ColumInfoDto;
import com.qmtec.servicecore.service.colum.ColumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class ColumServiceImpl implements ColumService {

    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    /**
     * 添加FileTemplate的Dao操作
     */
    private int addColumInfoCore(ColumInfoDto columInfoDto, String id) {
        return columnInfoMapper.insertSelective(ColumnInfo
                        .builder()
                        .id(id)
                        .name(columInfoDto.getName())
                        .type(columInfoDto.getType())
                        .build()
                , ColumnInfo.Column.id
                , ColumnInfo.Column.name
                , ColumnInfo.Column.type

        );
    }

    /**
     * 修改FlumeConfig的Dao操作
     */
    private int updateColumInfoCore(ColumInfoDto columInfoDto) {
        return columnInfoMapper.updateByExampleSelective(ColumnInfo
                        .builder()
                        .name(columInfoDto.getName())
                        .type(columInfoDto.getType())
                        .build(),
                ColumnInfoExample.newAndCreateCriteria()
                        .andIdEqualTo(columInfoDto.getId()).example()
                , ColumnInfo.Column.name
                , ColumnInfo.Column.type
        );
    }

    @Override
    public Map<String, Object> addOrUpdateColumInfo(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        String id = null;
        try {
            ColumInfoDto columInfoDto = JSONObject.parseObject(data, ColumInfoDto.class);
            if (StringUtils.isEmpty(columInfoDto.getId())) {
                ColumnInfo columnInfo = this.getColumInfoByName(columInfoDto.getName());
                if (columnInfo != null) {
                    throw new CustomException("name : " + columnInfo.getName() + "Existing");
                }
                id = UUID.randomUUID().toString().trim().replaceAll("-", "");
                if (this.addColumInfoCore(columInfoDto, id) > 0) {
                    flag = true;
                }
            } else {//修改
                ColumnInfo columnInfo = this.getColumInfoByName(columInfoDto.getName());
                if (columnInfo != null && !columnInfo.getId().equals(columInfoDto.getId())) {
                    throw new CustomException("name : " + columnInfo.getName() + "Used");
                }
                id = columInfoDto.getId();
                if (this.updateColumInfoCore(columInfoDto) > 0) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        map.put("flag", flag);
        map.put("id", id);
        return map;
    }

    @Override
    public ColumnInfo getColumInfoByName(String name) {
        ColumnInfo columnInfo = null;

        Optional<ColumnInfo> columnInfoOptional = null;
        try {
            columnInfoOptional = Optional.ofNullable(columnInfoMapper.selectOneByExample(
                    ColumnInfoExample.newAndCreateCriteria()
                            .andNameEqualTo(name)
                            .example()
            ));
            if (columnInfoOptional.isPresent()) {
                columnInfo = columnInfoOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return columnInfo;
    }

    @Override
    public List<ColumInfoDto> getColumnInfoByIds(List<String> list) {
        List<ColumInfoDto>  columInfoDtoList = new ArrayList<>();

        if (list.size() > 0) {
            Optional<List<ColumnInfo>> columnInfos = Optional.ofNullable(
                    columnInfoMapper.selectByExample(
                            ColumnInfoExample.newAndCreateCriteria().andIdIn(list).example()
                    )
            );

            if (columnInfos.isPresent()) {
                List<ColumnInfo> columnInfoList = columnInfos.get();
                columnInfoList.forEach(columnInfo -> {
                    ColumInfoDto columInfoDto = ColumInfoDto.builder()
                            .name(columnInfo.getName())
                            .type(columnInfo.getType())
                            .build();
                    columInfoDtoList.add(columInfoDto);
                });

            } else {
                throw new CustomException("No data queried ", HttpCode.CODE_400);
            }
        } else {
            throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
        }
        return columInfoDtoList;
    }


    @Override
    public ListResult<ColumInfoDto> listSelectColumInfo(String searchParams, int page, int limit) {
        ListResult<ColumInfoDto> page1 = new ListResult<ColumInfoDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

        Optional<List<ColumnInfo>> optionalColumnInfoList = null;

        ColumnInfoExample.Criteria criteria = ColumnInfoExample.newAndCreateCriteria();
        if (paramMap != null) {
            String name = (String) paramMap.get("name");
            if (!StringUtils.isEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }
            String type = (String) paramMap.get("type");
            if (!StringUtils.isEmpty(type)) {
                criteria.andTypeEqualTo(type);
            }
        }

        ColumnInfoExample columnInfoExample = criteria.example();
        columnInfoExample.page((page - 1), limit);
        //columnInfoExample.setOrderByClause("create_time desc");

        optionalColumnInfoList = Optional.ofNullable(columnInfoMapper.selectByExample(columnInfoExample));
        if (optionalColumnInfoList.isPresent()) {

            List<ColumnInfo> columnInfoList = optionalColumnInfoList.get();
            if (columnInfoList.size() > 0) {
                long count = columnInfoMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<ColumInfoDto> columInfoDtoList = new ArrayList<ColumInfoDto>();

                columnInfoList.forEach(columnInfo -> {
                    ColumInfoDto columInfoDto = ColumInfoDto.builder()
                            .id(columnInfo.getId())
                            .name(columnInfo.getName())
                            .type(columnInfo.getType())
                            .build();

                    columInfoDtoList.add(columInfoDto);
                });
                page1.setResult(columInfoDtoList);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    @Override
    public ColumInfoDto updateColumInfoBefore(String id) {
        ColumInfoDto columInfoDto = null;
        Optional<ColumnInfo> columnInfoOptional = null;
        try {
            columnInfoOptional = Optional.ofNullable(
                    columnInfoMapper.selectOneByExample(
                            ColumnInfoExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (columnInfoOptional.isPresent()) {
                ColumnInfo columnInfo = columnInfoOptional.get();
                columInfoDto = ColumInfoDto.builder()
                        .id(columnInfo.getId())
                        .name(columnInfo.getName())
                        .type(columnInfo.getType())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return columInfoDto;
    }

    @Override
    public Boolean deleteColumInfo(String data) {
        Boolean falg = false;
        try {
            List<String> ids = (List<String>) JSONArray.parseObject(data, List.class);
            if (ids.size() > 0) {
                Optional<List<ColumnInfo>> columnInfos = Optional.ofNullable(
                        columnInfoMapper.selectByExample(
                                ColumnInfoExample.newAndCreateCriteria().andIdIn(ids).example()
                        )
                );

                if (columnInfos.isPresent()) {
                    if (columnInfoMapper.deleteByExample(
                            ColumnInfoExample.newAndCreateCriteria().andIdIn(ids).example()) > 0) {
                        falg = true;
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
    public ColumInfoDto getColumInfoById(String id) {
        return this.updateColumInfoBefore(id);
    }




}
