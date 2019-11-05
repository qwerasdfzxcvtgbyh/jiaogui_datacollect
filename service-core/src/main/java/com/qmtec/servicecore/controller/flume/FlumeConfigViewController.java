package com.qmtec.servicecore.controller.flume;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.dto.FlumeConfigDto;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FlumeConfig
 * 控制器
 */
@Controller
@RequestMapping("/flumeConfigView")
public class FlumeConfigViewController {

    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private FileTemplateService fileTemplateService;

    /**
     * 主动刷新flume状态
     *
     * @param data
     * @return
     */
    @RequestMapping("/refreshFlumeRunStatus")
    @ResponseBody
    public ResultModel<Boolean> refreshFlumeRunStatus(
            @RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.refreshFlumeRunStatus(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 跳转Flume列表页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/listFlume")
    public String list(HttpSession session) {
        return "/flume/table";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectFlumeConfig", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<FlumeConfigDto> listSelectFlumeConfig(String searchParams, int page, int limit) {

        PageResultModel<FlumeConfigDto> pageResultModel = new PageResultModel<FlumeConfigDto>();
        ListResult<FlumeConfigDto> listResult = flumeConfigService.listSelectFlumeConfig(searchParams, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");

        return pageResultModel;
    }

    private List<JSONObject> getFlumeTaskType(){
        List<JSONObject> list = new ArrayList<>();
        FlumeTaskType[] flumeTaskTypes = FlumeTaskType.values();
        for(FlumeTaskType flumeTaskType : flumeTaskTypes ){
            JSONObject json = new JSONObject();
            json.put("id",flumeTaskType.getValue());
            json.put("name",flumeTaskType.getName());
            list.add(json);
        }
        return  list;
    }

    /**
     * 跳转添加页
     *
     * @return
     */
    @RequestMapping(value = "/addFlume")
    public ModelAndView addFlume(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.FLUME.getValue()));
        modelAndView.addObject("flumeConfigTypes",this.getFlumeTaskType());
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("flumeConfig", new FlumeConfigDto());
        modelAndView.setViewName("/flume/form");

        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updateFlume")
    public ModelAndView updateFlume(int isAdd, Long contextId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.FLUME.getValue()));
        modelAndView.addObject("flumeConfigTypes",this.getFlumeTaskType());
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("flumeConfig", flumeConfigService.updateFlumeConfigBefore(contextId));
        modelAndView.setViewName("/flume/form");

        return modelAndView;
    }

    /**
     * 保存、修改Flume信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateFlume", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> addOrUpdateFlume(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.addOrUpdateFlume(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除Flume信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteFlumeConfig", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteFlumeConfig(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.deleteFlumeConfig(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }


    /**
     * 启动flume
     *
     * @return
     */
    @RequestMapping("/startFlume")
    @ResponseBody
    public ResultModel<Boolean> startFlume(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        Map<String, Object> map = flumeConfigService.startFlume(data);
        resultModel.setCode((int) map.get("code"));
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));

        return resultModel;
    }

    /**
     * 停止flume
     *
     * @return
     */
    @RequestMapping("/stopFlume")
    @ResponseBody
    public ResultModel<Boolean> stopFlume(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        Map<String, Object> map = flumeConfigService.stopFlume(data);
        resultModel.setCode((int) map.get("code"));
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));

        return resultModel;
    }


    /**
     * 查看运行信息前判断
     *
     * @return
     */
    @RequestMapping("/viewRunInforBefore")
    @ResponseBody
    public ResultModel<Boolean> viewRunInforBefore(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeConfigService.viewRunInforBefore(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 查看运行信息
     *
     * @return
     */
    @RequestMapping("/viewRunInfor")
    public ModelAndView viewRunInfor(Long contextId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flumeConfig", flumeConfigService.viewRunInfor(contextId));
        modelAndView.setViewName("/flume/monitorInfo");

        return modelAndView;
    }

    /**
     * 重启flume
     *
     * @return
     */
    @RequestMapping("/restartFlume")
    @ResponseBody
    public ResultModel<Boolean> restartFlume(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        Map<String, Object> map = flumeConfigService.restartFlume(data);
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));

        return resultModel;
    }

}
