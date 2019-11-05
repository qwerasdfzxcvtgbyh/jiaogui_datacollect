package com.qmtec.servicecore.service.flume.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.entity.flume.FlumeMonitor;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.util.HttpClientResult;
import com.qmtec.common.util.HttpClientUtil;
import com.qmtec.common.util.OrderContextIdFactory;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.comm.CommHttpReq;
import com.qmtec.servicecore.dao.FlumeConfigMapper;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.example.FlumeConfigExample;
import com.qmtec.servicecore.model.bo.FlumeConfigBo;
import com.qmtec.servicecore.model.dto.FlumeConfigDto;
import com.qmtec.servicecore.model.vo.FlumeConfigVo;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flume.FlumeMomitorProcess;
import com.qmtec.servicecore.service.flumeMonitor.FlumeMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class FlumeConfigServiceImpl implements FlumeConfigService {

    @Autowired
    private FlumeConfigMapper flumeConfigMapper;
    @Autowired
    private FlumeMomitorProcess flumeMomitorProcess;
    @Autowired
    private FlumeMonitorService flumeMonitorService;

    /**
     * 通过ContextId查询数据
     */
    @Override
    public FlumeConfigBo selectOneFlumeConfigByContextId(FlumeConfigVo flumeConfigVo) {
        FlumeConfigBo flumeConfigBo = new FlumeConfigBo();

        FlumeConfig flumeConfig = this.selectOneFlumeConfig(flumeConfigVo.getContextId());

        if (flumeConfig != null) {
            flumeConfigBo = FlumeConfigBo.builder()
                    .agentName(flumeConfig.getAgentName())
                    .code(flumeConfig.getCode())
                    .contextId(flumeConfig.getContextId())
                    .fileConfigContent(flumeConfig.getFileConfigContent())
                    .flumeHome(flumeConfig.getFlumeHome())
                    .createTime(flumeConfig.getCreateTime())
                    .startupTime(flumeConfig.getStartupTime())
                    .modifyTime(flumeConfig.getModifyTime())
                    .monitorPort(flumeConfig.getMonitorPort())
                    .processPid(flumeConfig.getProcessPid())
                    .name(flumeConfig.getName())
                    .serverIp(flumeConfig.getServerIp())
                    .runstate(flumeConfig.getRunstate())
                    .profileName(flumeConfig.getProfileName())
                    .build();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flumeConfigBo;
    }


    /**
     * 通过ContextId查询部分字段数据
     */
    @Override
    public FlumeConfigBo queryPartialFieldsFlumeConfigByContextId(FlumeConfigVo flumeConfigVo) {
        FlumeConfigBo flumeConfigBo = new FlumeConfigBo();

        FlumeConfig flumeConfig = this.selectOneFlumeConfig(flumeConfigVo.getContextId());

        if (flumeConfig != null) {
            flumeConfigBo = FlumeConfigBo.builder()
                    .agentName(flumeConfig.getAgentName())
                    .code(flumeConfig.getCode())
                    .contextId(flumeConfig.getContextId())
                    .flumeHome(flumeConfig.getFlumeHome())
                    .monitorPort(flumeConfig.getMonitorPort())
                    .processPid(flumeConfig.getProcessPid())
                    .name(flumeConfig.getName())
                    .serverIp(flumeConfig.getServerIp())
                    .runstate(flumeConfig.getRunstate())
                    .profileName(flumeConfig.getProfileName())
                    .build();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flumeConfigBo;
    }

    /**
     * 启动完成之后，用来更新数据(进程ID，监控端口，运行状态，启动时间)
     */
    @Override
    public Boolean updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(FlumeConfigVo flumeConfigVo) {
        Boolean flag = false;

        FlumeConfig flumeConfig = FlumeConfig.builder()
                .contextId(flumeConfigVo.getContextId())
                .processPid(flumeConfigVo.getProcessPid())
                .monitorPort(flumeConfigVo.getMonitorPort())
                .runstate(flumeConfigVo.getRunstate())
                .startupTime(flumeConfigVo.getStartupTime())
                .modifyTime(new Date())
                .build();

        if (flumeConfig != null) {

            if (flumeConfigMapper.updateByExampleSelective(flumeConfig,
                    FlumeConfigExample.newAndCreateCriteria()
                            .andContextIdEqualTo(flumeConfigVo.getContextId()).example(),
                    FlumeConfig.Column.processPid,
                    FlumeConfig.Column.monitorPort,
                    FlumeConfig.Column.runstate,
                    FlumeConfig.Column.modifyTime,
                    FlumeConfig.Column.startupTime) > 0) {
                flag = true;
            } else {
                throw new CustomException("Failed to modify to data", HttpCode.CODE_400);
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flag;
    }


    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (推送端)
     */
    @Override
    public Boolean pushCheckFlumeConfigByContextId(Long contextId) {
        Boolean flag = false;

        if (contextId != null && contextId != 0) {

            List<Integer> list = new ArrayList<Integer>();
            list.add(FlumeConfig.Runstate.FAIL.getValue());
            list.add(FlumeConfig.Runstate.UNOPENED.getValue());

            Optional<FlumeConfig> flumeConfigOptional = null;
            try {
                flumeConfigOptional = Optional.ofNullable(
                        flumeConfigMapper.selectOneByExample(
                                FlumeConfigExample.newAndCreateCriteria()
                                        .andContextIdEqualTo(contextId)
                                        .andRunstateIn(list)
                                        .example()));
            } catch (Exception e) {
                log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
                throw new CustomException("Data Query Failure", HttpCode.CODE_500);
            }

            if (flumeConfigOptional.isPresent()) {
                flag = true;
            } else {
                flag = false;
            }

        } else {
            throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
        }
        return flag;
    }

    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (接收端)
     */
    @Override
    public Boolean receiveCheckFlumeConfigByContextId(FlumeConfigVo flumeConfigVo) {
        Boolean flag = false;

        Optional<FlumeConfig> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectOneByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andContextIdEqualTo(flumeConfigVo.getContextId())
                                    .andRunstateEqualTo(flumeConfigVo.getRunstate())
                                    .example()));
        } catch (Exception e) {
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        if (flumeConfigOptional.isPresent()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 通过contextId查询数据
     */
    public FlumeConfig selectOneFlumeConfig(Long contextId) {
        FlumeConfig flumeConfig = null;

        Optional<FlumeConfig> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectOneByExampleSelective(
                            FlumeConfigExample.newAndCreateCriteria().andContextIdEqualTo(contextId).example()));
        } catch (Exception e) {
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        if (flumeConfigOptional.isPresent()) {
            flumeConfig = flumeConfigOptional.get();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flumeConfig;
    }


    /**
     * 根据ContextId更新数据(运行状态)
     */
    @Override
    public Boolean updateFlumeConfigByContextIdAndRunstate(FlumeConfigVo flumeConfigVo) {
        Boolean flag = false;

        Integer runstate = flumeConfigVo.getRunstate();

        if (runstate != null && runstate != 0) {
            flag = updateFlumeConfigByContextIdAndRunstate(flumeConfigVo.getContextId(), runstate);
        } else {
            throw new CustomException("Parametric anomaly ");
        }
        return flag;
    }

    private Boolean updateFlumeConfigByContextIdAndRunstate(Long contextId, Integer runstate) {
        Boolean flag = false;

        FlumeConfig flumeConfig = FlumeConfig.builder()
                .contextId(contextId)
                .runstate(runstate)
                .modifyTime(new Date())
                .build();

        if (flumeConfigMapper.updateByExampleSelective(flumeConfig,
                FlumeConfigExample.newAndCreateCriteria()
                        .andContextIdEqualTo(contextId)
                        .example(), FlumeConfig.Column.runstate, FlumeConfig.Column.modifyTime) > 0) {
            flag = true;
        } else {
            throw new CustomException("Data Update Failure", HttpCode.CODE_500);
        }
        return flag;
    }


    @Override
    public Boolean verifyMonitorPortIsAvailableByServerIp(FlumeConfigVo flumeConfigVo) {
        Boolean flag = false;

        if (!StringUtils.isEmpty(flumeConfigVo.getServerIp()) && flumeConfigVo.getMonitorPort() != 0
                && flumeConfigVo.getMonitorPort() != null) {

            String serverIp = flumeConfigVo.getServerIp();
            Integer monitorPort = flumeConfigVo.getMonitorPort();

            List<Integer> list = new ArrayList<Integer>();
            list.add(FlumeConfig.Runstate.FAIL.getValue());
            list.add(FlumeConfig.Runstate.UNOPENED.getValue());

            Optional<List<FlumeConfig>> flumeConfigOptional = null;
            try {
                flumeConfigOptional = Optional.ofNullable(
                        flumeConfigMapper.selectByExampleSelective(
                                FlumeConfigExample.newAndCreateCriteria()
                                        .andServerIpEqualTo(serverIp)
                                        .andMonitorPortEqualTo(monitorPort)
                                        .andRunstateNotIn(list)
                                        .example()));
            } catch (Exception e) {
                log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
                throw new CustomException("Data Query Failure", HttpCode.CODE_500);
            }

            if (flumeConfigOptional.isPresent()) {
                List<FlumeConfig> flumeConfigList = flumeConfigOptional.get();
                if (flumeConfigList.size() > 0) {
                    flag = false;
                } else {
                    flag = true;
                }
            }

        } else {
            throw new CustomException("Parametric anomaly ");
        }

        return flag;
    }


    /**
     * 获取flume需要进行监控端口访问的数据
     */
    @Override
    public List<FlumeConfig> monitoringFlume() {
        List<FlumeConfig> flumeConfigList = new ArrayList<FlumeConfig>();

        Optional<List<FlumeConfig>> flumeConfigOptional = null;
        try {

            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andRunstateEqualTo(FlumeConfig.Runstate.RUNING.getValue())
                                    .example()
                    ));

            if (flumeConfigOptional.isPresent()) {
                flumeConfigList = flumeConfigOptional.get();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return flumeConfigList;
    }

    @Override
    public List<FlumeConfigBo> queryFlumeConfigByServerIpAndRunstate(FlumeConfigVo flumeConfigVo) {
        List<FlumeConfigBo> flumeConfigBos = new ArrayList<FlumeConfigBo>();

        Optional<List<FlumeConfig>> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andServerIpEqualTo(flumeConfigVo.getServerIp())
                                    .andRunstateEqualTo(flumeConfigVo.getRunstate())
                                    .example()
                    ));

            if (flumeConfigOptional.isPresent()) {
                List<FlumeConfig> flumeConfigList = flumeConfigOptional.get();
                flumeConfigList.forEach(flumeConfig -> {
                    FlumeConfigBo flumeConfigBo = FlumeConfigBo.builder()
                            .agentName(flumeConfig.getAgentName())
                            .code(flumeConfig.getCode())
                            .contextId(flumeConfig.getContextId())
                            .flumeHome(flumeConfig.getFlumeHome())
                            .monitorPort(flumeConfig.getMonitorPort())
                            .processPid(flumeConfig.getProcessPid())
                            .name(flumeConfig.getName())
                            .serverIp(flumeConfig.getServerIp())
                            .runstate(flumeConfig.getRunstate())
                            .profileName(flumeConfig.getProfileName())
                            .build();
                    flumeConfigBos.add(flumeConfigBo);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return flumeConfigBos;
    }

    /**
     * --------------------------------------------------------------------------------------
     **/

    /**
     * 前端主动刷新
     */
    @Override
    public Boolean refreshFlumeRunStatus(String data) {
        Boolean flag = false;

        List<Long> list = JSONObject.parseArray(data, Long.class);

        if (list.size() > 0) {
            Optional<List<FlumeConfig>> flumeConfigOptional = null;
            try {

                flumeConfigOptional = Optional.ofNullable(
                        flumeConfigMapper.selectByExample(
                                FlumeConfigExample.newAndCreateCriteria()
                                        .andContextIdIn(list).example()
                        ));

                if (flumeConfigOptional.isPresent()) {
                    List<FlumeConfig> flumeConfigList = flumeConfigOptional.get();

                    List<FlumeConfig> needCheck = new ArrayList<>();
                    flumeConfigList.forEach(flumeConfig -> {
                        if (FlumeConfig.Runstate.RUNING.getValue().equals(flumeConfig.getRunstate())) {
                            needCheck.add(flumeConfig);
                        }
                    });

                    if (needCheck.size() > 0) {
                        List<FlumeMonitor> flumeMonitorList = flumeMomitorProcess.refreshStatus(needCheck, true);
                        flumeMonitorList.forEach(flumeMonitor -> {
                            Long contextId = flumeMonitor.getContextId();
                            Boolean b = flumeMonitor.getIsRun();

                            if (b) {
                                log.debug("ContextId : " + contextId + "任运行");
                                updateFlumeConfigByContextIdAndRunstate(
                                        FlumeConfigVo.builder()
                                                .contextId(contextId)
                                                .runstate(FlumeConfig.Runstate.RUNING.getValue()).build());
                            } else {
                                updateFlumeConfigByContextIdAndRunstate(
                                        FlumeConfigVo.builder()
                                                .contextId(contextId)
                                                .runstate(FlumeConfig.Runstate.PORTMONITORINGEXCEPTION.getValue()).build());
                            }
                        });
                    }
                    flag = true;
                } else {
                    throw new CustomException("Data does not exist", HttpCode.CODE_400);
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
                throw new CustomException(e.getMessage());
            }

        } else {
            throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
        }
        return flag;
    }

    /**
     * 分页查询
     */
    @Override
    public ListResult<FlumeConfigDto> listSelectFlumeConfig(String searchParams, int page, int limit) {
        ListResult<FlumeConfigDto> page1 = new ListResult<FlumeConfigDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);
        Optional<List<FlumeConfig>> flumeConfigOptional = null;

        FlumeConfigExample.Criteria criteria = FlumeConfigExample.newAndCreateCriteria();
        if (paramMap != null) {

            String cd = (String) paramMap.get("contextId");
            if (!StringUtils.isEmpty(cd)) {
                Long contextId = Long.valueOf((String) paramMap.get("contextId"));
                if (contextId != null && contextId != 0) {
                    criteria.andContextIdEqualTo(contextId);
                }
            }

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

        FlumeConfigExample flumeConfigExample = criteria.example();
        flumeConfigExample.page((page - 1), limit);
        flumeConfigExample.setOrderByClause("create_time desc");

        flumeConfigOptional = Optional.ofNullable(flumeConfigMapper.selectByExample(flumeConfigExample));
        if (flumeConfigOptional.isPresent()) {

            List<FlumeConfig> flumeConfigList = flumeConfigOptional.get();
            if (flumeConfigList.size() > 0) {
                long count = flumeConfigMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<FlumeConfigDto> flumeConfigDtoList = new ArrayList<FlumeConfigDto>();

                flumeConfigList.forEach(flumeConfig -> {
                    FlumeConfigDto flumeConfigDto = FlumeConfigDto.builder()
                            .contextId(flumeConfig.getContextId())
                            .name(flumeConfig.getName())
                            .code(flumeConfig.getCode())
                            .flumeHome(flumeConfig.getFlumeHome())
                            .profileName(flumeConfig.getProfileName())
                            .serverIp(flumeConfig.getServerIp())
                            .serverPort(flumeConfig.getServerPort())
                            .runstate(flumeConfig.getRunstate())
                            .createTime(flumeConfig.getCreateTime())
                            .build();

                    FlumeConfig.Runstate[] runstates = FlumeConfig.Runstate.values();

                    for (FlumeConfig.Runstate runstate : runstates) {
                        if (runstate.getValue().equals(flumeConfig.getRunstate())) {
                            flumeConfigDto.setRunStateName(runstate.getName());
                            break;
                        }
                    }
                    flumeConfigDtoList.add(flumeConfigDto);
                });

                page1.setResult(flumeConfigDtoList);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    /**
     * 通过Code获取
     */
    private FlumeConfig getFlumeConfigByCode(String code) {
        FlumeConfig flumeConfig = null;

        Optional<FlumeConfig> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectOneByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andCodeEqualTo(code)
                                    .example()
                    )
            );

            if (flumeConfigOptional.isPresent()) {
                flumeConfig = flumeConfigOptional.get();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        return flumeConfig;
    }

    /**
     * 添加FlumeConfig的Dao操作
     */
    private int addFlumeCore(FlumeConfigDto flumeConfigDto, Long contextId) {
        return flumeConfigMapper.insertSelective(FlumeConfig
                        .builder()
                        .contextId(contextId)
                        .name(flumeConfigDto.getName())
                        .code(flumeConfigDto.getCode())
                        .flumeHome(flumeConfigDto.getFlumeHome())
                        .profileName(flumeConfigDto.getProfileName())
                        .agentName(flumeConfigDto.getAgentName())
                        .serverIp(flumeConfigDto.getServerIp())
                        .serverPort(flumeConfigDto.getServerPort())
                        .fileConfigContent(flumeConfigDto.getFileConfigContent())
                        .flumeConfigType(flumeConfigDto.getFlumeConfigType())
                        .remark(flumeConfigDto.getRemark())
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .build()
                , FlumeConfig.Column.contextId
                , FlumeConfig.Column.name
                , FlumeConfig.Column.code
                , FlumeConfig.Column.flumeHome
                , FlumeConfig.Column.profileName
                , FlumeConfig.Column.agentName
                , FlumeConfig.Column.serverIp
                , FlumeConfig.Column.serverPort
                , FlumeConfig.Column.fileConfigContent
                , FlumeConfig.Column.flumeConfigType
                , FlumeConfig.Column.remark
                , FlumeConfig.Column.createTime
                , FlumeConfig.Column.modifyTime
        );
    }

    /**
     * 修改FlumeConfig的Dao操作
     */
    private int updateFlumeCore(FlumeConfigDto flumeConfigDto) {
        return flumeConfigMapper.updateByExampleSelective(FlumeConfig
                        .builder()
                        .flumeHome(flumeConfigDto.getFlumeHome())
                        .profileName(flumeConfigDto.getProfileName())
                        .agentName(flumeConfigDto.getAgentName())
                        .serverIp(flumeConfigDto.getServerIp())
                        .serverPort(flumeConfigDto.getServerPort())
                        .fileConfigContent(flumeConfigDto.getFileConfigContent())
                        .flumeConfigType(flumeConfigDto.getFlumeConfigType())
                        .remark(flumeConfigDto.getRemark())
                        .modifyTime(new Date())
                        .build(),
                FlumeConfigExample.newAndCreateCriteria()
                        .andContextIdEqualTo(flumeConfigDto.getContextId()).example()
                , FlumeConfig.Column.flumeHome
                , FlumeConfig.Column.profileName
                , FlumeConfig.Column.agentName
                , FlumeConfig.Column.serverIp
                , FlumeConfig.Column.serverPort
                , FlumeConfig.Column.fileConfigContent
                , FlumeConfig.Column.flumeConfigType
                , FlumeConfig.Column.remark
                , FlumeConfig.Column.modifyTime
        );
    }

    /**
     * 跳转修改页面前处理
     */
    @Override
    public FlumeConfigDto updateFlumeConfigBefore(Long contextId) {
        FlumeConfigDto flumeConfigDto = null;
        Optional<FlumeConfig> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectOneByExampleWithBLOBs(
                            FlumeConfigExample.newAndCreateCriteria().andContextIdEqualTo(contextId).example())
            );

            if (flumeConfigOptional.isPresent()) {
                FlumeConfig flumeConfig = flumeConfigOptional.get();
                flumeConfigDto = FlumeConfigDto.builder()
                        .contextId(flumeConfig.getContextId())
                        .name(flumeConfig.getName())
                        .code(flumeConfig.getCode())
                        .flumeHome(flumeConfig.getFlumeHome())
                        .profileName(flumeConfig.getProfileName())
                        .agentName(flumeConfig.getAgentName())
                        .serverIp(flumeConfig.getServerIp())
                        .serverPort(flumeConfig.getServerPort())
                        .fileConfigContent(flumeConfig.getFileConfigContent())
                        .flumeConfigType(flumeConfig.getFlumeConfigType())
                        .remark(flumeConfig.getRemark())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return flumeConfigDto;
    }

    /**
     * 添加FlumeConfig业务
     */
    @Override
    public Boolean addOrUpdateFlume(String data) {
        Boolean falg = false;

        try {
            FlumeConfigDto flumeConfigDto = JSONObject.parseObject(data, FlumeConfigDto.class);
            if (flumeConfigDto.getContextId() == null) {
                FlumeConfig flumeConfig = this.getFlumeConfigByCode(flumeConfigDto.getCode());
                if (flumeConfig != null) {
                    throw new CustomException("code : " + flumeConfigDto.getCode() + " Existing");
                }
                Long contextId = OrderContextIdFactory.getRandom();
                if (this.addFlumeCore(flumeConfigDto, contextId) > 0) {
                    falg = true;
                }
            } else {//修改
                FlumeConfig flumeConfig = this.getFlumeConfigByCode(flumeConfigDto.getCode());
                if (flumeConfig != null && !flumeConfig.getContextId().equals(flumeConfigDto.getContextId())) {
                    throw new CustomException("code : " + flumeConfigDto.getCode() + " Used");
                }
                if (this.updateFlumeCore(flumeConfigDto) > 0) {
                    falg = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }

        return falg;
    }

    @Override
    public Boolean deleteFlumeConfig(String data) {
        Boolean falg = false;

        try {
            List<Long> contextList = JSONObject.parseArray(data, Long.class);

            if (contextList.size() > 0) {
                Optional<List<FlumeConfig>> flumeConfigOptional = Optional.ofNullable(
                        flumeConfigMapper.selectByExample(
                                FlumeConfigExample.newAndCreateCriteria()
                                        .andContextIdIn(contextList)
                                        .example()
                        )
                );

                if (flumeConfigOptional.isPresent()) {
                    List<FlumeConfig> flumeConfigList = flumeConfigOptional.get();


                    if (flumeConfigList.size() > 0) {
                        flumeConfigList.forEach(flumeConfig -> {
                            if (!FlumeConfig.Runstate.UNOPENED.getValue().equals(flumeConfig.getRunstate())
                                    && !FlumeConfig.Runstate.FAIL.getValue().equals(flumeConfig.getRunstate())
                            ) {
                                throw new CustomException("Some data states do not meet deletion requirements");
                            }
                        });

                        if (flumeConfigMapper.deleteByExample(
                                FlumeConfigExample.newAndCreateCriteria()
                                        .andContextIdIn(contextList)
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


    /**
     * 启动flume
     */
    @Override
    public Map<String, Object> startFlume(String data) {
        Map<String, Object> map = new HashMap<String, Object>();

        Long contextId = Long.valueOf(data);

        try {
            if (contextId != null) {

                if (this.pushCheckFlumeConfigByContextId(contextId)) {
                    FlumeConfig flumeConfig = this.selectOneFlumeConfig(contextId);
                    if (flumeConfig != null) {

                        String urlSuffix = "/agent/startFlume";
                        map = this.startFlumeComm(flumeConfig, urlSuffix, FlumeConfig.Runstate.PUSHING.getValue());

                    } else {
                        throw new CustomException("未查询到ID:" + contextId + "的任务!!", HttpCode.CODE_400);
                    }
                } else {
                    throw new CustomException("状态不符开启要求，请刷新页面！");
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            this.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.FAIL.getValue());
            e.printStackTrace();
            log.error("Start Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return map;
    }

    private Map<String, Object> startFlumeComm(FlumeConfig flumeConfig, String urlSuffix, Integer runstate) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        StringBuffer url = new StringBuffer();
        //生成请求flume监控端口的url
        url.append("http://").append(flumeConfig.getServerIp()).append(":").append(flumeConfig.getServerPort())
                .append(urlSuffix);
        Map<String, String> params = new HashMap<String, String>();
        params.put("contextId", String.valueOf(flumeConfig.getContextId().longValue()));

        //修改状态为：开始推送
        flumeConfig.setRunstate(runstate);
        flumeConfig.setModifyTime(new Date());

        if (flumeConfigMapper.updateByExampleSelective(flumeConfig,
                FlumeConfigExample.newAndCreateCriteria()
                        .andContextIdEqualTo(flumeConfig.getContextId())
                        .example(), FlumeConfig.Column.runstate, FlumeConfig.Column.modifyTime) > 0) {
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
    public Map<String, Object> restartFlume(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long contextId = Long.valueOf(data);
        try {
            if (contextId != null) {

                FlumeConfig flumeConfig = this.selectOneFlumeConfig(contextId);
                if (flumeConfig != null) {
                    String urlSuffix = "/agent/restartFlume";

                    flumeMonitorService.updateMonitorInfoToClose(contextId);
                    map = this.startFlumeComm(flumeConfig, urlSuffix, FlumeConfig.Runstate.RESTART.getValue());

                } else {
                    throw new CustomException("未查询到ID:" + contextId + "的任务!!", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            this.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.FAIL.getValue());
            e.printStackTrace();
            log.error("Start Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return map;
    }

    /**
     * 停止flume
     */
    @Override
    public Map<String, Object> stopFlume(String data) {
        Map<String, Object> map = new HashMap<String, Object>();

        Long contextId = Long.valueOf(data);
        try {
            if (contextId != null) {
                FlumeConfig flumeConfig = this.selectOneFlumeConfig(contextId);

                if (flumeConfig != null) {
                    synchronized (this) {
                        if (FlumeConfig.Runstate.RUNING.getValue().equals(flumeConfig.getRunstate())
                                || FlumeConfig.Runstate.PORTMONITORINGEXCEPTION.getValue().equals(flumeConfig.getRunstate())
                        ) {
                            StringBuffer url = new StringBuffer();
                            //生成请求flume监控端口的url
                            url.append("http://").append(flumeConfig.getServerIp()).append(":").append(flumeConfig.getServerPort())
                                    .append("/agent/stopFlume");
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("contextId", String.valueOf(flumeConfig.getContextId().longValue()));

                            //发送远程请求
                            map = CommHttpReq.agentRequest(url.toString(), params);
                            if (HttpCode.CODE_200.toValue() == (int) map.get("code")) {
                                //修改监控内容“关闭”（如果之前被修改成“端口异常”，那这里也就修改不到）
                                flumeMonitorService.updateMonitorInfoToClose(contextId);
                            }
                        } else {
                            throw new CustomException("状态不符关闭要求，请刷新页面！");
                        }
                    }
                } else {
                    throw new CustomException("未查询到ID:" + contextId + "的任务!!", HttpCode.CODE_400);
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


    /**
     * 查看运行信息前判断
     *
     * @param data
     * @return
     */
    @Override
    public Boolean viewRunInforBefore(String data) {
        Boolean falg = false;

        Long contextId = Long.valueOf(data);
        try {
            if (contextId != null) {
                FlumeConfig flumeConfig = this.selectOneFlumeConfig(contextId);

                if (flumeConfig != null) {

                    if (FlumeConfig.Runstate.RUNING.getValue().equals(flumeConfig.getRunstate())) {
                        String serverIp = flumeConfig.getServerIp();
                        Integer monitorPort = flumeConfig.getMonitorPort();

                        StringBuffer url = new StringBuffer();
                        //生成请求flume监控端口的url
                        url.append("http://").append(serverIp).append(":").append(monitorPort).append("/metrics");

                        String content = flumeMomitorRequest(url.toString(), 3);
                        if (!StringUtils.isEmpty(content)) {

                            flumeConfig.setFlumeMonitorContent(content);
                            flumeConfig.setModifyTime(new Date());

                            if (flumeConfigMapper.updateByExampleSelective(flumeConfig,
                                    FlumeConfigExample.newAndCreateCriteria()
                                            .andContextIdEqualTo(flumeConfig.getContextId())
                                            .example(), FlumeConfig.Column.flumeMonitorContent, FlumeConfig.Column.modifyTime) > 0) {

                                falg = true;
                            } else {
                                throw new CustomException("Data Update Failure", HttpCode.CODE_500);
                            }
                        } else {
                            throw new CustomException("未请求到数据，请稍后再试！", HttpCode.CODE_500);
                        }
                    } else {
                        throw new CustomException("已非运行状态不能让查看，请刷新页面！");
                    }
                } else {
                    throw new CustomException("未查询到ID:" + contextId + "的任务!!", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    /**
     * 看运行信息
     *
     * @param contextId
     * @return
     */
    @Override
    public FlumeConfigDto viewRunInfor(Long contextId) {
        FlumeConfigDto flumeConfigDto = new FlumeConfigDto();

        FlumeConfig flumeConfig = this.selectOneFlumeConfig(contextId);

        if (flumeConfig != null) {
            flumeConfigDto = FlumeConfigDto.builder()
                    .contextId(flumeConfig.getContextId())
                    .monitorPort(flumeConfig.getMonitorPort())
                    .processPid(flumeConfig.getProcessPid())
                    .serverIp(flumeConfig.getServerIp())
                    .serverPort(flumeConfig.getServerPort())
                    .flumeMonitorContent(flumeConfig.getFlumeMonitorContent())
                    .build();
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return flumeConfigDto;
    }

    /**
     * 请求flume监控内容的url
     */
    private String flumeMomitorRequest(String url, int renum) {
        String content = null;
        try {
            HttpClientResult httpClientResult = HttpClientUtil.doGet(url);
            content = httpClientResult.getContent();
        } catch (Exception e) {
            if (renum > 0) {
                content = flumeMomitorRequest(url, --renum);
            }
        }
        return content;
    }


    /**
     * 根据指定“任务类型”获取实例集合
     *
     * @param type
     * @return
     */
    public List<FlumeConfigDto> getFlumeConfigByType(int type) {
        List<FlumeConfigDto> list = new ArrayList<FlumeConfigDto>();

        Optional<List<FlumeConfig>> flumeConfigOptional = null;
        try {
            List<FlumeConfig> flumeConfigs = new ArrayList<>();

            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andFlumeConfigTypeEqualTo(type)
                                    .example()
                    )
            );

            if (flumeConfigOptional.isPresent()) {
                flumeConfigs = flumeConfigOptional.get();
                if (flumeConfigs.size() > 0) {
                    flumeConfigs.forEach(flumeConfig -> {
                        FlumeConfigDto flumeConfigDto = FlumeConfigDto.builder()
                                .contextId(flumeConfig.getContextId())
                                .name(flumeConfig.getName())
                                .build();
                        list.add(flumeConfigDto);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return list;
    }

    public List<Long> getContextIdsByType(int type) {
        List<Long> list = new ArrayList<Long>();

        Optional<List<FlumeConfig>> flumeConfigOptional = null;
        try {
            List<FlumeConfig> flumeConfigs = new ArrayList<>();

            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andFlumeConfigTypeEqualTo(type)
                                    .example()
                    )
            );

            if (flumeConfigOptional.isPresent()) {
                flumeConfigs = flumeConfigOptional.get();
                if (flumeConfigs.size() > 0) {
                    flumeConfigs.forEach(flumeConfig -> {
                        list.add(flumeConfig.getContextId());
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return list;
    }


    public List<FlumeConfig> getFlumeConfigByTypeAndRunState (int type, int runState) {
        List<FlumeConfig> flumeConfigs = new ArrayList<>();

        Optional<List<FlumeConfig>> flumeConfigOptional = null;
        try {
            flumeConfigOptional = Optional.ofNullable(
                    flumeConfigMapper.selectByExample(
                            FlumeConfigExample.newAndCreateCriteria()
                                    .andFlumeConfigTypeEqualTo(type)
                                    .andRunstateEqualTo(runState)
                                    .example()
                    )
            );
            if (flumeConfigOptional.isPresent()) {
                flumeConfigs = flumeConfigOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return flumeConfigs;
    }

}
