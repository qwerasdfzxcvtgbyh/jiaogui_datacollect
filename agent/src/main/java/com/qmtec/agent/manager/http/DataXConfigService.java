package com.qmtec.agent.manager.http;

import com.github.dadiyang.httpinvoker.annotation.HttpApi;
import com.github.dadiyang.httpinvoker.annotation.HttpReq;
import com.github.dadiyang.httpinvoker.annotation.Param;
import com.github.dadiyang.httpinvoker.annotation.RetryPolicy;
import com.github.dadiyang.httpinvoker.enumeration.ReqMethod;

import java.util.Date;

/**
 * 请求service-core端的DataX配置管理接口
 */
@HttpApi("${serviceCore.host}/DataXConfig")
@RetryPolicy
public interface DataXConfigService {

    @HttpReq(value = "/selectOneDataXConfigById", method = ReqMethod.POST)
    String selectOneDataxConfigById(@Param("id") String id);

    @HttpReq(value = "/updateDataXConfigByIdAndRunstate", method = ReqMethod.POST)
    String updateDataXConfigByIdAndRunstate(@Param("id") String id,@Param("runstate")Integer runstate);

    @HttpReq(value = "/receiveCheckDataXConfigById", method = ReqMethod.POST)
    String receiveCheckDataXConfigById(@Param("id") String id,@Param("runstate")Integer runstate);

    @HttpReq(value = "/updateDataXConfigByProcesspidAndRunstateAndId", method = ReqMethod.POST)
    String updateDataXConfigByProcesspidAndRunstateAndId(
            @Param("id") String id,@Param("runstate") Integer runstate,
            @Param("processPid") Integer processPid, @Param("startupTime") Date startupTime);

    @HttpReq(value = "/queryPartialFieldsDataXConfigById", method = ReqMethod.POST)
    String queryPartialFieldsDataXConfigById(@Param("id") String id);

    @HttpReq(value = "/queryDataXConfigByServerIpAndRunstate", method = ReqMethod.POST)
    String queryDataXConfigByServerIpAndRunstate(@Param("serverIp") String serverIp, @Param("runstate") Integer runstate);

}
