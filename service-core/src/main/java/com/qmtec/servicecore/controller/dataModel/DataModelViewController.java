package com.qmtec.servicecore.controller.dataModel;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.comm.ColumType;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.dto.DataModelDto;
import com.qmtec.servicecore.service.dataModel.DataModelService;
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
import java.util.Map;

/**
 * DataModel
 * 控制器
 */
@Controller
@RequestMapping("/DataModelView")
public class DataModelViewController {

    @Autowired
    private DataModelService dataModelService;

    private List<JSONObject> getColumType(){
        List<JSONObject> list = new ArrayList<>();
        ColumType[] columTypes =  ColumType.values();
        for( ColumType columType: columTypes ){
            JSONObject json = new JSONObject();
            json.put("id",columType.getName());
            json.put("name",columType.getName());
            list.add(json);
        }
        return  list;
    }

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
    @RequestMapping(value = "/listSelectDataModel", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<DataModelDto> listSelectDataModel(String searchParams, int page, int limit) {

        PageResultModel<DataModelDto> pageResultModel = new PageResultModel<DataModelDto>();
        ListResult<DataModelDto> listResult = dataModelService.listSelectDataMode(searchParams, page, limit);
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
    @RequestMapping(value = "/addDataModel")
    public ModelAndView addDataModel(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("DataModel", new DataModelDto());
        modelAndView.addObject("columTypes", getColumType());
        modelAndView.setViewName("/dataModel/formInfo");
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
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("DataModel", dataModelService.updateDataModeBefore(id));//获取对象
        modelAndView.addObject("columTypes", getColumType());
        modelAndView.setViewName("/dataModel/formInfo");

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
        resultModel.setData(dataModelService.addOrUpdateDataMode(data));
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
        resultModel.setData(dataModelService.deleteDataMode(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

}
