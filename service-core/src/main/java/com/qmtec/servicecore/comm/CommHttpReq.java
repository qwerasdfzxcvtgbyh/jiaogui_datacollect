package com.qmtec.servicecore.comm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.util.HttpClientResult;
import com.qmtec.common.util.HttpClientUtil;
import com.qmtec.common.web.HttpCode;

import java.util.HashMap;
import java.util.Map;

/**
 *  公用的，对Http再次包装
 *
 */
public class CommHttpReq {

    public static Map<String, Object> agentRequest(String url, Map<String, String> params) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            HttpClientResult httpClientResult = HttpClientUtil.doPost(url, params);
            String content = httpClientResult.getContent();
            JSONObject jsonObject = JSON.parseObject(content);

            Integer code = (Integer) jsonObject.get("code");
            String message = (String)jsonObject.get("message");
            if(code.intValue() == HttpCode.CODE_200.toValue()){
                map.put("code", code);
                map.put("data", jsonObject.get("data"));
                map.put("message", message);
            }else{
                throw new Exception(message);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return map;
    }

}
