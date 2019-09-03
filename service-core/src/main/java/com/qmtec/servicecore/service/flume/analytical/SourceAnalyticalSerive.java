package com.qmtec.servicecore.service.flume.analytical;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.servicecore.comm.FlumeAnalyticalComm;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.FlumeRelationSourcesMapper;
import com.qmtec.servicecore.dao.SourceInfoMapper;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.FlumeRelationSources;
import com.qmtec.servicecore.entity.SourceInfo;
import com.qmtec.servicecore.entity.example.SourceInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class SourceAnalyticalSerive {

    @Autowired
    private SourceInfoMapper sourceInfoMapper;
    @Autowired
    private FlumeRelationSourcesMapper flumeRelationSourcesMapper;

    private void Comm(SourceInfo sourceInfo, FlumeAnalyticalComm source, FlumeConfig flumeConfig) {
        sourceInfo.setComponentName(source.getAssemblyName());
        sourceInfo.setIpAddr(flumeConfig.getServerIp());
        sourceInfo.setModifyTime(new Date());
        sourceInfo.setDeleteFlag(0);
        sourceInfo.setRunState(SourceInfo.RunState.RUNING.getValue());
    }

    private void mysqlComm(SourceInfo sourceInfo, FlumeAnalyticalComm source, FlumeConfig flumeConfig) {
        this.Comm(sourceInfo, source, flumeConfig);
        String coreContent = source.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);

        sourceInfo.setCurrentThroughput((String) jsonObject.get("CurrentThroughput"));
        sourceInfo.setMaxThroughput((String) jsonObject.get("MaxThroughput"));
        sourceInfo.setEventCount((String) jsonObject.get("EventCount"));
        sourceInfo.setAverageThroughput((String) jsonObject.get("AverageThroughput"));
    }

    private void kafkaComm(SourceInfo sourceInfo, FlumeAnalyticalComm source, FlumeConfig flumeConfig) {
        this.Comm(sourceInfo, source, flumeConfig);
        String coreContent = source.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);

        sourceInfo.setEventGetTimer((String) jsonObject.get("KafkaEventGetTimer"));
        sourceInfo.setAppendBatchAcceptedCount((String) jsonObject.get("AppendBatchAcceptedCount"));
        sourceInfo.setEventAcceptedCount((String) jsonObject.get("EventAcceptedCount"));
        sourceInfo.setAppendReceivedCount((String) jsonObject.get("AppendReceivedCount"));
        sourceInfo.setAppendBatchReceivedCount((String) jsonObject.get("AppendBatchReceivedCount"));
        sourceInfo.setKafkaCommitTimer((String) jsonObject.get("KafkaCommitTimer"));
        sourceInfo.setEventReceivedCount((String) jsonObject.get("EventReceivedCount"));
        sourceInfo.setAppendAcceptedCount((String) jsonObject.get("AppendAcceptedCount"));
        sourceInfo.setOpenConnectionCount((String) jsonObject.get("OpenConnectionCount"));
        sourceInfo.setKafkaEmptyCount((String) jsonObject.get("KafkaEmptyCount"));
    }

    public void sourceAnalyticalByTaskType(FlumeConfig flumeConfig, FlumeAnalyticalComm source) {
        SourceInfo sourceInfo = null;
        if (StringUtils.isEmpty(source.getId())) {
            sourceInfo = new SourceInfo();
            String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
            sourceInfo.setId(id);
            sourceInfo.setCreateTime(new Date());
            if (FlumeTaskType.MysqlToKafka.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.mysqlComm(sourceInfo, source, flumeConfig);
            } else if (FlumeTaskType.KafkaToHdfs.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.kafkaComm(sourceInfo, source, flumeConfig);
            }
            sourceInfoMapper.insert(sourceInfo);
            flumeRelationSourcesMapper.insert(FlumeRelationSources
                    .builder()
                    .contextId(flumeConfig.getContextId())
                    .id(id)
                    .build());
        } else {
            sourceInfo = sourceInfoMapper.selectOneByExample(SourceInfoExample.newAndCreateCriteria().andIdEqualTo(source.getId()).example());
            if (FlumeTaskType.MysqlToKafka.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.mysqlComm(sourceInfo, source, flumeConfig);
            } else if (FlumeTaskType.KafkaToHdfs.getValue().equals(flumeConfig.getFlumeConfigType())) {
                this.kafkaComm(sourceInfo, source, flumeConfig);
            }
            sourceInfoMapper.updateByExample(sourceInfo, SourceInfoExample.newAndCreateCriteria().andIdEqualTo(source.getId()).example());
        }
    }


}
