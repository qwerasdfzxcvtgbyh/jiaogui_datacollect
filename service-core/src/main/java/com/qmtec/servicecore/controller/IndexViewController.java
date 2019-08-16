package com.qmtec.servicecore.controller;

import com.qmtec.servicecore.config.WebMvcConfg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *
 * View控制器
 */
@Controller
public class IndexViewController {

    /**
     * 跳转主页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", session.getAttribute(WebMvcConfg.SESSION_KEY));
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public String welcome(HttpSession session) {
        return "/admin/welcome";
    }

    @RequestMapping(value = "/initJson", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String initJson(HttpSession session) {
        return "{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"首页\",\"icon\":\"fa fa-home\",\"href\":\"\"},\"logoInfo\":{\"title\":\"ETL平台\",\"image\":\"images/logo.png\",\"href\":\"\"},\"menuInfo\":{\"currency\":{\"title\":\"常规管理\",\"icon\":\"fa fa-address-book\",\"child\":[{\"title\":\"Flume任务配置\",\"href\":\"/flumeConfigView/listFlume\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"},{\"title\":\"Flume监控\",\"href\":\"\",\"icon\":\"fa fa-binoculars\",\"target\":\"_self\",\"child\":[{\"title\":\"MysqlToKafka\",\"href\":\"/flumeMonitorView/mysqlToKafka\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"},{\"title\":\"KafkaToKafka\",\"href\":\"/flumeMonitorView/KafkaToKafka\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"Datax任务配置\",\"href\":\"/DataxConfigView/listDataxConfig\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"},{\"title\":\"模板文件配置\",\"href\":\"/fileTemplateView/listFileTemplate\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"},{\"title\":\"Hive元数据管理\",\"href\":\"/hiveMateView/hiveIndex\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"}]}}}";
    }




}
