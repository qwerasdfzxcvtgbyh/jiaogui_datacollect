package com.qmtec.agent.service;

import java.util.Map;

public interface AgentService {

    Boolean startFlume(Long contextId, Map<String, String> initMap);

    Boolean restartFlume(Long contextId, Map<String, String> initMap);

    Boolean stopFlume(Long contextId, Map<String, String> initMap);

    Boolean startDataX(String id, Map<String, String> initMap);

    Boolean restartDataX(String id, Map<String, String> initMap);

    Boolean stopDataX(String id, Map<String, String> initMap);
}
