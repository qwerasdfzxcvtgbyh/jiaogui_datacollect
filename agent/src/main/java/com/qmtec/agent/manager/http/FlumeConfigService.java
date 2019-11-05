package com.qmtec.agent.manager.http;

import com.github.dadiyang.httpinvoker.annotation.HttpApi;
import com.github.dadiyang.httpinvoker.annotation.HttpReq;
import com.github.dadiyang.httpinvoker.annotation.Param;
import com.github.dadiyang.httpinvoker.annotation.RetryPolicy;
import com.github.dadiyang.httpinvoker.enumeration.ReqMethod;

import java.util.Date;
import java.util.List;

/**
 * 请求service-core端的flume配置管理接口
 */
@HttpApi("${serviceCore.host}/flumeConfig")
@RetryPolicy
public interface FlumeConfigService {

    /**
     * 通过ContextId查询数据
     *
     * @param contextId
     * @return
     */
    @HttpReq(value = "/selectOneFlumeConfigByContextId", method = ReqMethod.POST)
    String selectOneFlumeConfigByContextId(@Param("contextId") Long contextId);

    /**
     * 通过ContextId查询部分字段数据
     *
     * @param contextId
     * @return
     */
    @HttpReq(value = "/queryPartialFieldsFlumeConfigByContextId", method = ReqMethod.POST)
    String queryPartialFieldsFlumeConfigByContextId(@Param("contextId") Long contextId);

    /**
     * 更新数据
     *
     * @param contextId
     * @param monitorPort
     * @param runstate
     * @param processPid
     * @param startupTime
     * @return
     */
    @HttpReq(value = "/updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId", method = ReqMethod.POST)
    String updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(
            @Param("contextId") Long contextId, @Param("monitorPort") Integer monitorPort,
            @Param("runstate") Integer runstate, @Param("processPid") Integer processPid,
            @Param("startupTime") Date startupTime);


    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (接收端)
     *
     * @param contextId
     * @return
     */
    @HttpReq(value = "/receiveCheckFlumeConfigByContextId", method = ReqMethod.POST)
    String receiveCheckFlumeConfigByContextId(@Param("contextId") Long contextId,@Param("runstate")Integer runstate);


    /**
     * 根据ContextId更新数据(运行状态)
     *
     * @param contextId
     * @param runstate
     * @return
     */
    @HttpReq(value = "/updateFlumeConfigByContextIdAndRunstate", method = ReqMethod.POST)
    String updateFlumeConfigByContextIdAndRunstate(@Param("contextId") Long contextId, @Param("runstate") Integer runstate);


    /**
     * 根据serverIp判断monitorPort是否可用
     *
     * @param serverIp
     * @param monitorPort
     * @return
     */
    @HttpReq(value = "/verifyMonitorPortIsAvailableByServerIp", method = ReqMethod.POST)
    String verifyMonitorPortIsAvailableByServerIp(@Param("serverIp") String serverIp, @Param("monitorPort") Integer monitorPort);


    @HttpReq(value = "/queryFlumeConfigByServerIpAndRunstate", method = ReqMethod.POST)
    String queryFlumeConfigByServerIpAndRunstate(@Param("serverIp") String serverIp, @Param("runstate") Integer runstate);
}
