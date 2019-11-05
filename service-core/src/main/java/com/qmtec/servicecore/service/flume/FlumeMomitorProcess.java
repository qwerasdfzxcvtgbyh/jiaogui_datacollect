package com.qmtec.servicecore.service.flume;

import com.qmtec.common.entity.flume.FlumeMonitor;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.util.HttpClientResult;
import com.qmtec.common.util.HttpClientUtil;
import com.qmtec.servicecore.entity.FlumeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


/**
 *  该类主要用于对Flume任务进行远程请求
 *
 */
@Slf4j
@Service
public class FlumeMomitorProcess {

    @Value("${activeRefresh.ThreadNum}")
    private int ActiveRefreshThreadNum;

    @Value("${passiveRefresh.ThreadNum}")
    private int PassiveRefreshThreadNum;

    private static ExecutorService pool = null;

    /**
     * 初始化线程池
     *
     * @return
     */
    public ExecutorService getThreadPool() {
        if (pool == null) {
            int ThreadPoolNum = ActiveRefreshThreadNum + PassiveRefreshThreadNum;
            pool = Executors.newFixedThreadPool(ThreadPoolNum);
        }
        return pool;
    }

    /**
     * 分发给线程，并执行
     *
     * @param flumeConfigs
     * @return
     */
    public List<FlumeMonitor> refreshStatus(List<FlumeConfig> flumeConfigs, boolean isActive) {
        List<FlumeMonitor> list = new ArrayList<FlumeMonitor>();
        try {
            int threadNum;
            if (isActive) {
                threadNum = ActiveRefreshThreadNum;
            } else {
                threadNum = PassiveRefreshThreadNum;
            }
            List<Future<FlumeMonitor>> fetures = this.getThreadPool().invokeAll(getFlumeMomitorHttpThreadList(flumeConfigs, threadNum));
            for (Future<FlumeMonitor> feture : fetures) {
                FlumeMonitor flumeMonitor = feture.get();
                list.add(flumeMonitor);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("刷新状态失败");
        }
        return list;
    }

    /**
     * 分发给线程
     *
     * @param flumeConfigs
     * @return
     */
    private List<FlumeMomitorHttpThread> getFlumeMomitorHttpThreadList(List<FlumeConfig> flumeConfigs, int threadNum) {
        List<List<FlumeConfig>> configs = new ArrayList<List<FlumeConfig>>();
        //创建盒子
        int i = 0;
        for (; i < threadNum; i++) {
            configs.add(new ArrayList<FlumeConfig>());
        }

        //往盒子里放数据
        int f = 0;
        for (; f < flumeConfigs.size(); f++) {
            int n = f % threadNum;
            configs.get(n).add(flumeConfigs.get(f));
        }

        List<FlumeMomitorHttpThread> list = new ArrayList<FlumeMomitorHttpThread>();
        for (List<FlumeConfig> flumeConfigList : configs) {
            if (flumeConfigList.size() > 0) {
                list.add(new FlumeMomitorHttpThread(flumeConfigList));
            }
        }

        return list;
    }

    /**
     * 访问flume监控端口
     */
    class FlumeMomitorHttpThread implements Callable<FlumeMonitor> {

        private List<FlumeConfig> flumeConfigs = null;

        public FlumeMomitorHttpThread(List<FlumeConfig> flumeConfigs) {
            this.flumeConfigs = flumeConfigs;
        }

        @Override
        public FlumeMonitor call() throws Exception {
            FlumeMonitor flumeMonitor = new FlumeMonitor();
            if (flumeConfigs.size() > 0) {
                flumeConfigs.forEach(flumeConfig -> {

                    String serverIp = flumeConfig.getServerIp();
                    Integer monitorPort = flumeConfig.getMonitorPort();
                    Long contextId = flumeConfig.getContextId();

                    if (monitorPort != null) {
                        StringBuffer url = new StringBuffer();
                        //生成请求flume监控端口的url
                        url.append("http://").append(serverIp).append(":").append(monitorPort).append("/metrics");

                        String content = this.flumeMomitorRequest(url.toString(), 3);
                        if (StringUtils.isEmpty(content)) {
                            flumeMonitor.setIsRun(false);
                        } else {
                            flumeMonitor.setIsRun(true);
                            flumeMonitor.setFlumeMonitorContent(content);
                        }
                    } else {
                        log.debug("ID ：" + flumeConfig.getContextId() + " ==> MonitorPort is null");
                        flumeMonitor.setIsRun(false);
                    }
                    flumeMonitor.setContextId(contextId);
                });
            }
            return flumeMonitor;
        }

        /**
         * 请求flume的url
         *
         * @param url
         * @param renum : 重复次数
         * @return
         */
        private String flumeMomitorRequest(String url, int renum) {
            String content = null;
            try {
                HttpClientResult httpClientResult = HttpClientUtil.doGet(url);
                content = httpClientResult.getContent();
            } catch (Exception e) {
                log.debug("异常：" + e.getMessage());
                if (renum > 0) {
                    content = flumeMomitorRequest(url, --renum);
                }
            }
            return content;
        }
    }


}
