package com.qmtec.agent.manager;


import com.qmtec.agent.manager.base.BaseManage;
import com.qmtec.agent.manager.http.DataXConfigService;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class DataXConfigManage extends BaseManage<DataXConfigService> {

    /**
     * 通过Id查询数据
     *
     * @param Id
     * @return
     */
    public Map<String, Object> selectOneDataxConfigById(String Id) {

        Map<String, Object> reponseObject = null;
        try {
            this.init();
            String flumeConfigJson = baseService.selectOneDataxConfigById(Id);
            reponseObject =  this.getMapDataByJson(flumeConfigJson);
        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }

        return reponseObject;
    }


    /**
     * 根据Id更新数据(运行状态)
     *
     * @param id
     * @param runstate
     * @return
     */
    public Boolean updateDataXConfigByIdAndRunstate(String id, Integer runstate) {
        Boolean flag = null;
        try {
            this.init();
            String dataXConfigJson = baseService.updateDataXConfigByIdAndRunstate(id,runstate);
            flag = this.getBooleanDataByJson(dataXConfigJson);
        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 判断任务id是否执行 ，true : 可以执行
     * (接收端)
     *
     */
    public Boolean receiveCheckDataXConfigById(String id,Integer runstate) {
        Boolean flag = null;
        try {
            this.init();
            String flumeConfigJson = baseService.receiveCheckDataXConfigById(id,runstate);
            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("校验时发生异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    /**
     * 启动成功后修改状态，添加进程ID、开始时间
     *
     * @param id
     * @param runstate
     * @param processPid
     * @param startupTime
     * @return
     */
    public Boolean updateDataXConfigByProcesspidAndRunstateAndId(
            String id,Integer runstate, Integer processPid,
            Date startupTime) {

        Boolean flag = null;
        try {
            this.init();
            String flumeConfigJson = baseService.updateDataXConfigByProcesspidAndRunstateAndId(
                    id,runstate,  processPid,  startupTime);

            flag = this.getBooleanDataByJson(flumeConfigJson);

        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return flag;
    }

    public Map<String, Object> queryPartialFieldsDataXConfigById(String id) {
        Map<String, Object> reponseObject = null;
        try {
            this.init();
            String flumeConfigJson = baseService.queryPartialFieldsDataXConfigById(id);
            reponseObject =  this.getMapDataByJson(flumeConfigJson);
        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return reponseObject;
    }

    public List<Map<String, Object>> queryDataXConfigByServerIpAndRunstate(String serverIp, Integer runstate) {
        List<Map<String, Object>> reponseObject = null;
        try {
            this.init();
            String flumeConfigJson = baseService.queryDataXConfigByServerIpAndRunstate(serverIp,runstate);
            reponseObject =  this.getListDataByJson(flumeConfigJson);
        } catch (CustomException e) {
            throw new CustomException("执行异常，错误信息:" + e.getMessage(), HttpCode.CODE_500);
        } finally {
            this.close();
        }
        return reponseObject;
    }

}
