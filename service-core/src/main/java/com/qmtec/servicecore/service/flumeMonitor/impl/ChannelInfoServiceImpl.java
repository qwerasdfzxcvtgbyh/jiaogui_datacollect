package com.qmtec.servicecore.service.flumeMonitor.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.ChannelInfoMapper;
import com.qmtec.servicecore.dao.FlumeRelationChannelMapper;
import com.qmtec.servicecore.entity.ChannelInfo;
import com.qmtec.servicecore.entity.FlumeRelationChannel;
import com.qmtec.servicecore.entity.example.ChannelInfoExample;
import com.qmtec.servicecore.entity.example.FlumeRelationChannelExample;
import com.qmtec.servicecore.model.dto.ChannelInfoDto;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flumeMonitor.ChannleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class ChannelInfoServiceImpl implements ChannleInfoService {

    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private FlumeRelationChannelMapper flumeRelationChannelMapper;

    private List<String> getChaannelIdByContextId(int type, Long contextId) {
        List<String> list = new ArrayList<String>();

        List<Long> contextIdList = new ArrayList<Long>();
        if (contextId != null) {
            contextIdList.add(contextId);
        } else {
            contextIdList = flumeConfigService.getContextIdsByType(type);
        }


        if (contextIdList.size() > 0) {
            Optional<List<FlumeRelationChannel>> optionalFlumeRelationChannels = null;
            try {
                List<FlumeRelationChannel> flumeRelationChannels = new ArrayList<>();
                optionalFlumeRelationChannels = Optional.ofNullable(
                        flumeRelationChannelMapper.selectByExample(
                                FlumeRelationChannelExample.newAndCreateCriteria()
                                        .andContextIdIn(contextIdList)
                                        .example()
                        )
                );
                if (optionalFlumeRelationChannels.isPresent()) {
                    flumeRelationChannels = optionalFlumeRelationChannels.get();
                    if (flumeRelationChannels.size() > 0) {
                        flumeRelationChannels.forEach(flumeRelationSources1 -> {
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

    @Override
    public ListResult<ChannelInfoDto> listSelectChannelInfo(String searchParams, int page, int limit, int type) {
        ListResult<ChannelInfoDto> page1 = new ListResult<ChannelInfoDto>();

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

        List<String> channelIds = this.getChaannelIdByContextId(type, contextId);

        if (channelIds.size() > 0) {

            Optional<List<ChannelInfo>> optionalChannelInfos = null;

            ChannelInfoExample.Criteria criteria = ChannelInfoExample.newAndCreateCriteria();
            criteria.andIdIn(channelIds);

            if (dataType == 1) {
                criteria.andRunStateEqualTo(ChannelInfo.RunState.RUNING.getValue());
            } else {
                criteria.andRunStateNotEqualTo(ChannelInfo.RunState.RUNING.getValue());
            }

            ChannelInfoExample channelInfoExample = criteria.example();
            channelInfoExample.page((page - 1), limit);
            channelInfoExample.setOrderByClause("create_time desc");

            optionalChannelInfos = Optional.ofNullable(channelInfoMapper.selectByExample(channelInfoExample));
            if (optionalChannelInfos.isPresent()) {

                List<ChannelInfo> channelInfoList = optionalChannelInfos.get();

                if (channelInfoList.size() > 0) {
                    long count = channelInfoMapper.countByExample(criteria.example());
                    page1.setTotal(count);

                    List<ChannelInfoDto> channelInfoDtos = new ArrayList<ChannelInfoDto>();

                    if(FlumeTaskType.MysqlToKafka.value().intValue() == type){
                        channelInfoDtos = this.mysqlToKafkaViewDto(channelInfoList, channelInfoDtos);
                    }

                    page1.setResult(channelInfoDtos);
                } else {
                    page1.setMessage("no data");
                }
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        }
        return page1;
    }

    private List<ChannelInfoDto> mysqlToKafkaViewDto(List<ChannelInfo> channelInfoList, List<ChannelInfoDto> channelInfoDtos) {
        channelInfoList.forEach(channelInfo -> {
            ChannelInfoDto channelInfoDto = ChannelInfoDto.builder()
                    .id(channelInfo.getId())
                    .componentName(channelInfo.getComponentName())
                    .ipAddr(channelInfo.getIpAddr())
                    .channelCapacity(channelInfo.getChannelCapacity())
                    .channelSize(channelInfo.getChannelSize())
                    .percentage(channelInfo.getPercentage())
                    .putAttemptCount(channelInfo.getPutAttemptCount())
                    .putSuccessCount(channelInfo.getPutSuccessCount())
                    .takeAttemptCount(channelInfo.getTakeAttemptCount())
                    .takeSuccessCount(channelInfo.getTakeSuccessCount())
                    .startTime(channelInfo.getStartTime())
                    .runState(channelInfo.getRunState())
                    .createTime(channelInfo.getCreateTime())
                    .build();

            ChannelInfo.RunState[] runstates = ChannelInfo.RunState.values();
            for (ChannelInfo.RunState runstate : runstates) {
                if (runstate.getValue().equals(channelInfo.getRunState())) {
                    channelInfoDto.setRunStateName(runstate.getName());
                    break;
                }
            }
            channelInfoDtos.add(channelInfoDto);
        });
        return channelInfoDtos;
    }


    /**
     * 根据contextId获取正在运行的
     *
     * @param contextId
     */
    public ChannelInfo getRunChannelByContextId(Long contextId) {
        ChannelInfo channelInfo = null;

        Optional<List<FlumeRelationChannel>> optionalFlumeRelationChannels = null;
        try {
            List<FlumeRelationChannel> flumeRelationChannels = new ArrayList<FlumeRelationChannel>();
            optionalFlumeRelationChannels = Optional.ofNullable(
                    flumeRelationChannelMapper.selectByExample(
                            FlumeRelationChannelExample.newAndCreateCriteria()
                                    .andContextIdEqualTo(contextId)
                                    .example()
                    )
            );

            List<String> list = new ArrayList<String>();
            if (optionalFlumeRelationChannels.isPresent()) {
                flumeRelationChannels = optionalFlumeRelationChannels.get();
                if (flumeRelationChannels.size() > 0) {
                    flumeRelationChannels.forEach(flumeRelationChannel -> {
                        list.add(flumeRelationChannel.getId());
                    });

                    channelInfo = channelInfoMapper.selectOneByExample(
                            ChannelInfoExample.newAndCreateCriteria()
                                    .andIdIn(list)
                                    .andRunStateEqualTo(ChannelInfo.RunState.RUNING.getValue())
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
        return channelInfo;
    }

    public void updateChanelStateByContextId(Long contextId, Integer runstate) {

        ChannelInfo channelInfo = this.getRunChannelByContextId(contextId);
        if (channelInfo != null) {
            channelInfo.setRunState(runstate);
            channelInfo.setModifyTime(new Date());

            channelInfoMapper.updateByPrimaryKey(channelInfo);
        }
    }

}
