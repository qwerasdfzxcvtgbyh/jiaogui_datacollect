package com.qmtec.servicecore.controller.flume;

import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.model.bo.FlumeConfigBo;
import com.qmtec.servicecore.model.vo.FlumeConfigVo;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FlumeConfig
 * 控制器
 *
 */
@RestController
@RequestMapping("/flumeConfig")
public class FlumeConfigController {

    @Autowired
    private FlumeConfigService flumeConfigService;

    @Autowired
    private Validator validator;

    /**
     * 公用的数据校验
     *
     * @param flumeConfigVo
     */
    private void valid( FlumeConfigVo flumeConfigVo){
        List<ConstraintViolation> valid = validator.validate(flumeConfigVo);
        if (valid != null && valid.size() > 0) {
            throw new CustomException("请求数据不正确,异常参数为:{" +
                    valid.get(0).getMessage() + "}", HttpCode.CODE_400);
        }
    }

    /**
     * 通过ContextId查询数据
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/selectOneFlumeConfigByContextId")
    public ResultModel<FlumeConfigBo> selectOneFlumeConfigByContextId(@RequestBody FlumeConfigVo flumeConfigVo) {

        this.valid(flumeConfigVo);
        ResultModel<FlumeConfigBo> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.selectOneFlumeConfigByContextId(flumeConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 通过ContextId查询部分字段数据
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/queryPartialFieldsFlumeConfigByContextId")
    public ResultModel<FlumeConfigBo> queryPartialFieldsFlumeConfigByContextId(@RequestBody FlumeConfigVo flumeConfigVo) {

        this.valid(flumeConfigVo);
        ResultModel<FlumeConfigBo> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.queryPartialFieldsFlumeConfigByContextId(flumeConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (接收端)
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/receiveCheckFlumeConfigByContextId")
    public ResultModel<Boolean> receiveCheckFlumeConfigByContextId(@RequestBody FlumeConfigVo flumeConfigVo) {

        this.valid(flumeConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<Boolean>();
        resultModel.setData(flumeConfigService.receiveCheckFlumeConfigByContextId(flumeConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 启动完成之后，用来更新数据(进程ID，监控端口，运行状态)
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId")
    public ResultModel<Boolean> updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(
            @RequestBody FlumeConfigVo flumeConfigVo) {

        this.valid(flumeConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(flumeConfigVo));
        resultModel.setMessage("更新成功");

        return resultModel;
    }

    /**
     * 根据ContextId更新数据(运行状态)
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/updateFlumeConfigByContextIdAndRunstate")
    public ResultModel<Boolean> updateFlumeConfigByContextIdAndRunstate(
            @RequestBody FlumeConfigVo flumeConfigVo) {

        this.valid(flumeConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.updateFlumeConfigByContextIdAndRunstate(flumeConfigVo));
        resultModel.setMessage("更新成功");

        return resultModel;
    }

    /**
     * 根据serverIp判断monitorPort是否可用
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/verifyMonitorPortIsAvailableByServerIp")
    public ResultModel<Boolean> verifyMonitorPortIsAvailableByServerIp(
            @RequestBody FlumeConfigVo flumeConfigVo) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.verifyMonitorPortIsAvailableByServerIp(flumeConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 查询数据通过serverIp 、Runstate
     *
     * @param flumeConfigVo
     * @return
     */
    @RequestMapping("/queryFlumeConfigByServerIpAndRunstate")
    public ResultModel<List<FlumeConfigBo>> queryFlumeConfigByServerIpAndRunstate(@RequestBody FlumeConfigVo flumeConfigVo) {

        ResultModel<List<FlumeConfigBo>> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.queryFlumeConfigByServerIpAndRunstate(flumeConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }
}
