package com.qmtec.servicecore.service.flumeMonitor.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.FlumeRelationSourcesMapper;
import com.qmtec.servicecore.dao.SourceInfoMapper;
import com.qmtec.servicecore.entity.FlumeRelationSources;
import com.qmtec.servicecore.entity.SourceInfo;
import com.qmtec.servicecore.entity.example.FlumeRelationSourcesExample;
import com.qmtec.servicecore.entity.example.SourceInfoExample;
import com.qmtec.servicecore.model.dto.SourceInfoDto;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flumeMonitor.SourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class SourceInfoServiceImpl implements SourceInfoService {

    @Autowired
    private SourceInfoMapper sourceInfoMapper;
    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private FlumeRelationSourcesMapper flumeRelationSourcesMapper;

    private List<String> getSourceIdByContextId(int type, Long contextId) {
        List<String> list = new ArrayList<String>();

        List<Long> contextIdList = new ArrayList<Long>();
        if (contextId != null) {
            contextIdList.add(contextId);
        } else {
            contextIdList = flumeConfigService.getContextIdsByType(type);
        }

        if (contextIdList.size() > 0) {
            Optional<List<FlumeRelationSources>> optionalFlumeRelationSources = null;
            try {
                List<FlumeRelationSources> flumeRelationSources = new ArrayList<>();
                optionalFlumeRelationSources = Optional.ofNullable(
                        flumeRelationSourcesMapper.selectByExample(
                                FlumeRelationSourcesExample.newAndCreateCriteria()
                                        .andContextIdIn(contextIdList)
                                        .example()
                        )
                );
                if (optionalFlumeRelationSources.isPresent()) {
                    flumeRelationSources = optionalFlumeRelationSources.get();
                    if (flumeRelationSources.size() > 0) {
                        flumeRelationSources.forEach(flumeRelationSources1 -> {
                            list.add(flumeRelationSources1.getId());
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

    /**
     * @param page
     * @param limit
     * @param type
     * @return
     */
    @Override
    public ListResult<SourceInfoDto> listSelectSoureceInfo(String searchParams, int page, int limit, int type) {
        ListResult<SourceInfoDto> page1 = new ListResult<SourceInfoDto>();

        Long contextId = null;
        int dataType = 1;

        if (searchParams != null) {
            Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

            if (!StringUtils.isEmpty((String) paramMap.get("contextId"))) {
                contextId = Long.valueOf((String) paramMap.get("contextId"));
            }

            if (!StringUtils.isEmpty((String) paramMap.get("dataType"))) {
                dataType = Integer.valueOf((String) paramMap.get("dataType")).intValue();
            }
        }

        List<String> sourceIds = this.getSourceIdByContextId(type, contextId);

        if (sourceIds.size() > 0) {
            Optional<List<SourceInfo>> optionalSourceInfos = null;

            SourceInfoExample.Criteria criteria = SourceInfoExample.newAndCreateCriteria();
            criteria.andIdIn(sourceIds);

            if (dataType == 1) {
                criteria.andRunStateEqualTo(SourceInfo.RunState.RUNING.getValue());
            } else {
                criteria.andRunStateNotEqualTo(SourceInfo.RunState.RUNING.getValue());
            }

            SourceInfoExample sourceInfoExample = criteria.example();
            sourceInfoExample.page((page - 1), limit);
            sourceInfoExample.setOrderByClause("create_time desc");

            optionalSourceInfos = Optional.ofNullable(sourceInfoMapper.selectByExample(sourceInfoExample));
            if (optionalSourceInfos.isPresent()) {

                List<SourceInfo> sourceInfoList = optionalSourceInfos.get();

                if (sourceInfoList.size() > 0) {
                    long count = sourceInfoMapper.countByExample(criteria.example());
                    page1.setTotal(count);

                    List<SourceInfoDto> sourceInfoDtos = new ArrayList<SourceInfoDto>();

                    if (FlumeTaskType.MysqlToKafka.value().intValue() == type) {
                        sourceInfoDtos = this.mysqlToKafkaViewDto(sourceInfoList, sourceInfoDtos);
                    } else if (FlumeTaskType.KafkaToHdfs.value().intValue() == type) {
                        sourceInfoDtos = this.KafkaToHdfsViewDto(sourceInfoList, sourceInfoDtos);
                    }

                    page1.setResult(sourceInfoDtos);
                } else {
                    page1.setMessage("no data");
                }
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        }
        return page1;
    }

    private List<SourceInfoDto> mysqlToKafkaViewDto(List<SourceInfo> sourceInfoList, List<SourceInfoDto> sourceInfoDtos) {
        sourceInfoList.forEach(sourceInfo -> {
            SourceInfoDto sourceInfoDto = SourceInfoDto.builder()
                    .id(sourceInfo.getId())
                    .componentName(sourceInfo.getComponentName())
                    .averageThroughput(sourceInfo.getAverageThroughput())
                    .currentThroughput(sourceInfo.getCurrentThroughput())
                    .eventCount(sourceInfo.getEventCount())
                    .ipAddr(sourceInfo.getIpAddr())
                    .maxThroughput(sourceInfo.getMaxThroughput())
                    .runState(sourceInfo.getRunState())
                    .createTime(sourceInfo.getCreateTime())
                    .build();

            SourceInfo.RunState[] runstates = SourceInfo.RunState.values();
            for (SourceInfo.RunState runstate : runstates) {
                if (runstate.getValue().equals(sourceInfo.getRunState())) {
                    sourceInfoDto.setRunStateName(runstate.getName());
                    break;
                }
            }
            sourceInfoDtos.add(sourceInfoDto);
        });
        return sourceInfoDtos;
    }

    private List<SourceInfoDto> KafkaToHdfsViewDto(List<SourceInfo> sourceInfoList, List<SourceInfoDto> sourceInfoDtos) {
        sourceInfoList.forEach(sourceInfo -> {
            SourceInfoDto sourceInfoDto = SourceInfoDto.builder()
                    .id(sourceInfo.getId())
                    .componentName(sourceInfo.getComponentName())
                    .eventGetTimer(sourceInfo.getEventGetTimer())
                    .appendBatchAcceptedCount(sourceInfo.getAppendBatchAcceptedCount())
                    .eventAcceptedCount(sourceInfo.getEventAcceptedCount())
                    .appendReceivedCount(sourceInfo.getAppendReceivedCount())
                    .appendBatchReceivedCount(sourceInfo.getAppendBatchReceivedCount())
                    .kafkaCommitTimer(sourceInfo.getKafkaCommitTimer())
                    .eventReceivedCount(sourceInfo.getEventReceivedCount())
                    .appendAcceptedCount(sourceInfo.getAppendAcceptedCount())
                    .openConnectionCount(sourceInfo.getOpenConnectionCount())
                    .kafkaEmptyCount(sourceInfo.getKafkaEmptyCount())
                    .ipAddr(sourceInfo.getIpAddr())
                    .runState(sourceInfo.getRunState())
                    .createTime(sourceInfo.getCreateTime())
                    .build();

            SourceInfo.RunState[] runstates = SourceInfo.RunState.values();
            for (SourceInfo.RunState runstate : runstates) {
                if (runstate.getValue().equals(sourceInfo.getRunState())) {
                    sourceInfoDto.setRunStateName(runstate.getName());
                    break;
                }
            }
            sourceInfoDtos.add(sourceInfoDto);
        });
        return sourceInfoDtos;
    }

    /**
     * 根据contextId获取正在运行的
     *
     * @param contextId
     */
    public SourceInfo getRunSoureceByContextId(Long contextId) {
        SourceInfo sourceInfo = null;

        Optional<List<FlumeRelationSources>> optionalFlumeRelationSources = null;
        try {
            List<FlumeRelationSources> flumeRelationSources = new ArrayList<>();
            optionalFlumeRelationSources = Optional.ofNullable(
                    flumeRelationSourcesMapper.selectByExample(
                            FlumeRelationSourcesExample.newAndCreateCriteria()
                                    .andContextIdEqualTo(contextId)
                                    .example()
                    )
            );

            List<String> list = new ArrayList<String>();
            if (optionalFlumeRelationSources.isPresent()) {
                flumeRelationSources = optionalFlumeRelationSources.get();
                if (flumeRelationSources.size() > 0) {
                    flumeRelationSources.forEach(flumeRelationSources1 -> {
                        list.add(flumeRelationSources1.getId());
                    });
                    sourceInfo = sourceInfoMapper.selectOneByExample(
                            SourceInfoExample.newAndCreateCriteria()
                                    .andIdIn(list)
                                    .andRunStateEqualTo(SourceInfo.RunState.RUNING.getValue())
                                    .example());

                } else {
                    log.warn("contextId ：" + contextId + "还未添加过source监控信息");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }
        return sourceInfo;
    }

    public void updateSoureceStateByContextId(Long contextId, Integer runstate) {

        SourceInfo sourceInfo = this.getRunSoureceByContextId(contextId);
        if (sourceInfo != null) {
            sourceInfo.setRunState(runstate);
            sourceInfo.setModifyTime(new Date());

            sourceInfoMapper.updateByPrimaryKey(sourceInfo);
        }
    }


}
