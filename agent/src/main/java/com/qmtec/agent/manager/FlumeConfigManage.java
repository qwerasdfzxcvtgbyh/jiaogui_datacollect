package com.qmtec.agent.manager;


import com.qmtec.agent.manager.base.BaseManage;
import com.qmtec.agent.manager.http.FlumeConfigService;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class FlumeConfigManage extends BaseManage<FlumeConfigService> {

    /**
     * 通过ContextId查询数据
     *
     * @param contextId
     * @return
     */
    public Map<String, Object> selectOneFlumeConfigByContextId(Long contextId) {

        Map<String, Object> reponseObject = null;
        try {
            this.init();

            String flumeConfigJson = baseService.selectOneFlumeConfigByContextId(contextId);

            reponseObject =  this.getMapDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return reponseObject;
    }

    /**
     * 通过ContextId查询数据
     *
     * @param contextId
     * @return
     */
    public Map<String, Object> queryPartialFieldsFlumeConfigByContextId(Long contextId) {

        Map<String, Object> reponseObject = null;
        try {
            this.init();

            String flumeConfigJson = baseService.queryPartialFieldsFlumeConfigByContextId(contextId);

            reponseObject =  this.getMapDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return reponseObject;
    }

    /**
     * 更新数据
     *
     * @param contextId
     * @param monitorPort
     * @param runstate
     * @param processPid
     * @param startupTime
     * @return
     */
    public Boolean updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(
            Long contextId, Integer monitorPort,
            Integer runstate, Integer processPid,
            Date startupTime) {

        Boolean flag = null;
        try {
            this.init();
            String flumeConfigJson = baseService.updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(
                     contextId,  monitorPort,runstate,  processPid,  startupTime);

            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 判断任务ContextId是否执行 ，true : 可以执行
     * (接收端)
     *
     * @param contextId
     * @return
     */
    public Boolean receiveCheckFlumeConfigByContextId(Long contextId,Integer runstate) {
        Boolean flag = null;
        try {
            this.init();

            String flumeConfigJson = baseService.receiveCheckFlumeConfigByContextId(contextId,runstate);

            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
                 throw new CustomException("校验时发生异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 根据ContextId更新数据(运行状态)
     *
     * @param contextId
     * @param runstate
     * @return
     */
    public Boolean updateFlumeConfigByContextIdAndRunstate(Long contextId, Integer runstate) {
        Boolean flag = null;
        try {
            this.init();
            String flumeConfigJson = baseService.updateFlumeConfigByContextIdAndRunstate(contextId,runstate);

            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 根据serverIp判断monitorPort是否可用
     *
     * @param serverIp
     * @param monitorPort
     * @return
     */
    public Boolean verifyMonitorPortIsAvailableByServerIp(String serverIp,Integer monitorPort){
        Boolean flag = null;
        try {
            this.init();

            String flumeConfigJson = baseService.verifyMonitorPortIsAvailableByServerIp(serverIp,monitorPort);

            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息：" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 通过ContextId查询数据
     *
     * @return
     */
    public List<Map<String, Object>> queryFlumeConfigByServerIpAndRunstate(String serverIp,Integer runstate) {
        List<Map<String, Object>> reponseObject = null;
        try {
            this.init();
            String flumeConfigJson = baseService.queryFlumeConfigByServerIpAndRunstate(serverIp,runstate);
            reponseObject =  this.getListDataByJson(flumeConfigJson);
        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return reponseObject;
    }

}
