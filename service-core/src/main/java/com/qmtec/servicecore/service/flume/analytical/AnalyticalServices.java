package com.qmtec.servicecore.service.flume.analytical;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.servicecore.comm.FlumeAnalyticalComm;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.dao.FlumeConfigMapper;
import com.qmtec.servicecore.dao.FlumeRelationChannelMapper;
import com.qmtec.servicecore.dao.FlumeRelationSinkMapper;
import com.qmtec.servicecore.dao.FlumeRelationSourcesMapper;
import com.qmtec.servicecore.entity.*;
import com.qmtec.servicecore.entity.example.FlumeConfigExample;
import com.qmtec.servicecore.entity.example.FlumeRelationChannelExample;
import com.qmtec.servicecore.entity.example.FlumeRelationSinkExample;
import com.qmtec.servicecore.entity.example.FlumeRelationSourcesExample;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flumeMonitor.ChannleInfoService;
import com.qmtec.servicecore.service.flumeMonitor.SinkInfoService;
import com.qmtec.servicecore.service.flumeMonitor.SourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Service
public class AnalyticalServices {

    @Autowired
    private SourceAnalyticalSerive sourceAnalyticalSerive;
    @Autowired
    private ChannelAnalyticalSerive channelAnalyticalSerive;
    @Autowired
    private SinkAnalyticalSerive sinkAnalyticalSerive;

    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private SourceInfoService sourceInfoService;
    @Autowired
    private ChannleInfoService channleInfoService;
    @Autowired
    private SinkInfoService sinkInfoService;



    public void analyticalContent(Long contextId, String content) throws Exception {

        JSONObject jsonObject = JSONObject.parseObject(content);
        Iterator<String> iterator = jsonObject.keySet().iterator();

        FlumeAnalyticalComm sink = new FlumeAnalyticalComm();
        FlumeAnalyticalComm source = new FlumeAnalyticalComm();
        FlumeAnalyticalComm channel = new FlumeAnalyticalComm();

        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = jsonObject.getString(key);
            String name = key.substring(key.lastIndexOf(".")+1,key.length());
            String pref = key.substring(0, key.lastIndexOf("."));

            if (pref.contains("SOURCE")) {
                source.setAssemblyType(pref);
                source.setAssemblyName(name);
                source.setCoreContent(value);

                SourceInfo sourceInfo = sourceInfoService.getRunSoureceByContextId(contextId);
                if (sourceInfo != null) {
                    source.setId(sourceInfo.getId());
                } else {
                    log.info("当前任务ID :" + contextId + "未中间表查到对应的SOURCE");
                }

            }

            if (pref.contains("CHANNEL")) {
                channel.setAssemblyType(pref);
                channel.setAssemblyName(name);
                channel.setCoreContent(value);

                ChannelInfo channelInfo = channleInfoService.getRunChannelByContextId(contextId);
                if (channelInfo != null) {
                    channel.setId(channelInfo.getId());
                } else {
                    log.info("当前任务ID :" + contextId + "未中间表查到对应的CHANNEL");
                }

            }

            if (pref.contains("SINK")) {
                sink.setAssemblyType(pref);
                sink.setAssemblyName(name);
                sink.setCoreContent(value);

                SinkInfo sinkInfo = sinkInfoService.getRunSinkByContextId(contextId);
                if (sinkInfo != null) {
                    sink.setId(sinkInfo.getId());
                } else {
                    log.info("当前任务ID :" + contextId + "未中间表查到对应的SINK");
                }

            }
        }
        FlumeConfig flumeConfig = flumeConfigService.selectOneFlumeConfig(contextId);

        if (FlumeTaskType.MysqlToKafka.getValue().equals(flumeConfig.getFlumeConfigType())) {
            this.mysqlToKafka(flumeConfig, source, channel, sink);
        }

    }

    private void mysqlToKafka(FlumeConfig flumeConfig, FlumeAnalyticalComm source, FlumeAnalyticalComm channel, FlumeAnalyticalComm sink) {
        sourceAnalyticalSerive.mysql(flumeConfig, source);
        channelAnalyticalSerive.memory(flumeConfig, channel);
        sinkAnalyticalSerive.kafka(flumeConfig, sink);
    }

}
