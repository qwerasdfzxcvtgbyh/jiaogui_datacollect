package com.qmtec.servicecore.service.flumeMonitor.impl;

import com.qmtec.common.entity.flume.FlumeMonitor;
import com.qmtec.servicecore.entity.ChannelInfo;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.SinkInfo;
import com.qmtec.servicecore.entity.SourceInfo;
import com.qmtec.servicecore.model.vo.FlumeConfigVo;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flume.FlumeMomitorProcess;
import com.qmtec.servicecore.service.flume.analytical.AnalyticalServices;
import com.qmtec.servicecore.service.flumeMonitor.ChannleInfoService;
import com.qmtec.servicecore.service.flumeMonitor.FlumeMonitorService;
import com.qmtec.servicecore.service.flumeMonitor.SinkInfoService;
import com.qmtec.servicecore.service.flumeMonitor.SourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class FlumeMonitorServiceImpl implements FlumeMonitorService {

    @Autowired
    private SourceInfoService sourceInfoService;
    @Autowired
    private ChannleInfoService channleInfoService;
    @Autowired
    private SinkInfoService sinkInfoService;

    @Autowired
    private FlumeMomitorProcess flumeMomitorProcess;
    @Autowired
    private AnalyticalServices analyticalServices;
    @Autowired
    private FlumeConfigService flumeConfigService;

    /**
     * 修改“正在运行”的监控信息状态为“端口异常”
     *
     * @param contextId
     */
    public void updateMonitorInfoToPoreExce(Long contextId) {
        sourceInfoService.updateSoureceStateByContextId(contextId, SourceInfo.RunState.PORTMONITORINGEXCEPTION.getValue());
        channleInfoService.updateChanelStateByContextId(contextId, ChannelInfo.RunState.PORTMONITORINGEXCEPTION.getValue());
        sinkInfoService.updateSinkStateByContextId(contextId, SinkInfo.RunState.PORTMONITORINGEXCEPTION.getValue());
    }

    /**
     * 修改“正在运行”的监控信息状态为“关闭”
     *
     * @param contextId
     */
    public void updateMonitorInfoToClose(Long contextId) {
        sourceInfoService.updateSoureceStateByContextId(contextId, SourceInfo.RunState.CLOSE.getValue());
        channleInfoService.updateChanelStateByContextId(contextId, ChannelInfo.RunState.CLOSE.getValue());
        sinkInfoService.updateSinkStateByContextId(contextId, SinkInfo.RunState.CLOSE.getValue());
    }

    /**
     * 修改“正在运行”的监控信息状态
     *
     * @param contextId
     * @param runstate
     */
    public void updateMonitoeInfoState(Long contextId, Integer runstate) {
        sourceInfoService.updateSoureceStateByContextId(contextId, runstate);
        channleInfoService.updateChanelStateByContextId(contextId, runstate);
        sinkInfoService.updateSinkStateByContextId(contextId, runstate);
    }

    /**
     * 由定时线程调用该方法
     * 查询所有“已开启”的Flume任务，并通过http请求
     */
    public void timingMonitor() {
        try {
            List<FlumeConfig> flumeConfigList = flumeConfigService.monitoringFlume();
            this.monitorCore(flumeConfigList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("定时监控任务》》执行失败");
        }
    }

    /**
     * 前端主动刷新时调用
     * 查询某个任务类型下的
     */
    public Boolean autoRefreshMonitor(int type) {
        Boolean flag = false;
        try {
            List<FlumeConfig> flumeConfigList = flumeConfigService
                             .getFlumeConfigByTypeAndRunState(type, FlumeConfig.Runstate.RUNING.getValue());
            this.monitorCore(flumeConfigList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("主动刷新监控任务》》执行失败");
        }
        return flag;
    }

    /**
     *
     */
    private void monitorCore(List<FlumeConfig> flumeConfigList) throws Exception {
        if (flumeConfigList.size() > 0) {
            List<FlumeMonitor> flumeMonitorList = flumeMomitorProcess.refreshStatus(flumeConfigList, false);

            for (FlumeMonitor flumeMonitor : flumeMonitorList) {
                Long contextId = flumeMonitor.getContextId();
                Boolean b = flumeMonitor.getIsRun();

                if (b) {
                    log.debug("ContextId : " + contextId + "任运行");

                    String content = flumeMonitor.getFlumeMonitorContent();


                    synchronized (this){
                        analyticalServices.analyticalContent(contextId, content);//解析内容
                    }

                } else {
                    //修改任务为端口异常
                    flumeConfigService.updateFlumeConfigByContextIdAndRunstate(
                            FlumeConfigVo.builder()
                                    .contextId(contextId)
                                    .runstate(FlumeConfig.Runstate.PORTMONITORINGEXCEPTION.getValue()).build());

                    this.updateMonitorInfoToPoreExce(contextId);//修改“正在运行”监控内容为：“端口异常”
                }
            }
        } else {
            log.info("无运行的flume");
        }
    }


}
