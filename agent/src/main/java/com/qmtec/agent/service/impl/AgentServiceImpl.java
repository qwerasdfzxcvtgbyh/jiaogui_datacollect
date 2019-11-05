package com.qmtec.agent.service.impl;

import com.qmtec.agent.service.AgentService;
import com.qmtec.agent.service.DataX.DataxService;
import com.qmtec.agent.service.flume.FlumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AgentServiceImpl  implements AgentService {

    @Autowired
    private FlumeService flumeService;
    @Autowired
    private DataxService dataxService;

    /**
     * 启动flume
     *
     * @param initMap  ： 系统初始化值（现在未使用）
     * @return
     */
    @Override
    public Boolean startFlume(Long contextId, Map<String, String> initMap) {
        return flumeService.start(contextId,initMap);
    }

    /**
     * 停止flume
     *
     * @param initMap  ： 系统初始化值（现在未使用）
     * @return
     */
    @Override
    public Boolean stopFlume(Long contextId, Map<String, String> initMap) {
        return flumeService.stop(contextId,initMap);
    }

    /**
     * 重启flume
     *
     * @param contextId
     * @param initMap
     * @return
     */
    @Override
    public Boolean restartFlume(Long contextId, Map<String, String> initMap) {
        return flumeService.restart(contextId,initMap);
    }

    @Override
    public Boolean startDataX(String id, Map<String, String> initMap) {
        return dataxService.startDataX(id,initMap);
    }

    @Override
    public Boolean restartDataX(String id, Map<String, String> initMap) {
        return dataxService.restartDataX(id,initMap);
    }

    @Override
    public Boolean stopDataX(String id, Map<String, String> initMap) {
        return dataxService.stopDataX(id,initMap);
    }

}
