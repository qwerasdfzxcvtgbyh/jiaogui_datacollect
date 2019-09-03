package com.qmtec.servicecore.service.flume.analytical;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.servicecore.comm.FlumeAnalyticalComm;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.FlumeRelationSinkMapper;
import com.qmtec.servicecore.dao.SinkInfoMapper;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.FlumeRelationSink;
import com.qmtec.servicecore.entity.SinkInfo;
import com.qmtec.servicecore.entity.example.SinkInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class SinkAnalyticalSerive {

    @Autowired
    private SinkInfoMapper sinkInfoMapper;
    @Autowired
    private FlumeRelationSinkMapper flumeRelationSinkMapper;

    private void comm(FlumeConfig flumeConfig, FlumeAnalyticalComm sink, SinkInfo sinkInfo) {
        sinkInfo.setComponentName(sink.getAssemblyName());
        sinkInfo.setIpAddr(flumeConfig.getServerIp());
        sinkInfo.setModifyTime(new Date());
        sinkInfo.setDeleteFlag(0);
        sinkInfo.setRunState(SinkInfo.RunState.RUNING.getValue());
    }

    private void kafkaCore(FlumeConfig flumeConfig, FlumeAnalyticalComm sink, SinkInfo sinkInfo) {
        this.comm(flumeConfig, sink, sinkInfo);
        String coreContent = sink.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);
        sinkInfo.setConnectionCreatedCount((String) jsonObject.get("ConnectionCreatedCount"));
        sinkInfo.setBatchCompleteCount((String) jsonObject.get("BatchCompleteCount"));
        sinkInfo.setBatchEmptyCount((String) jsonObject.get("BatchEmptyCount"));
        sinkInfo.setEventDrainattemptCount((String) jsonObject.get("EventDrainAttemptCount"));
        sinkInfo.setStartTime( new Date(Long.valueOf((String)jsonObject.get("StartTime"))));
        sinkInfo.setBatchUnderflowCount((String) jsonObject.get("BatchUnderflowCount"));
        sinkInfo.setConnectionFailedCount((String) jsonObject.get("ConnectionFailedCount"));
        sinkInfo.setConnectionClosedCount((String) jsonObject.get("ConnectionClosedCount"));
        sinkInfo.setRollbackCount((String) jsonObject.get("RollbackCount"));
        sinkInfo.setEventDrainSuccessCount((String) jsonObject.get("EventDrainSuccessCount"));
        sinkInfo.setKafkaEventSendtimer((String) jsonObject.get("KafkaEventSendTimer"));
    }

    private void hdfsCore(FlumeConfig flumeConfig, FlumeAnalyticalComm sink, SinkInfo sinkInfo) {
        this.comm(flumeConfig, sink, sinkInfo);
        String coreContent = sink.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);
        sinkInfo.setConnectionCreatedCount((String) jsonObject.get("ConnectionCreatedCount"));
        sinkInfo.setBatchCompleteCount((String) jsonObject.get("BatchCompleteCount"));
        sinkInfo.setBatchEmptyCount((String) jsonObject.get("BatchEmptyCount"));
        sinkInfo.setEventDrainattemptCount((String) jsonObject.get("EventDrainAttemptCount"));
        sinkInfo.setStartTime( new Date(Long.valueOf((String)jsonObject.get("StartTime"))));
        sinkInfo.setBatchUnderflowCount((String) jsonObject.get("BatchUnderflowCount"));
        sinkInfo.setConnectionFailedCount((String) jsonObject.get("ConnectionFailedCount"));
        sinkInfo.setConnectionClosedCount((String) jsonObject.get("ConnectionClosedCount"));
        sinkInfo.setEventDrainSuccessCount((String) jsonObject.get("EventDrainSuccessCount"));
        sinkInfo.setChannelReadFail((String) jsonObject.get("ChannelReadFail"));
        sinkInfo.setEventWriteFail((String) jsonObject.get("EventWriteFail"));
    }



    public void sinkAnalyticalByTaskType(FlumeConfig flumeConfig, FlumeAnalyticalComm sink) {
        SinkInfo sinkInfo = null;
        if (StringUtils.isEmpty(sink.getId())) {
            sinkInfo = new SinkInfo();
            String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
            sinkInfo.setId(id);
            sinkInfo.setCreateTime(new Date());
            if (FlumeTaskType.MysqlToKafka.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.kafkaCore(flumeConfig, sink, sinkInfo);
            } else if (FlumeTaskType.KafkaToHdfs.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.hdfsCore(flumeConfig, sink, sinkInfo);
            }
            sinkInfoMapper.insert(sinkInfo);
            flumeRelationSinkMapper.insert(FlumeRelationSink
                    .builder()
                    .contextId(flumeConfig.getContextId())
                    .id(id)
                    .build());
        } else {
            sinkInfo = sinkInfoMapper.selectOneByExample(SinkInfoExample.newAndCreateCriteria().andIdEqualTo(sink.getId()).example());
            if (FlumeTaskType.MysqlToKafka.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.kafkaCore(flumeConfig, sink, sinkInfo);
            } else if (FlumeTaskType.KafkaToHdfs.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.hdfsCore(flumeConfig, sink, sinkInfo);
            }
            sinkInfoMapper.updateByExample(sinkInfo, SinkInfoExample.newAndCreateCriteria().andIdEqualTo(sink.getId()).example());
        }
    }


}
