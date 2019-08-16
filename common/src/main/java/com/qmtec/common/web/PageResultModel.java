package com.qmtec.common.web;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PageResultModel<T>  {

    private Long count = 0L;

    private List<T> data = new ArrayList<>();

    /**
     * 分页查询正常返回：0，Layui的规范
     */
    private int code = HttpCode.CODE_200.toValue();

    private String msg;

    public PageResultModel(Long count, List<T> data, int code, String msg) {
        this.count = count;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public PageResultModel(Long count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    public PageResultModel() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
