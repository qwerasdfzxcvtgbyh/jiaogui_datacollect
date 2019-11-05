package com.qmtec.servicecore.service.flume;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.model.bo.FlumeConfigBo;
import com.qmtec.servicecore.model.dto.FlumeConfigDto;
import com.qmtec.servicecore.model.vo.FlumeConfigVo;

import java.util.List;
import java.util.Map;

public interface FlumeConfigService {

    /**
     * 通过ContextId查询数据
     *
     * @param flumeConfigVo
     * @return
     */
    FlumeConfigBo selectOneFlumeConfigByContextId(FlumeConfigVo flumeConfigVo);

    /**
     * 启动完成之后，用来更新数据(进程ID，监控端口，运行状态，启动时间)
     *
     * @param flumeConfigVo
     * @return
     */
    Boolean updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(FlumeConfigVo flumeConfigVo);


    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (推送端)
     *
     * @param contextId
     * @return
     */
    Boolean pushCheckFlumeConfigByContextId(Long contextId);

    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (接收端)
     *
     * @param flumeConfigVo
     * @return
     */
    Boolean receiveCheckFlumeConfigByContextId(FlumeConfigVo flumeConfigVo);

    /**
     * 根据ContextId更新数据(运行状态)
     *
     * @param flumeConfigVo
     * @return
     */
    Boolean updateFlumeConfigByContextIdAndRunstate(FlumeConfigVo flumeConfigVo);

    /**
     * 根据serverIp判断monitorPort是否可用
     *
     * @param flumeConfigVo
     * @return
     */
    Boolean verifyMonitorPortIsAvailableByServerIp(FlumeConfigVo flumeConfigVo);

    /**
     * 获取flume需要进行监控端口访问的数据
     *
     * @return
     */
    List<FlumeConfig> monitoringFlume();

    /**
     * 通过ContextId查询部分字段数据
     *
     * @param flumeConfigVo
     * @return
     */
    FlumeConfigBo queryPartialFieldsFlumeConfigByContextId(FlumeConfigVo flumeConfigVo);


    List<FlumeConfigBo> queryFlumeConfigByServerIpAndRunstate(FlumeConfigVo flumeConfigVo);

    /**----------------------------------------------------------------------------------------*/

    /**
     * 前端主动刷新
     *
     * @param data
     * @return
     */
    Boolean refreshFlumeRunStatus(String data);

    /**
     * 分页查询
     *
     * @return
     */
    ListResult<FlumeConfigDto> listSelectFlumeConfig(String searchParams, int page, int limit);


    /**
     * 添加 、修改
     *
     * @param data
     * @return
     */
    Boolean addOrUpdateFlume(String data);

    /**
     * 跳转修改页面前处理
     *
     * @param contextId
     * @return
     */
    FlumeConfigDto updateFlumeConfigBefore(Long contextId);

    /**
     * 删除Flume的配置信息
     *
     * @param data
     * @return
     */
    Boolean deleteFlumeConfig(String data);

    /**
     * 启动Flume
     *
     * @param data
     * @return
     */
    Map<String, Object> startFlume(String data);

    /**
     * 停止Flume
     *
     * @param data
     * @return
     */
    Map<String, Object> stopFlume(String data);

    /**
     * 查看运行信息前判断
     *
     * @param data
     * @return
     */
    Boolean viewRunInforBefore(String data);

    /**
     * 看运行信息
     *
     * @param contextId
     * @return
     */
    FlumeConfigDto viewRunInfor(Long contextId);

    /**
     * 重启Flume
     *
     * @param data
     * @return
     */
    Map<String, Object> restartFlume(String data);


    /**
     * 根据指定“任务类型”获取ID集合
     *
     * @param type
     * @return
     */
    List<Long> getContextIdsByType(int type);

    /**
     * 根据指定“任务类型”获取实例集合
     *
     */
    List<FlumeConfigDto> getFlumeConfigByType(int type);

    /**
     * 通过contextId查询数据
     *
     */
    FlumeConfig selectOneFlumeConfig(Long contextId);

    /**
     *  通过任务类型、运行状态来获取
     *
     */
    List<FlumeConfig> getFlumeConfigByTypeAndRunState (int type, int runState);
}
