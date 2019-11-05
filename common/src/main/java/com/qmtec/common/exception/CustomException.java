package com.qmtec.common.exception;


import com.qmtec.common.web.HttpCode;

/**
 * 自定义异常类
 *
 * @date 2017/10/28
 */
public class CustomException extends RuntimeException {
    private HttpCode httpCode;
    private Object data;

    public CustomException(String message, HttpCode httpCode, Object data) {
        super(message);
        this.httpCode = httpCode;
        this.data = data;
    }

    public CustomException(String message, HttpCode httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public CustomException(String message) {
        super(message);
        this.httpCode = HttpCode.CODE_400;
    }

    public CustomException(String message, Exception e) {
        super(message + "\n" + e.getMessage());
        this.httpCode = HttpCode.CODE_400;
    }

    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
