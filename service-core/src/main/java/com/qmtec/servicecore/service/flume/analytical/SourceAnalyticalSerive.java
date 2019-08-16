package com.qmtec.servicecore.service.flume.analytical;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.servicecore.comm.FlumeAnalyticalComm;
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


    private void mysqlComm(SourceInfo sourceInfo ,FlumeAnalyticalComm source,FlumeConfig flumeConfig){
        sourceInfo.setComponentName(source.getAssemblyName());
        sourceInfo.setIpAddr(flumeConfig.getServerIp());

        String coreContent = source.getCoreContent();
        JSONObject jsonObject = JSONObject.parseObject(coreContent);

        sourceInfo.setCurrentThroughput((String)jsonObject.get("CurrentThroughput"));
        sourceInfo.setMaxThroughput((String)jsonObject.get("MaxThroughput"));
        sourceInfo.setEventCount((String)jsonObject.get("EventCount"));
        sourceInfo.setAverageThroughput((String)jsonObject.get("AverageThroughput"));

        sourceInfo.setModifyTime(new Date());
        sourceInfo.setDeleteFlag(0);
        sourceInfo.setRunState(SourceInfo.RunState.RUNING.getValue());
    }

    public void mysql(FlumeConfig flumeConfig ,FlumeAnalyticalComm source) {
        SourceInfo sourceInfo = null;
        if(StringUtils.isEmpty(source.getId())){
            sourceInfo = new SourceInfo();
            String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
            sourceInfo.setId(id);
            sourceInfo.setCreateTime(new Date());
            this.mysqlComm(sourceInfo,source,flumeConfig);
            sourceInfoMapper.insert(sourceInfo);
            flumeRelationSourcesMapper.insert(FlumeRelationSources
                    .builder()
                    .contextId(flumeConfig.getContextId())
                    .id(id)
                    .build());
        }else{
            sourceInfo = sourceInfoMapper.selectOneByExample(SourceInfoExample.newAndCreateCriteria().andIdEqualTo(source.getId()).example());
            this.mysqlComm(sourceInfo,source,flumeConfig);
            sourceInfoMapper.updateByExample(sourceInfo,SourceInfoExample.newAndCreateCriteria().andIdEqualTo(source.getId()).example());
        }
    }


}
