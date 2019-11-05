package com.qmtec.servicecore.service.flume.analytical;


import com.alibaba.fastjson.JSONObject;
import com.qmtec.servicecore.comm.FlumeAnalyticalComm;
import com.qmtec.servicecore.dao.ChannelInfoMapper;
import com.qmtec.servicecore.dao.FlumeRelationChannelMapper;
import com.qmtec.servicecore.dao.FlumeRelationSinkMapper;
import com.qmtec.servicecore.entity.ChannelInfo;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.FlumeRelationChannel;
import com.qmtec.servicecore.entity.example.ChannelInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class ChannelAnalyticalSerive {

    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    @Autowired
    private FlumeRelationChannelMapper flumeRelationChannelMapper;



    private void memoryCore(ChannelInfo channelInfo,FlumeAnalyticalComm channel,FlumeConfig flumeConfig){
        channelInfo.setComponentName(channel.getAssemblyName());
        channelInfo.setIpAddr(flumeConfig.getServerIp());

        String coreContent = channel.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);

        channelInfo.setChannelCapacity((String) jsonObject.get("ChannelCapacity"));
        channelInfo.setPercentage((String) jsonObject.get("ChannelFillPercentage"));
        channelInfo.setChannelSize((String) jsonObject.get("ChannelSize"));
        channelInfo.setTakeSuccessCount((String) jsonObject.get("EventTakeSuccessCount"));
        channelInfo.setTakeAttemptCount((String) jsonObject.get("EventTakeAttemptCount"));
        channelInfo.setPutAttemptCount((String) jsonObject.get("EventPutAttemptCount"));
        channelInfo.setPutSuccessCount((String) jsonObject.get("EventPutSuccessCount"));
        channelInfo.setStartTime(new Date(Long.valueOf((String)jsonObject.get("StartTime"))));

        channelInfo.setModifyTime(new Date());
        channelInfo.setDeleteFlag(0);
        channelInfo.setRunState(ChannelInfo.RunState.RUNING.getValue());
    }


    public void memory(FlumeConfig flumeConfig, FlumeAnalyticalComm channel) {
        ChannelInfo channelInfo = null;

        if (StringUtils.isEmpty(channel.getId())) {
            channelInfo = new ChannelInfo();
            String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
            channelInfo.setId(id);
            channelInfo.setCreateTime(new Date());
            this.memoryCore(channelInfo,channel,flumeConfig);
            channelInfoMapper.insert(channelInfo);
            flumeRelationChannelMapper.insert(FlumeRelationChannel
                    .builder()
                    .contextId(flumeConfig.getContextId())
                    .id(id)
                    .build());
        } else {
            channelInfo = channelInfoMapper.selectOneByExample(ChannelInfoExample.newAndCreateCriteria().andIdEqualTo(channel.getId()).example());
            this.memoryCore(channelInfo,channel,flumeConfig);
            channelInfoMapper.updateByExample(channelInfo,ChannelInfoExample.newAndCreateCriteria().andIdEqualTo(channel.getId()).example());
        }
    }

}
