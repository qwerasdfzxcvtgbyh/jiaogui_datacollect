package com.qmtec.servicecore.service.datax.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.dao.DataXConfigMapper;
import com.qmtec.servicecore.entity.DataXConfig;
import com.qmtec.servicecore.entity.example.DataXConfigExample;
import com.qmtec.servicecore.model.dto.DataxConfigDto;
import com.qmtec.servicecore.service.datax.DataxConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class DataxConfigServiceImpl implements DataxConfigService {

    @Autowired
    private DataXConfigMapper dataXConfigMapper;

    private int addDataxConfigCore(DataxConfigDto dataxConfigDto, String id) {
        return dataXConfigMapper.insertSelective(DataXConfig
                        .builder()
                        .id(id)
                        .name(dataxConfigDto.getName())
                        .code(dataxConfigDto.getCode())
                        .serverIp(dataxConfigDto.getServerIp())
                        .serverPort(dataxConfigDto.getServerPort())
                        .jsonFileName(dataxConfigDto.getJsonFileName())
                        .jsonFilePath(dataxConfigDto.getJsonFilePath())
                        .jsonFileContent(dataxConfigDto.getJsonFileContent())
                        .pythonscriptPath(dataxConfigDto.getPythonscriptPath())
                        .pythonscriptFileName(dataxConfigDto.getPythonscriptFileName())
                        .pythonscriptContent(dataxConfigDto.getPythonscriptContent())
                        .remark(dataxConfigDto.getRemark())
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .build()
                , DataXConfig.Column.id
                , DataXConfig.Column.name
                , DataXConfig.Column.code
                , DataXConfig.Column.serverIp
                , DataXConfig.Column.serverPort
                , DataXConfig.Column.jsonFileName
                , DataXConfig.Column.jsonFilePath
                , DataXConfig.Column.jsonFileContent
                , DataXConfig.Column.pythonscriptPath
                , DataXConfig.Column.pythonscriptFileName
                , DataXConfig.Column.pythonscriptContent
                , DataXConfig.Column.remark
                , DataXConfig.Column.createTime
                , DataXConfig.Column.modifyTime
        );
    }

    /**
     * 修改DataxConfig的Dao操作
     */
    private int updateDataxConfigCore(DataxConfigDto dataxConfigDto) {
        return dataXConfigMapper.updateByExampleSelective(DataXConfig
                        .builder()
                        .serverIp(dataxConfigDto.getServerIp())
                        .serverPort(dataxConfigDto.getServerPort())
                        .jsonFileName(dataxConfigDto.getJsonFileName())
                        .jsonFilePath(dataxConfigDto.getJsonFilePath())
                        .jsonFileContent(dataxConfigDto.getJsonFileContent())
                        .pythonscriptPath(dataxConfigDto.getPythonscriptPath())
                        .pythonscriptFileName(dataxConfigDto.getPythonscriptFileName())
                        .pythonscriptContent(dataxConfigDto.getPythonscriptContent())
                        .remark(dataxConfigDto.getRemark())
                        .modifyTime(new Date())
                        .build(),
                DataXConfigExample.newAndCreateCriteria()
                        .andIdEqualTo(dataxConfigDto.getId()).example()
                , DataXConfig.Column.serverIp
                , DataXConfig.Column.serverPort
                , DataXConfig.Column.jsonFileName
                , DataXConfig.Column.jsonFilePath
                , DataXConfig.Column.jsonFileContent
                , DataXConfig.Column.pythonscriptPath
                , DataXConfig.Column.pythonscriptFileName
                , DataXConfig.Column.pythonscriptContent
                , DataXConfig.Column.remark
                , DataXConfig.Column.modifyTime
        );
    }

    @Override
    public Boolean addOrUpdateDataxConfig(String data) {
        Boolean falg = false;

        try {
            DataxConfigDto dataxConfigDto = JSONObject.parseObject(data, DataxConfigDto.class);
            if (StringUtils.isEmpty(dataxConfigDto.getId())) {
                DataXConfig dataXConfig = this.getDataXConfigByCode(dataxConfigDto.getCode());
                if (dataXConfig != null) {
                    throw new CustomException("code : " + dataXConfig.getCode() + " Existing");
                }
                String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
                if (this.addDataxConfigCore(dataxConfigDto, id) > 0) {
                    falg = true;
                }
            } else {//修改
                DataXConfig dataXConfig = this.getDataXConfigByCode(dataxConfigDto.getCode());
                if (dataXConfig != null && !dataXConfig.getId().equals(dataxConfigDto.getId())) {
                    throw new CustomException("code : " + dataxConfigDto.getCode() + " Used");
                }
                if (this.updateDataxConfigCore(dataxConfigDto) > 0) {
                    falg = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }

        return falg;
    }

    /**
     * 通过Code获取
     */
    private DataXConfig getDataXConfigByCode(String code) {
        DataXConfig dataXConfig = null;

        Optional<DataXConfig> dataXConfigOptional = null;
        try {
            dataXConfigOptional = Optional.ofNullable(
                    dataXConfigMapper.selectOneByExample(
                            DataXConfigExample.newAndCreateCriteria()
                                    .andCodeEqualTo(code)
                                    .example()
                    )
            );

            if (dataXConfigOptional.isPresent()) {
                dataXConfig = dataXConfigOptional.get();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return dataXConfig;
    }

    @Override
    public ListResult<DataxConfigDto> listSelectDataxConfig(String searchParams, int page, int limit) {
        ListResult<DataxConfigDto> page1 = new ListResult<DataxConfigDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);
        Optional<List<DataXConfig>> optionalDataXConfigList = null;

        DataXConfigExample.Criteria criteria = DataXConfigExample.newAndCreateCriteria();
        if (paramMap != null) {

            String name = (String) paramMap.get("name");
            if (!StringUtils.isEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }

            String code = (String) paramMap.get("code");
            if (!StringUtils.isEmpty(code)) {
                //criteria.andCodeEqualTo(code);
                criteria.andCodeLike("%" + code + "%");
            }

            String serverIp = (String) paramMap.get("serverIp");
            if (!StringUtils.isEmpty(serverIp)) {
                criteria.andServerIpLike("%" + serverIp + "%");
            }
        }

        DataXConfigExample dataXConfigExample = criteria.example();
        dataXConfigExample.page((page - 1), limit);
        dataXConfigExample.setOrderByClause("create_time desc");

        optionalDataXConfigList = Optional.ofNullable(dataXConfigMapper.selectByExample(dataXConfigExample));
        if (optionalDataXConfigList.isPresent()) {

            List<DataXConfig> dataXConfigs = optionalDataXConfigList.get();
            if (dataXConfigs.size() > 0) {
                long count = dataXConfigMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<DataxConfigDto> dataxConfigDtos = new ArrayList<DataxConfigDto>();

                dataXConfigs.forEach(dataXConfig -> {
                    DataxConfigDto dataxConfigDto = DataxConfigDto.builder()
                            .id(dataXConfig.getId())
                            .name(dataXConfig.getName())
                            .code(dataXConfig.getCode())
                            .jsonFileName(dataXConfig.getJsonFileName())
                            .jsonFilePath(dataXConfig.getJsonFilePath())
                            .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                            .pythonscriptPath(dataXConfig.getPythonscriptPath())
                            .serverIp(dataXConfig.getServerIp())
                            .serverPort(dataXConfig.getServerPort())
                            .runstate(dataXConfig.getRunstate())
                            .createTime(dataXConfig.getCreateTime())
                            .build();
                    //FlumeConfig.Runstate[] runstates = FlumeConfig.Runstate.values();
                    dataxConfigDtos.add(dataxConfigDto);
                });

                page1.setResult(dataxConfigDtos);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    @Override
    public DataxConfigDto updateDataxConfigBefore(String id) {
        DataxConfigDto dataxConfigDto = null;
        Optional<DataXConfig> dataXConfigOptional = null;
        try {
            dataXConfigOptional = Optional.ofNullable(
                    dataXConfigMapper.selectOneByExampleWithBLOBs(
                            DataXConfigExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (dataXConfigOptional.isPresent()) {
                DataXConfig dataXConfig = dataXConfigOptional.get();
                dataxConfigDto = DataxConfigDto.builder()
                        .id(dataXConfig.getId())
                        .name(dataXConfig.getName())
                        .code(dataXConfig.getCode())
                        .jsonFilePath(dataXConfig.getJsonFilePath())
                        .jsonFileName(dataXConfig.getJsonFileName())
                        .jsonFileContent(dataXConfig.getJsonFileContent())
                        .pythonscriptPath(dataXConfig.getPythonscriptPath())
                        .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                        .pythonscriptContent(dataXConfig.getPythonscriptContent())
                        .serverIp(dataXConfig.getServerIp())
                        .serverPort(dataXConfig.getServerPort())
                        .remark(dataXConfig.getRemark())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new RuntimeException("Data Query Failure");
        }
        return dataxConfigDto;
    }

    @Override
    public Boolean deleteDataxConfig(String data) {
        Boolean falg = false;

        try {
            List<String> Ids = JSONObject.parseArray(data, String.class);

            if (Ids.size() > 0) {
                Optional<List<DataXConfig>> optionalDataXConfigs = Optional.ofNullable(
                        dataXConfigMapper.selectByExample(
                                DataXConfigExample.newAndCreateCriteria()
                                        .andIdIn(Ids)
                                        .example()
                        )
                );

                if (optionalDataXConfigs.isPresent()) {
                    List<DataXConfig> dataXConfigs = optionalDataXConfigs.get();

                    if(dataXConfigs.size() > 0){
                        dataXConfigs.forEach(dataXConfig -> {
                            if (!DataXConfig.Runstate.UNOPENED.getValue().equals(dataXConfig.getRunstate())
                                    && !DataXConfig.Runstate.FAIL.getValue().equals(dataXConfig.getRunstate())
                            ) {
                                throw new CustomException("Some data states do not meet deletion requirements");
                            }
                        });

                        if (dataXConfigMapper.deleteByExample(
                                DataXConfigExample.newAndCreateCriteria()
                                        .andIdIn(Ids)
                                        .example()) > 0) {
                            falg = true;
                        }
                    }else{
                        throw new CustomException("No data queried ", HttpCode.CODE_400);
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
}
