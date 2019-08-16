package com.qmtec.servicecore.controller.fileTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.dto.FileTemplateDto;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * FileTemplate
 * 控制器
 */
@Controller
@RequestMapping("/fileTemplateView")
public class FileTemplateViewController {

    @Autowired
    private FileTemplateService fileTemplateService;

    /**
     * 跳转Flume列表页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/listFileTemplate")
    public ModelAndView list(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("filetypes", getFileType());
        modelAndView.setViewName("/fileTemplate/list");

        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectFileTemplate", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<FileTemplateDto> listSelectFlumeConfig(String searchParams, int page, int limit) {

        PageResultModel<FileTemplateDto> pageResultModel = new PageResultModel<FileTemplateDto>();
        ListResult<FileTemplateDto> listResult = fileTemplateService.listSelectFileTemplate(searchParams, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");

        return pageResultModel;
    }

    private List<JSONObject> getFileType(){
        List<JSONObject> list = new ArrayList<>();

        FileTemplate.Filetype[] filetypes =  FileTemplate.Filetype.values();
        for( FileTemplate.Filetype filetype : filetypes ){
            JSONObject json = new JSONObject();
            json.put("id",filetype.getValue());
            json.put("name",filetype.getName());
            list.add(json);
        }

        return  list;
    }

    /**
     * 跳转添加页
     *
     * @return
     */
    @RequestMapping(value = "/addFileTemplate")
    public ModelAndView addFlume(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("fileTemplate", new FileTemplateDto());
        modelAndView.setViewName("/fileTemplate/form");
        modelAndView.addObject("filetypes", getFileType());
        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updateFileTemplate")
    public ModelAndView updateFlume(int isAdd, String id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("fileTemplate", fileTemplateService.updateFileTemplateBefore(id));
        modelAndView.setViewName("/fileTemplate/form");
        modelAndView.addObject("filetypes", getFileType());

        return modelAndView;
    }

    /**
     * 保存、修改文件模板信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateFlumeTemplate", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> addOrUpdateFlumeTemplate(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(fileTemplateService.addOrUpdateFlumeTemplate(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除文件模板
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteFileTemplate", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteFileTemplate(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(fileTemplateService.deleteFileTemplate(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

    @RequestMapping(value = "/getFileTemplateContentById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel<String> getFileTemplateContentById(@RequestBody String data) {

        ResultModel<String> resultModel = new ResultModel<>();
        resultModel.setData(fileTemplateService.getFileTemplateContentById(data));
        resultModel.setMessage("获取成功");

        return resultModel;
    }

}
