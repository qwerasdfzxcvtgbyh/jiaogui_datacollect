package com.qmtec.servicecore.controller.colum;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.comm.ColumType;
import com.qmtec.servicecore.model.dto.ColumInfoDto;
import com.qmtec.servicecore.service.colum.ColumService;
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
 * ColumInfo
 * 控制器
 */
@Controller
@RequestMapping("/ColumInfoView")
public class ColumInfoViewController {

    @Autowired
    private ColumService columService;

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
     * 跳转Flume列表页
     *
     * @return
     */
    @RequestMapping(value = "/listColumInfo")
    public ModelAndView listColumInfo() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("columTypes", getColumType());
        modelAndView.setViewName("/colum/list");

        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectColumInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<ColumInfoDto> listSelectColumInfo(String searchParams,int page, int limit) {

        PageResultModel<ColumInfoDto> pageResultModel = new PageResultModel<ColumInfoDto>();
        ListResult<ColumInfoDto> listResult = columService.listSelectColumInfo(searchParams, page, limit);
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
    @RequestMapping(value = "/addColumInfo")
    public ModelAndView addColumInfo(int isAdd) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("columInfo", new ColumInfoDto());
        modelAndView.setViewName("/colum/form");
        modelAndView.addObject("columTypes", getColumType());
        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updatecolum")
    public ModelAndView updatecolum(int isAdd, String id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("columInfo", columService.updateColumInfoBefore(id));
        modelAndView.setViewName("/colum/form");
        modelAndView.addObject("columTypes", getColumType());

        return modelAndView;
    }

    /**
     * 保存、修改
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateColumInfo", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Map<String,Object>> addOrUpdateColumInfo(@RequestBody String data) {

        ResultModel<Map<String,Object> > resultModel = new ResultModel<>();
        resultModel.setData(columService.addOrUpdateColumInfo(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteColumInfo", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteColumInfo(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(columService.deleteColumInfo(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

    @RequestMapping(value = "/getColumInfoById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel<ColumInfoDto> getColumInfoById(@RequestBody String data) {

        ResultModel<ColumInfoDto> resultModel = new ResultModel<ColumInfoDto>();
        resultModel.setData(columService.getColumInfoById(data));
        resultModel.setMessage("获取成功");

        return resultModel;
    }

}
