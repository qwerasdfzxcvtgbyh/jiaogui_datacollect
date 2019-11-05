package com.qmtec.servicecore.service.datax.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.comm.CommHttpReq;
import com.qmtec.servicecore.dao.DataXConfigMapper;
import com.qmtec.servicecore.entity.DataXConfig;
import com.qmtec.servicecore.entity.example.DataXConfigExample;
import com.qmtec.servicecore.model.bo.DataxConfigBo;
import com.qmtec.servicecore.model.dto.DataxConfigDto;
import com.qmtec.servicecore.model.vo.DataxConfigVo;
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
                        .dataxHome(dataxConfigDto.getDataxHome())
                        .jsonFileName(dataxConfigDto.getJsonFileName())
                        .jsonFileContent(dataxConfigDto.getJsonFileContent())
                        .pythonscriptFileName(dataxConfigDto.getPythonscriptFileName())
                        .pythonscriptContent(dataxConfigDto.getPythonscriptContent())
                        .remark(dataxConfigDto.getRemark())
                        .jsonSwitch(dataxConfigDto.getJsonSwitch())
                        .pythonSwitch(dataxConfigDto.getPythonSwitch())
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .build()
                , DataXConfig.Column.id
                , DataXConfig.Column.name
                , DataXConfig.Column.code
                , DataXConfig.Column.serverIp
                , DataXConfig.Column.serverPort
                , DataXConfig.Column.dataxHome
                , DataXConfig.Column.jsonFileName
                , DataXConfig.Column.jsonFileContent
                , DataXConfig.Column.pythonscriptFileName
                , DataXConfig.Column.pythonscriptContent
                , DataXConfig.Column.remark
                , DataXConfig.Column.jsonSwitch
                , DataXConfig.Column.pythonSwitch
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
                        .dataxHome(dataxConfigDto.getDataxHome())
                        .jsonFileName(dataxConfigDto.getJsonFileName())
                        .jsonFileContent(dataxConfigDto.getJsonFileContent())
                        .pythonscriptFileName(dataxConfigDto.getPythonscriptFileName())
                        .pythonscriptContent(dataxConfigDto.getPythonscriptContent())
                        .remark(dataxConfigDto.getRemark())
                        .jsonSwitch(dataxConfigDto.getJsonSwitch())
                        .pythonSwitch(dataxConfigDto.getPythonSwitch())
                        .modifyTime(new Date())
                        .build(),
                DataXConfigExample.newAndCreateCriteria()
                        .andIdEqualTo(dataxConfigDto.getId()).example()
                , DataXConfig.Column.serverIp
                , DataXConfig.Column.serverPort
                , DataXConfig.Column.dataxHome
                , DataXConfig.Column.jsonFileName
                , DataXConfig.Column.jsonFileContent
                , DataXConfig.Column.pythonscriptFileName
                , DataXConfig.Column.pythonscriptContent
                , DataXConfig.Column.remark
                , DataXConfig.Column.jsonSwitch
                , DataXConfig.Column.pythonSwitch
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
                            .dataxHome(dataXConfig.getDataxHome() == null ? "" : dataXConfig.getDataxHome())
                            .jsonFileName(dataXConfig.getJsonFileName())
                            .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                            .serverIp(dataXConfig.getServerIp())
                            .serverPort(dataXConfig.getServerPort())
                            .runstate(dataXConfig.getRunstate())
                            .createTime(dataXConfig.getCreateTime())
                            .build();

                    //dataXConfig.Runstate[] runstates = dataXConfig.Runstate.values();
                    DataXConfig.Runstate[] runstates = DataXConfig.Runstate.values();
                    for (DataXConfig.Runstate runstate : runstates) {
                        if (runstate.getValue().equals(dataXConfig.getRunstate())) {
                            dataxConfigDto.setRunstateName(runstate.getName());
                            break;
                        }
                    }

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
                        .dataxHome(dataXConfig.getDataxHome())
                        .jsonFileName(dataXConfig.getJsonFileName())
                        .jsonFileContent(dataXConfig.getJsonFileContent())
                        .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                        .pythonscriptContent(dataXConfig.getPythonscriptContent())
                        .serverIp(dataXConfig.getServerIp())
                        .serverPort(dataXConfig.getServerPort())
                        .remark(dataXConfig.getRemark())
                        .jsonSwitch(dataXConfig.getJsonSwitch())
                        .pythonSwitch(dataXConfig.getPythonSwitch())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
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

                    if (dataXConfigs.size() > 0) {
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
                    } else {
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

    public DataXConfig selectOnedataXConfig(String id) {
        DataXConfig dataXConfig = null;

        Optional<DataXConfig> dataXConfigOptional = null;
        try {
            dataXConfigOptional = Optional.ofNullable(
                    dataXConfigMapper.selectOneByExampleSelective(
                            DataXConfigExample.newAndCreateCriteria().andIdEqualTo(id).example()));
        } catch (Exception e) {
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        if (dataXConfigOptional.isPresent()) {
            dataXConfig = dataXConfigOptional.get();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return dataXConfig;
    }

    /**
     * --------------------------------------------------------------------------------------------------------
     **/

    @Override
    public DataxConfigBo selectOneDataxConfigById(DataxConfigVo dataxConfigVo) {
        DataxConfigBo dataxConfigBo = new DataxConfigBo();

        Optional<DataXConfig> optionalDataXConfig = null;
        try {
            optionalDataXConfig = Optional.ofNullable(dataXConfigMapper.selectOneByExampleWithBLOBs(
                    DataXConfigExample.newAndCreateCriteria()
                            .andIdEqualTo(dataxConfigVo.getId())
                            .example()));

            if (optionalDataXConfig.isPresent()) {
                DataXConfig dataXConfig = optionalDataXConfig.get();
                dataxConfigBo = DataxConfigBo.builder()
                        .id(dataXConfig.getId())
                        .name(dataXConfig.getName())
                        .code(dataXConfig.getCode())
                        .dataxHome(dataXConfig.getDataxHome())
                        .jsonFileContent(dataXConfig.getJsonFileContent())
                        .jsonFileName(dataXConfig.getJsonFileName())
                        .pythonscriptContent(dataXConfig.getPythonscriptContent())
                        .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                        .serverIp(dataXConfig.getServerIp())
                        .runstate(dataXConfig.getRunstate())
                        .jsonSwitch(dataXConfig.getJsonSwitch())
                        .pythonSwitch(dataXConfig.getPythonSwitch())
                        .build();
            } else {
                throw new CustomException(" Data does not exist ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return dataxConfigBo;
    }

    @Override
    public Boolean updateDataXConfigByIdAndRunstate(DataxConfigVo dataxConfigVo) {
        Boolean flag = false;

        Integer runstate = dataxConfigVo.getRunstate();
        if (runstate != null && runstate != 0) {
            flag = updateDataXConfigByIdAndRunstate(dataxConfigVo.getId(), runstate);
        } else {
            throw new CustomException("Parametric anomaly ");
        }

        return flag;
    }

    private Boolean updateDataXConfigByIdAndRunstate(String id, Integer runstate) {
        Boolean flag = false;

        DataXConfig dataXConfig = DataXConfig.builder()
                .id(id)
                .runstate(runstate)
                .modifyTime(new Date())
                .build();

        if (dataXConfigMapper.updateByExampleSelective(dataXConfig,
                DataXConfigExample.newAndCreateCriteria()
                        .andIdEqualTo(id)
                        .example(), DataXConfig.Column.runstate, DataXConfig.Column.modifyTime) > 0) {
            flag = true;
        } else {
            throw new CustomException("Data Update Failure", HttpCode.CODE_500);
        }
        return flag;
    }

    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (推送端)
     */
    @Override
    public Boolean pushCheckDataXConfigById(String id) {
        Boolean flag = false;

        if (!StringUtils.isEmpty(id)) {

            List<Integer> list = new ArrayList<Integer>();
            list.add(DataXConfig.Runstate.FAIL.getValue());
            list.add(DataXConfig.Runstate.UNOPENED.getValue());

            Optional<DataXConfig> dataXConfigOptional = null;
            try {
                dataXConfigOptional = Optional.ofNullable(
                        dataXConfigMapper.selectOneByExample(
                                DataXConfigExample.newAndCreateCriteria()
                                        .andIdEqualTo(id)
                                        .andRunstateIn(list)
                                        .example()));

                if (dataXConfigOptional.isPresent()) {
                    flag = true;
                } else {
                    flag = false;
                }
            } catch (Exception e) {
                log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
                throw new CustomException("Data Query Failure", HttpCode.CODE_500);
            }
        } else {
            throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
        }
        return flag;
    }

    @Override
    public Boolean receiveCheckDataXConfigById(DataxConfigVo dataxConfigVo) {
        Boolean flag = false;

        Optional<DataXConfig> dataXConfigOptional = null;
        try {
            dataXConfigOptional = Optional.ofNullable(
                    dataXConfigMapper.selectOneByExample(
                            DataXConfigExample.newAndCreateCriteria()
                                    .andIdEqualTo(dataxConfigVo.getId())
                                    .andRunstateEqualTo(dataxConfigVo.getRunstate())
                                    .example()));
        } catch (Exception e) {
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        if (dataXConfigOptional.isPresent()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    private Map<String, Object> startDataXComm(DataXConfig dataXConfig, String urlSuffix, Integer runstate) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        StringBuffer url = new StringBuffer();
        //生成请求flume监控端口的url
        url.append("http://").append(dataXConfig.getServerIp()).append(":").append(dataXConfig.getServerPort())
                .append(urlSuffix);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", dataXConfig.getId());

        //修改状态为：开始推送
        dataXConfig.setRunstate(runstate);
        dataXConfig.setModifyTime(new Date());

        if (dataXConfigMapper.updateByExampleSelective(dataXConfig,
                DataXConfigExample.newAndCreateCriteria()
                        .andIdEqualTo(dataXConfig.getId())
                        .example(), DataXConfig.Column.runstate, DataXConfig.Column.modifyTime) > 0) {
            //发送远程请求
            synchronized (this) {
                map = CommHttpReq.agentRequest(url.toString(), params);
            }
        } else {
            throw new CustomException("Data Update Failure", HttpCode.CODE_500);
        }
        return map;
    }

    @Override
    public Boolean updateDataXConfigByProcesspidAndRunstateAndId(DataxConfigVo dataxConfigVo) {
        Boolean flag = false;

        DataXConfig dataXConfig = DataXConfig.builder()
                .id(dataxConfigVo.getId())
                .processPid(dataxConfigVo.getProcessPid())
                .runstate(dataxConfigVo.getRunstate())
                .startupTime(dataxConfigVo.getStartupTime())
                .modifyTime(new Date())
                .build();

        if (dataXConfig != null) {
            if (dataXConfigMapper.updateByExampleSelective(dataXConfig,
                    DataXConfigExample.newAndCreateCriteria()
                            .andIdEqualTo(dataXConfig.getId()).example(),
                    DataXConfig.Column.processPid,
                    DataXConfig.Column.runstate,
                    DataXConfig.Column.modifyTime,
                    DataXConfig.Column.startupTime) > 0) {
                flag = true;
            } else {
                throw new CustomException("Failed to modify to data", HttpCode.CODE_400);
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flag;
    }

    @Override
    public Map<String, Object> startDataX(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = data;
        try {
            if (!StringUtils.isEmpty(id)) {
                if (this.pushCheckDataXConfigById(id)) {
                    DataXConfig dataXConfig = this.selectOnedataXConfig(id);
                    if (dataXConfig != null) {
                        String urlSuffix = "/agent/startDataX";
                        map = this.startDataXComm(dataXConfig, urlSuffix, DataXConfig.Runstate.PUSHING.getValue());
                    } else {
                        throw new CustomException("未查询到ID:" + id + "的任务!!", HttpCode.CODE_400);
                    }
                } else {
                    throw new CustomException("状态不符开启要求，请刷新页面！");
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            this.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.FAIL.getValue());
            e.printStackTrace();
            log.error("Start Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return map;
    }

    @Override
    public DataxConfigBo queryPartialFieldsDataXConfigById(DataxConfigVo dataxConfigVo) {
        DataxConfigBo dataxConfigBo = new DataxConfigBo();

        DataXConfig dataXConfig = this.selectOnedataXConfig(dataxConfigVo.getId());

        if (dataXConfig != null) {
            dataxConfigBo = DataxConfigBo.builder()
                    .id(dataXConfig.getId())
                    .name(dataXConfig.getName())
                    .code(dataXConfig.getCode())
                    .processPid(dataXConfig.getProcessPid())
                    .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                    .serverPort(dataXConfig.getServerPort())
                    .jsonFileName(dataXConfig.getJsonFileName())
                    .dataxHome(dataXConfig.getDataxHome())
                    .serverIp(dataXConfig.getServerIp())
                    .runstate(dataXConfig.getRunstate())
                    .jsonSwitch(dataXConfig.getJsonSwitch())
                    .pythonSwitch(dataXConfig.getPythonSwitch())
                    .build();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return dataxConfigBo;
    }

    @Override
    public List<DataxConfigBo> queryDataXConfigByServerIpAndRunstate(DataxConfigVo dataxConfigVo) {
        List<DataxConfigBo> dataxConfigBoArrayList = new ArrayList<DataxConfigBo>();

        Optional<List<DataXConfig>> optionalDataXConfigList = null;
        try {
            optionalDataXConfigList = Optional.ofNullable(
                    dataXConfigMapper.selectByExample(
                            DataXConfigExample.newAndCreateCriteria()
                                    .andServerIpEqualTo(dataxConfigVo.getServerIp())
                                    .andRunstateEqualTo(dataxConfigVo.getRunstate())
                                    .example()
                    ));

            if (optionalDataXConfigList.isPresent()) {
                List<DataXConfig> dataXConfigs = optionalDataXConfigList.get();
                dataXConfigs.forEach(dataXConfig -> {
                    DataxConfigBo dataxConfigBo = DataxConfigBo.builder()
                            .id(dataXConfig.getId())
                            .name(dataXConfig.getName())
                            .code(dataXConfig.getCode())
                            .processPid(dataXConfig.getProcessPid())
                            .pythonscriptFileName(dataXConfig.getPythonscriptFileName())
                            .serverPort(dataXConfig.getServerPort())
                            .jsonFileName(dataXConfig.getJsonFileName())
                            .dataxHome(dataXConfig.getDataxHome())
                            .serverIp(dataXConfig.getServerIp())
                            .runstate(dataXConfig.getRunstate())
                            .jsonSwitch(dataXConfig.getJsonSwitch())
                            .pythonSwitch(dataXConfig.getPythonSwitch())
                            .build();
                    dataxConfigBoArrayList.add(dataxConfigBo);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return dataxConfigBoArrayList;
    }

    @Override
    public Map<String, Object> restartDataX(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = data;
        try {
            if (!StringUtils.isEmpty(id)) {
                DataXConfig dataXConfig = this.selectOnedataXConfig(id);
                if (dataXConfig != null) {
                    String urlSuffix = "/agent/restartDataX";
                    map = this.startDataXComm(dataXConfig, urlSuffix, DataXConfig.Runstate.RESTART.getValue());
                } else {
                    throw new CustomException("未查询到ID:" + id + "的任务!!", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            this.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.FAIL.getValue());
            e.printStackTrace();
            log.error("Start Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return map;
    }

    @Override
    public Map<String, Object> stopDataX(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = data;
        try {
            if (!StringUtils.isEmpty(id)) {
                DataXConfig dataXConfig = this.selectOnedataXConfig(id);

                if (dataXConfig != null) {
                    synchronized (this) {
                        if (DataXConfig.Runstate.RUNING.getValue().equals(dataXConfig.getRunstate())) {
                            StringBuffer url = new StringBuffer();
                            //生成请求flume监控端口的url
                            url.append("http://").append(dataXConfig.getServerIp()).append(":").append(dataXConfig.getServerPort())
                                    .append("/agent/stopDataX");
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("contextId", String.valueOf(dataXConfig.getId()));

                            //发送远程请求
                            map = CommHttpReq.agentRequest(url.toString(), params);
                        } else {
                            throw new CustomException("状态不符关闭要求，请刷新页面！");
                        }
                    }
                } else {
                    throw new CustomException("未查询到ID:" + id + "的任务!!", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Start Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return map;
    }


}
