package com.qmtec.servicecore.service.flumeMonitor;

public interface FlumeMonitorService {

    void updateMonitoeInfoState(Long contextId ,Integer runstate);

    void updateMonitorInfoToPoreExce(Long contextId);

    void updateMonitorInfoToClose(Long contextId);

    void timingMonitor();

    Boolean autoRefreshMonitor(int type);

}
