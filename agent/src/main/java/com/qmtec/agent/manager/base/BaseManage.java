package com.qmtec.agent.manager.base;

import com.alibaba.fastjson.JSONObject;
import com.github.dadiyang.httpinvoker.HttpApiProxyFactory;
import com.github.dadiyang.httpinvoker.requestor.ResponseProcessor;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class BaseManage<T> {

    @Value("${serviceCore.host}")
    public String serviceCoreHost; //serviceCore-Host端的Ip

    public T baseService = null;

    public void init() {
        ResponseProcessor flumeProcessor = (response, getData) -> {
            return response.getBody();
        };
        System.setProperty("serviceCore.host", serviceCoreHost);

        Class<T> rawType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        baseService = HttpApiProxyFactory.newProxy(rawType, flumeProcessor);

    }

    public void close() {
        if (baseService != null) {
            baseService = null;
        }
    }

    public Map<String, Object> getMapDataByJson(String baseJson){
        Map<String, Object> reponseObject = null;

        JSONObject baseObject = JSONObject.parseObject(baseJson);

        Integer reponseCode = (Integer) baseObject.get("code");

        if (HttpCode.CODE_200.toValue() == reponseCode) {
            reponseObject = (Map<String, Object>) baseObject.get("data");
        } else {
            throw new CustomException("请求数据异常");
        }
        return reponseObject ;
    }

    public Boolean getBooleanDataByJson(String baseJson){
        Boolean flag = null;

        JSONObject baseObject = JSONObject.parseObject(baseJson);

        Integer reponseCode = (Integer) baseObject.get("code");

        if (HttpCode.CODE_200.toValue() == reponseCode) {
            flag = (Boolean) baseObject.get("data");
        } else {
            throw new CustomException("请求数据异常", HttpCode.CODE_400);
        }
        return flag ;
    }

    public List<Map<String, Object>> getListDataByJson(String baseJson){
        List<Map<String, Object>> reponseObject = null;

        JSONObject baseObject = JSONObject.parseObject(baseJson);
        Integer reponseCode = (Integer) baseObject.get("code");

        if (HttpCode.CODE_200.toValue() == reponseCode) {
            reponseObject = (List<Map<String, Object>>) baseObject.get("data");
        } else {
            throw new CustomException("请求数据异常");
        }
        return reponseObject ;
    }

}

