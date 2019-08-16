package com.qmtec.agent.controller;


import com.qmtec.agent.service.AgentService;
import com.qmtec.common.web.HttpCode;
import com.qmtec.common.web.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    /**
     * 启动flume
     *
     * @return
     */
    @RequestMapping("/startFlume")
    public ResultModel<Boolean> startFlume(Long contextId) {

        ResultModel<Boolean> resultModel = new ResultModel<>();

        Boolean res = agentService.startFlume(contextId,new HashMap<>());//map存放固定值

        resultModel.setData(res);
        if (res) {
            resultModel.setMessage("启动成功");
            return resultModel;
        }
        resultModel.setCode(HttpCode.CODE_500.toValue());
        resultModel.setMessage("启动失败");
        return resultModel;
    }

    /**
     * 停止flume
     *
     * @return
     */
    @RequestMapping("/stopFlume")
    public ResultModel<Boolean> stopFlume(Long contextId) {

        ResultModel<Boolean> resultModel = new ResultModel<>();

        Boolean res = agentService.stopFlume(contextId,new HashMap<>());//map存放固定值
        resultModel.setData(res);
        if (res) {
            resultModel.setMessage("进程停止成功");
            return resultModel;
        }

        resultModel.setCode(HttpCode.CODE_500.toValue());
        resultModel.setMessage("进程停止失败");
        return resultModel;
    }

    /**
     * 重启flume
     *
     * @return
     */
    @RequestMapping("/restartFlume")
    public ResultModel<Boolean> restartFlume(Long contextId) {

        ResultModel<Boolean> resultModel = new ResultModel<>();

        Boolean res = agentService.restartFlume(contextId,new HashMap<>());//map存放固定值

        resultModel.setData(res);
        if (res) {
            resultModel.setMessage("启动成功");
            return resultModel;
        }
        resultModel.setCode(HttpCode.CODE_500.toValue());
        resultModel.setMessage("启动失败");
        return resultModel;
    }

}
