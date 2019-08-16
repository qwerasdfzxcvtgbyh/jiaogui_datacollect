package com.qmtec.servicecore.service.flumeMonitor.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.FlumeRelationSinkMapper;
import com.qmtec.servicecore.dao.SinkInfoMapper;
import com.qmtec.servicecore.entity.FlumeRelationSink;
import com.qmtec.servicecore.entity.SinkInfo;
import com.qmtec.servicecore.entity.example.FlumeRelationSinkExample;
import com.qmtec.servicecore.entity.example.SinkInfoExample;
import com.qmtec.servicecore.model.dto.SinkInfoDto;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flumeMonitor.SinkInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class SinkInfoServiceImpl implements SinkInfoService {

    @Autowired
    private SinkInfoMapper sinkInfoMapper;
    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private FlumeRelationSinkMapper flumeRelationSinkMapper;

    private List<String> getSinkIdByContextId(int type,Long contextId) {
        List<String> list = new ArrayList<String>();

        List<Long> contextIdList = new ArrayList<Long>();
        if(contextId != null){
            contextIdList.add(contextId);
        }else{
            contextIdList = flumeConfigService.getContextIdsByType(type);
        }
        if (contextIdList.size() > 0) {

            Optional<List<FlumeRelationSink>> optionalFlumeRelationSinks = null;
            try {
                List<FlumeRelationSink> flumeRelationSinks = new ArrayList<>();

                optionalFlumeRelationSinks = Optional.ofNullable(
                        flumeRelationSinkMapper.selectByExample(
                                FlumeRelationSinkExample.newAndCreateCriteria()
                                        .andContextIdIn(contextIdList)
                                        .example()
                        )
                );
                if (optionalFlumeRelationSinks.isPresent()) {
                    flumeRelationSinks = optionalFlumeRelationSinks.get();
                    if (flumeRelationSinks.size() > 0) {
                        flumeRelationSinks.forEach(flumeRelationSink -> {
                            list.add(flumeRelationSink.getId());
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
                throw new CustomException("Data Query Failure", HttpCode.CODE_500);
            }
        }
        return list;
    }

    @Override
    public ListResult<SinkInfoDto> listSelectSinkInfo(String searchParams, int page, int limit,int type) {

        ListResult<SinkInfoDto> page1 = new ListResult<SinkInfoDto>();

        Long contextId = null;
        int dataType = 1;

        if(searchParams != null){
            Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

            if (!StringUtils.isEmpty((String) paramMap.get("contextId"))) {
                contextId = Long.valueOf((String) paramMap.get("contextId"));
            }

            if(!StringUtils.isEmpty((String)paramMap.get("dataType"))){
                dataType = Integer.valueOf((String) paramMap.get("dataType")).intValue();
            }
        }


        List<String> sinkIds = this.getSinkIdByContextId(type,contextId);

        if (sinkIds.size() > 0) {
            Optional<List<SinkInfo>> optionalSinkInfoList = null;

            SinkInfoExample.Criteria criteria = SinkInfoExample.newAndCreateCriteria();
            criteria.andIdIn(sinkIds);

            if(dataType == 1){
                criteria.andRunStateEqualTo(SinkInfo.RunState.RUNING.getValue());
            }else{
                criteria.andRunStateNotEqualTo(SinkInfo.RunState.RUNING.getValue());
            }

            SinkInfoExample sinkInfoExample = criteria.example();
            sinkInfoExample.page((page - 1), limit);
            sinkInfoExample.setOrderByClause("create_time desc");

            optionalSinkInfoList = Optional.ofNullable(sinkInfoMapper.selectByExample(sinkInfoExample));
            if (optionalSinkInfoList.isPresent()) {

                List<SinkInfo> sinkInfoList = optionalSinkInfoList.get();

                if (sinkInfoList.size() > 0) {
                    long count = sinkInfoMapper.countByExample(criteria.example());
                    page1.setTotal(count);

                    List<SinkInfoDto> sinkInfoDtos = new ArrayList<SinkInfoDto>();

                    if(FlumeTaskType.MysqlToKafka.value().intValue() == type){
                        sinkInfoDtos = this.mysqlToKafkaViewDto(sinkInfoList,sinkInfoDtos);
                    }

                    page1.setResult(sinkInfoDtos);
                } else {
                    page1.setMessage("no data");
                }
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        }
        return page1;
    }

    private List<SinkInfoDto> mysqlToKafkaViewDto(List<SinkInfo> sinkInfoList,List<SinkInfoDto> sinkInfoDtos){
        sinkInfoList.forEach(sinkInfo -> {
            SinkInfoDto sinkInfoDto = SinkInfoDto.builder()
                    .id(sinkInfo.getId())
                    .componentName(sinkInfo.getComponentName())
                    .ipAddr(sinkInfo.getIpAddr())

                    .batchCompleteCount(sinkInfo.getBatchCompleteCount())
                    .batchEmptyCount(sinkInfo.getBatchEmptyCount())
                    .batchUnderflowCount(sinkInfo.getBatchUnderflowCount())
                    .connectionClosedCount(sinkInfo.getConnectionClosedCount())
                    .connectionCreatedCount(sinkInfo.getConnectionCreatedCount())
                    .connectionFailedCount(sinkInfo.getConnectionFailedCount())
                    .eventDrainattemptCount(sinkInfo.getEventDrainattemptCount())
                    .eventDrainSuccessCount(sinkInfo.getEventDrainSuccessCount())
                    .kafkaEventSendtimer(sinkInfo.getKafkaEventSendtimer())
                    .rollbackCount(sinkInfo.getRollbackCount())
                    .startTime(sinkInfo.getStartTime())

                    .runState(sinkInfo.getRunState())
                    .createTime(sinkInfo.getCreateTime())
                    .build();

            SinkInfo.RunState[] runstates = SinkInfo.RunState.values();
            for (SinkInfo.RunState runstate : runstates) {
                if (runstate.getValue().equals(sinkInfo.getRunState())) {
                    sinkInfoDto.setRunStateName(runstate.getName());
                    break;
                }
            }
            sinkInfoDtos.add(sinkInfoDto);
        });
        return sinkInfoDtos;
    }


    /**
     * 根据contextId获取正在运行的
     *
     * @param contextId
     */
    public SinkInfo getRunSinkByContextId(Long contextId) {
        SinkInfo sinkInfo = null;

        Optional<List<FlumeRelationSink>> optionalFlumeRelationSinks = null;
        try {
            List<FlumeRelationSink> flumeRelationSinks = new ArrayList<FlumeRelationSink>();
            optionalFlumeRelationSinks = Optional.ofNullable(
                    flumeRelationSinkMapper.selectByExample(
                            FlumeRelationSinkExample.newAndCreateCriteria()
                                    .andContextIdEqualTo(contextId)
                                    .example()
                    )
            );

            List<String> list = new ArrayList<String>();
            if (optionalFlumeRelationSinks.isPresent()) {
                flumeRelationSinks = optionalFlumeRelationSinks.get();
                if (flumeRelationSinks.size() > 0) {
                    flumeRelationSinks.forEach(flumeRelationSink -> {
                        list.add(flumeRelationSink.getId());
                    });

                    sinkInfo = sinkInfoMapper.selectOneByExample(
                            SinkInfoExample.newAndCreateCriteria()
                                    .andIdIn(list)
                                    .andRunStateEqualTo(SinkInfo.RunState.RUNING.getValue())
                                    .example());

                } else {
                    log.warn("contextId ：" + contextId + "还未添加过chanel监控信息");
                   //throw new CustomException("Data Query Failure", HttpCode.CODE_500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return sinkInfo;
    }

    public void updateSinkStateByContextId(Long contextId,Integer runstate) {

        SinkInfo sinkInfo  =  this.getRunSinkByContextId(contextId);
        if(sinkInfo != null){
            sinkInfo.setRunState(runstate);
            sinkInfo.setModifyTime(new Date());

            sinkInfoMapper.updateByPrimaryKey(sinkInfo);
        }
    }
}
