package com.qmtec.servicecore.controller.resourceDB;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.table.Element;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.model.dto.FileTemplateDto;
import com.qmtec.servicecore.model.dto.ResourceDbDto;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import com.qmtec.servicecore.service.resourceDB.view.ResouceDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * resourceDB
 * 控制器
 */
@Controller
@RequestMapping("/resourceDBView")
public class ResourceDBViewController {

    @Autowired
    private ResouceDBService resouceDBService;

    private List<JSONObject> getDbType() {
        List<JSONObject> list = new ArrayList<>();
        DbType[] filetypes = DbType.values();
        for (DbType dbtype : filetypes) {
            JSONObject json = new JSONObject();
            json.put("id", dbtype.getValue());
            json.put("name", dbtype.getType());
            list.add(json);
        }
        return list;
    }

    /**
     * 跳转数据源列表页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/listResouceDB")
    public ModelAndView listResouceDB(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbTypes", getDbType());
        modelAndView.setViewName("/resourceDB/list");
        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectResouceDB", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<ResourceDbDto> listSelectResouceDB(String searchParams, int page, int limit) {

        PageResultModel<ResourceDbDto> pageResultModel = new PageResultModel<ResourceDbDto>();
        ListResult<ResourceDbDto> listResult = resouceDBService.listSelectResouceDB(searchParams, page, limit);
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
    @RequestMapping(value = "/addResourceDB")
    public ModelAndView addResourceDB(int type, int isAdd) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        ResourceDbDto resourceDbDto = new ResourceDbDto();
        resourceDbDto.setDatabaseType(Integer.valueOf(type));
        modelAndView.addObject("resourceDB", resourceDbDto);

        if (DbType.TYPE_MYSQL.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/mysql/mysql");
        } else if (DbType.TYPE_HDFS.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/hdfs/hdfs");
        } else if (DbType.TYPE_HIVE.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/hive/hive");
        }

        modelAndView.addObject("dbTypes", getDbType());
        return modelAndView;
    }

    /**
     * 跳转修改页
     *
     * @return
     */
    @RequestMapping(value = "/updateResourceDB")
    public ModelAndView updateResourceDB(int type, int isAdd, String id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isAdd", isAdd);
        modelAndView.addObject("resourceDB", resouceDBService.updateResourceDbBefore(id));

        if (DbType.TYPE_MYSQL.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/mysql/mysql");
        } else if (DbType.TYPE_HDFS.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/hdfs/hdfs");
        } else if (DbType.TYPE_HIVE.getValue().equals(type)) {
            modelAndView.setViewName("/resourceDB/hive/hive");
        }

        modelAndView.addObject("dbTypes", getDbType());
        return modelAndView;
    }

    /**
     * 保存、修改数据源
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/addOrUpdateResourceDb", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> addOrUpdateResourceDb(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(resouceDBService.addOrUpdateResourceDb(data));
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    /**
     * 删除数据源
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteResourceDb", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> deleteResourceDb(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(resouceDBService.deleteResourceDb(data));
        resultModel.setMessage("删除成功");

        return resultModel;
    }

    /**
     * 连接测试
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/testConn", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel<Boolean> testConn(@RequestBody String data) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(resouceDBService.testConn(data));
        resultModel.setMessage("连接成功");

        return resultModel;
    }

    /**
     * --------------------------------------mysql-----------------------------------------------
     */
    @RequestMapping(value = "/relationIndex/{id}")
    public ModelAndView list(@PathVariable(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbId", id);
        modelAndView.addObject("treeData", JSON.toJSON(resouceDBService.getRelationDataBaseTableTree(id)));
        modelAndView.setViewName("/resourceDB/mysql/relationalDatabaseIndex");
        return modelAndView;
    }


    /**
     * 关系型数据库数据表基本信息
     *
     * @return
     */
    @RequestMapping(value = "/getTabInfo/{id}/{name}", method = {RequestMethod.GET})
    public ModelAndView getTabInfo(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbId", id);
        modelAndView.addObject("tabName", name);
        modelAndView.addObject("tableInfo", resouceDBService.getTabInfo(id, name));
        modelAndView.setViewName("/resourceDB/mysql/relationTableInfo");
        return modelAndView;
    }

    /**
     * 关系型数据库数据表字段信息
     *
     * @param dbId
     * @param tabName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/listSelectTableFieldInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<Element> listSelectTableFieldInfo(String dbId, String tabName, int page, int limit) {

        PageResultModel<Element> pageResultModel = new PageResultModel<Element>();
        ListResult<Element> listResult = resouceDBService.listSelectTableFieldInfo(dbId, tabName, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    /**
     * -----------------------------------------------------------------------------------
     */
    @RequestMapping(value = "/hiveIndex/{id}")
    public ModelAndView hiveIndex(@PathVariable(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbId", id);
        modelAndView.addObject("treeData", JSON.toJSON(resouceDBService.getHiveDataBaseTableTree(id)));
        modelAndView.setViewName("/resourceDB/hive/hiveDatabaseIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/getHiveTabInfo/{id}/{name}", method = {RequestMethod.GET})
    public ModelAndView getHiveTabInfo(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbId", id);
        modelAndView.addObject("tabName", name);
        modelAndView.setViewName("/resourceDB/hive/hiveTableInfo");
        return modelAndView;
    }

    /**
     * 获取hive表的字段
     *
     * @param dbId
     * @param tabName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/listSelectHiveTableFieldInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<Map<String, Object>> listSelectHiveTableFieldInfo(String dbId, String tabName, int page, int limit) {

        PageResultModel<Map<String, Object>> pageResultModel = new PageResultModel<Map<String, Object>>();
        ListResult<Map<String, Object>> listResult = resouceDBService.listSelectHiveTableFieldInfo(dbId, tabName, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }




    @RequestMapping(value = "/jumpDataPreview/{id}/{name}", method = {RequestMethod.GET})
    public ModelAndView jumpDataPreview(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dbId", id);
        modelAndView.addObject("tabName", name);
        modelAndView.addObject("ColumnsDtoLists",resouceDBService.jumpDataPreview(id,name));
        modelAndView.setViewName("/resourceDB/tableDatePreview");
        return modelAndView;
    }


    @RequestMapping(value = "/listSelectTableDataPreview", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<Map<String, Object>> listSelectTableDataPreview(String dbId,String tabName,int page, int limit) {

        PageResultModel<Map<String, Object>> pageResultModel = new PageResultModel<Map<String, Object>>();
        ListResult<Map<String, Object>>  listResult = resouceDBService.listSelectTableDataPreview(dbId,tabName, page, limit);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }


}
