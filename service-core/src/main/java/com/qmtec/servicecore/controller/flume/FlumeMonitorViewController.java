package com.qmtec.servicecore.controller.flume;


import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.comm.FlumeTaskType;
import com.qmtec.servicecore.model.dto.ChannelInfoDto;
import com.qmtec.servicecore.model.dto.SinkInfoDto;
import com.qmtec.servicecore.model.dto.SourceInfoDto;
import com.qmtec.servicecore.service.flume.FlumeConfigService;
import com.qmtec.servicecore.service.flumeMonitor.ChannleInfoService;
import com.qmtec.servicecore.service.flumeMonitor.FlumeMonitorService;
import com.qmtec.servicecore.service.flumeMonitor.SinkInfoService;
import com.qmtec.servicecore.service.flumeMonitor.SourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * FlumeMonitor
 * 控制器
 */
@Controller
@RequestMapping("/flumeMonitorView")
public class FlumeMonitorViewController {

    @Autowired
    private SourceInfoService sourceInfoService;
    @Autowired
    private ChannleInfoService channleInfoService;
    @Autowired
    private SinkInfoService sinkInfoService;

    @Autowired
    private FlumeConfigService flumeConfigService;
    @Autowired
    private FlumeMonitorService flumeMonitorService;

    @RequestMapping(value = "/mysqlToKafka")
    public ModelAndView mysqlToKafka() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flumeCfigs",flumeConfigService.getFlumeConfigByType(
                FlumeTaskType.MysqlToKafka.value()
        ));
        modelAndView.setViewName("/flumeMonitor/mysqlTokafka");
        return modelAndView;
    }

    @RequestMapping(value = "/KafkaToHdfs")
    public ModelAndView KafkaToHdfs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flumeCfigs",flumeConfigService.getFlumeConfigByType(
                FlumeTaskType.KafkaToHdfs.value()
        ));
        modelAndView.setViewName("/flumeMonitor/kafkaTohdfs");
        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectSoureceInfo/{type}", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<SourceInfoDto> listSelectSoureceInfo(String searchParams,int page, int limit, @PathVariable(name = "type") int type) {

        PageResultModel<SourceInfoDto> pageResultModel = new PageResultModel<SourceInfoDto>();
        ListResult<SourceInfoDto> listResult = sourceInfoService.listSelectSoureceInfo( searchParams,page, limit,type);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectChannelInfo/{type}", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<ChannelInfoDto> listSelectChannelInfo( String searchParams,int page, int limit, @PathVariable(name = "type") int type) {

        PageResultModel<ChannelInfoDto> pageResultModel = new PageResultModel<ChannelInfoDto>();
        ListResult<ChannelInfoDto> listResult = channleInfoService.listSelectChannelInfo( searchParams,page, limit,type);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listSelectSinkInfo/{type}", method = RequestMethod.POST)
    @ResponseBody
    public PageResultModel<SinkInfoDto> listSelectSinkInfo(String searchParams, int page, int limit, @PathVariable(name = "type") int type) {

        PageResultModel<SinkInfoDto> pageResultModel = new PageResultModel<SinkInfoDto>();
        ListResult<SinkInfoDto> listResult = sinkInfoService.listSelectSinkInfo(searchParams, page, limit,type);
        pageResultModel.setData(listResult.getResult());
        pageResultModel.setCount(listResult.getTotal());
        pageResultModel.setMsg("查询成功");
        return pageResultModel;
    }

    /**
     * 前端主动刷新
     *
     */
    @RequestMapping(value = "/autoRefreshMonitor/{type}", method = {RequestMethod.GET})
    @ResponseBody
    public ResultModel<Boolean> autoRefreshMonitor(@PathVariable(name = "type") int type) {

        ResultModel<Boolean> resultModel = new ResultModel<>();
        resultModel.setData(flumeMonitorService.autoRefreshMonitor(type));
        resultModel.setMessage("执行成功");

        return resultModel;
    }

}
