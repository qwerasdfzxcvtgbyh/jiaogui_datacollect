package com.qmtec.servicecore.controller.datax;

import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.bo.DataxConfigBo;
import com.qmtec.servicecore.model.dto.DataxConfigDto;
import com.qmtec.servicecore.model.vo.DataxConfigVo;
import com.qmtec.servicecore.model.vo.FlumeConfigVo;
import com.qmtec.servicecore.service.datax.DataxConfigService;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * DataxConfig
 * 控制器
 */
@RestController
@RequestMapping("/DataXConfig")
public class DataXConfigController {

    @Autowired
    private DataxConfigService dataxConfigService;
    @Autowired
    private Validator validator;

    /**
     * 公用的数据校验
     *
     */
    private void valid( DataxConfigVo dataxConfigVo){
        List<ConstraintViolation> valid = validator.validate(dataxConfigVo);
        if (valid != null && valid.size() > 0) {
            throw new CustomException("请求数据不正确,异常参数为:{" +
                    valid.get(0).getMessage() + "}", HttpCode.CODE_400);
        }
    }

    /**
     * 通过Id查询数据
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/selectOneDataXConfigById")
    public ResultModel<DataxConfigBo> selectOneDataxConfigById(@RequestBody DataxConfigVo dataxConfigVo) {

        this.valid(dataxConfigVo);
        ResultModel<DataxConfigBo> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.selectOneDataxConfigById(dataxConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 根据Id更新数据(运行状态)
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/updateDataXConfigByIdAndRunstate")
    public ResultModel<Boolean> updateDataXConfigByIdAndRunstate( @RequestBody DataxConfigVo dataxConfigVo) {

        this.valid(dataxConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.updateDataXConfigByIdAndRunstate(dataxConfigVo));
        resultModel.setMessage("更新成功");

        return resultModel;
    }


    /**
     * 判断任务id是否执行 ，true : 可以执行
     * (接收端)
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/receiveCheckDataXConfigById")
    public ResultModel<Boolean> receiveCheckDataXConfigById(@RequestBody DataxConfigVo dataxConfigVo) {

        this.valid(dataxConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<Boolean>();
        resultModel.setData(dataxConfigService.receiveCheckDataXConfigById(dataxConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 启动完成之后，用来更新数据(进程ID,运行状态)
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/updateDataXConfigByProcesspidAndRunstateAndId")
    public ResultModel<Boolean> updateDataXConfigByProcesspidAndRunstateAndId(
            @RequestBody DataxConfigVo dataxConfigVo) {

        this.valid(dataxConfigVo);
        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.updateDataXConfigByProcesspidAndRunstateAndId(dataxConfigVo));
        resultModel.setMessage("更新成功");

        return resultModel;
    }


    /**
     * 通过ContextId查询部分字段数据
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/queryPartialFieldsDataXConfigById")
    public ResultModel<DataxConfigBo> queryPartialFieldsDataXConfigById(@RequestBody DataxConfigVo dataxConfigVo) {

        this.valid(dataxConfigVo);
        ResultModel<DataxConfigBo> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.queryPartialFieldsDataXConfigById(dataxConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }

    /**
     * 查询数据通过serverIp 、Runstate
     *
     * @param dataxConfigVo
     * @return
     */
    @RequestMapping("/queryDataXConfigByServerIpAndRunstate")
    public ResultModel<List<DataxConfigBo>> queryDataXConfigByServerIpAndRunstate(@RequestBody DataxConfigVo dataxConfigVo) {

        ResultModel<List<DataxConfigBo>> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.queryDataXConfigByServerIpAndRunstate(dataxConfigVo));
        resultModel.setMessage("查询成功");

        return resultModel;
    }


}
