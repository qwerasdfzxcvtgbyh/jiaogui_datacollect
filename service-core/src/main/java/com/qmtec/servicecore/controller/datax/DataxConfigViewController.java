package com.qmtec.servicecore.controller.datax;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.dto.DataxConfigDto;
import com.qmtec.servicecore.service.datax.DataxConfigService;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

/**
 * DataxConfig
 * 控制器
 */
@Controller
@RequestMapping("/DataxConfigView")
public class DataxConfigViewController {

    @Autowired
    private DataxConfigService dataxConfigService;
    @Autowired
    private FileTemplateService fileTemplateService;

    /**
     * 跳转Datax列表页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/listDataxConfig")
    public ModelAndView listDataxConfig(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/datax/table");

        return modelAndView;
    }

    /**
     * 分页查询Datax任务
     *
     * @return
     */
    @RequestMapping(value = "/listSelectDataxConfig", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<DataxConfigDto> listSelectDataxConfig(String searchParams, int page, int limit) {

        PageResultModel<DataxConfigDto> pageResultModel = new PageResultModel<DataxConfigDto>();
        ListResult<DataxConfigDto> listResult = dataxConfigService.listSelectDataxConfig(searchParams, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");

        return pageResultModel;
    }
    

    /**
     * 跳转添加页
     *
     * @return
     */
    @RequestMapping(value = "/addDataxConfig")
    public ModelAndView addDataxConfig(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PythonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DATAXSCRIPT.getValue()));
        modelAndView.addObject("JsonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DATAXJSON.getValue()));

        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("dataxConfig", new DataxConfigDto());
        modelAndView.setViewName("/datax/form");
        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updateDataxConfig")
    public ModelAndView updateDataxConfig(int isAdd, String id) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PythonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DATAXSCRIPT.getValue()));
        modelAndView.addObject("JsonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DATAXJSON.getValue()));

        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("dataxConfig", dataxConfigService.updateDataxConfigBefore(id));
        modelAndView.setViewName("/datax/form");

        return modelAndView;
    }

    /**
     * 保存、修改Datax任务信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateDataxConfig", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> addOrUpdateDataxConfig(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.addOrUpdateDataxConfig(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除Datax任务
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteDataxConfig", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteDataxConfig(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(dataxConfigService.deleteDataxConfig(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

}
