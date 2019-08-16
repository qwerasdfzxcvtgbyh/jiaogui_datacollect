package com.qmtec.agent.manager.http;

import com.github.dadiyang.httpinvoker.annotation.HttpApi;
import com.github.dadiyang.httpinvoker.annotation.HttpReq;
import com.github.dadiyang.httpinvoker.annotation.Param;
import com.github.dadiyang.httpinvoker.annotation.RetryPolicy;
import com.github.dadiyang.httpinvoker.enumeration.ReqMethod;

import java.util.Date;

/**
 * 请求service-core端的flume配置管理接口
 */
@HttpApi("${serviceCore.host}/DataxConfigView")
@RetryPolicy
public interface DataConfigService {


}
