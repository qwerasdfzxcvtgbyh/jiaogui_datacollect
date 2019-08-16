package com.qmtec.servicecore.config;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import com.qmtec.common.web.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        ResultModel resultModel = new ResultModel(HttpCode.CODE_500, e.getMessage());

        e.printStackTrace();
        log.error("系统异常:[{}]", e.getMessage(),e);
        resultModel.setMessage(e.getMessage());

        return JSONObject.toJSON(resultModel);
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Object logicCustomExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        ResultModel resultModel = new ResultModel(HttpCode.CODE_500, e.getMessage());
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            resultModel.setCode(customException.getHttpCode().toValue());
            resultModel.setMessage(customException.getMessage());
        } else {
            //对系统级异常进行日志记录
            log.error("系统异常:[{}]", e.getMessage(),e);
        }

        return JSONObject.toJSON(resultModel);
    }
}
