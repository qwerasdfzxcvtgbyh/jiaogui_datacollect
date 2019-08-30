package com.qmtec.servicecore.controller.dataModel;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * DataModel
 * 控制器
 */
@Controller
@RequestMapping("/DataModelView")
public class DataModelViewController {

    /**
     * 跳转DataModel列表页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/listDataModel")
    public ModelAndView listDataModel(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dataModel/list");

        return modelAndView;
    }

    /**
     * 分页查询DataModel任务
     *
     * @return
     */
    /*@RequestMapping(value = "/listSelectDataModelConfig", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<DataModelConfigDto> listSelectDataModelConfig(String searchParams, int page, int limit) {

        PageResultModel<DataModelConfigDto> pageResultModel = new PageResultModel<DataModelConfigDto>();
        ListResult<DataModelConfigDto> listResult = DataModelConfigService.listSelectDataModelConfig(searchParams, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");

        return pageResultModel;
    }*/
    

    /**
     * 跳转添加页
     *
     * @return
     */
    @RequestMapping(value = "/addDataModel")
    public ModelAndView addDataModel(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();

        /*modelAndView.addObject("PythonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DataModelSCRIPT.getValue()));
        modelAndView.addObject("JsonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DataModelJSON.getValue()));

        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("DataModelConfig", new DataModelConfigDto());*/
        modelAndView.setViewName("/dataModel/form");

        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updateDataModel")
    public ModelAndView updateDataModel(int isAdd, String id) {

        ModelAndView modelAndView = new ModelAndView();

        /*modelAndView.addObject("PythonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DataModelSCRIPT.getValue()));
        modelAndView.addObject("JsonFileTemplates",
                fileTemplateService.getFileTemplateByFileType(FileTemplate.Filetype.DataModelJSON.getValue()));

        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("DataModelConfig", DataModelConfigService.updateDataModelConfigBefore(id));*/
        modelAndView.setViewName("/DataModel/form");

        return modelAndView;
    }

    /**
     * 保存、修改DataModel任务信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateDataModel", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> addOrUpdateDataModel(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        //resultModel.setData(DataModelConfigService.addOrUpdateDataModelConfig(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除DataModel任务
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteDataModel", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteDataModel(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        //resultModel.setData(DataModelConfigService.deleteDataModelConfig(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

    /**--------------------------------------**/

    @RequestMapping("/startDataModel")
    @ResponseBody
    public ResultModel<Boolean> startDataModel(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        /*Map<String, Object> map = DataModelConfigService.startDataModel(data);
        resultModel.setCode((int) map.get("code"));
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));*/

        return resultModel;
    }

    /**
     * 重启DataModel
     *
     * @return
     */
    @RequestMapping("/restartDataModel")
    @ResponseBody
    public ResultModel<Boolean> restartDataModel(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        /*Map<String, Object> map = DataModelConfigService.restartDataModel(data);
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));
*/
        return resultModel;
    }

    /**
     * 停止DataModel
     *
     * @return
     */
    @RequestMapping("/stopDataModel")
    @ResponseBody
    public ResultModel<Boolean> stopDataModel(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        /*Map<String, Object> map = DataModelConfigService.stopDataModel(data);
        resultModel.setCode((int) map.get("code"));
        resultModel.setData((Boolean) map.get("data"));
        resultModel.setMessage((String) map.get("message"));*/

        return resultModel;
    }
}
