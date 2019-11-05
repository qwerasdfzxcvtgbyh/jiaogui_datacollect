package com.qmtec.servicecore.controller.hiveMate;


import com.alibaba.fastjson.JSON;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.servicecore.model.dto.hive.TableFieldInfoDto;
import com.qmtec.servicecore.model.dto.hive.TableIndexInfoDto;
import com.qmtec.servicecore.model.dto.hive.TablePartitionInfoDto;
import com.qmtec.servicecore.service.hiveMate.HiveMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/hiveMateView")
public class HiveMateViewController {

    @Autowired
    private HiveMateService hiveMateService;

    /**
     * 跳转hive
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/hiveIndex")
    public ModelAndView list(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("treeData", JSON.toJSON(hiveMateService.getHiveTree()));
        modelAndView.setViewName("/hiveMate/index");
        return modelAndView;
    }

    /**
     * 查询数据库基本信息
     *
     * @param dbId
     * @return
     */
    @RequestMapping(value = "/readDBInfo/{dbId}", method = {RequestMethod.GET})
    public ModelAndView readDBInfo(@PathVariable String dbId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbInfo",hiveMateService.readDBInfo(dbId));
        modelAndView.setViewName("/hiveMate/dbInfo");
        return modelAndView;
    }


    @RequestMapping(value = "/readTableInfo/{TabId}", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView readTableInfo(@PathVariable String TabId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tableBaseInfo",hiveMateService.readTableBaseInfo(TabId));
        modelAndView.addObject("TabId",TabId);
        modelAndView.setViewName("/hiveMate/tableInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/listSelectTableFieldInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<TableFieldInfoDto> listSelectTableFieldInfo(Long TabId,int page, int limit) {

        PageResultModel<TableFieldInfoDto> pageResultModel = new PageResultModel<TableFieldInfoDto>();
        ListResult<TableFieldInfoDto> listResult = hiveMateService.listSelectTableFieldInfo(TabId,page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    @RequestMapping(value = "/listSelectTableIndexInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<TableIndexInfoDto> listSelectTableIndexInfo(Long TabId,int page, int limit) {

        PageResultModel<TableIndexInfoDto> pageResultModel = new PageResultModel<TableIndexInfoDto>();
        ListResult<TableIndexInfoDto> listResult = hiveMateService.listSelectTableIndexInfo(TabId,page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }


    @RequestMapping(value = "/listSelectTablePartitionInfo/{TabId}", method = RequestMethod.GET)
    @ResponseBody
    public PageResultModel<TablePartitionInfoDto> listSelectTablePartitionInfo(@PathVariable String TabId) {

        PageResultModel<TablePartitionInfoDto> pageResultModel = new PageResultModel<TablePartitionInfoDto>();
        ListResult<TablePartitionInfoDto> listResult = hiveMateService.listSelectTablePartitionInfo(TabId);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    @RequestMapping(value = "/jumpDataPreview/{TabId}", method = {RequestMethod.GET})
    public ModelAndView jumpDataPreview(@PathVariable String TabId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("TabId",TabId);
        modelAndView.addObject("ColumnsDtoLists",hiveMateService.getColumnsDtoViewListByTableID(Long.valueOf(TabId)));
        modelAndView.setViewName("/hiveMate/tableDatePreview");
        return modelAndView;
    }

    @RequestMapping(value = "/listSelectTableDataPreview", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<Map<String, Object>> listSelectTableDataPreview(Long TabId,int page, int limit) {

        PageResultModel<Map<String, Object>> pageResultModel = new PageResultModel<Map<String, Object>>();
        ListResult<Map<String, Object>>  listResult = hiveMateService.hiveDataPreview(TabId, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");

        return pageResultModel;
    }
}
