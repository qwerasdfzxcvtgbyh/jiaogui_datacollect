package com.qmtec.common.status;

import java.io.Serializable;

public class ServiceStatus <T> implements Serializable {
    private T data;
    private StatusCodeEnum codeEnum;

    public ServiceStatus() {
        this.codeEnum = StatusCodeEnum.Fail;
    }

    public ServiceStatus(StatusCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }


    public ServiceStatus(StatusCodeEnum codeEnum, T data) {
        this.codeEnum = codeEnum;
        this.data = data;
    }


    public void setCodeEnum(StatusCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public StatusCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
