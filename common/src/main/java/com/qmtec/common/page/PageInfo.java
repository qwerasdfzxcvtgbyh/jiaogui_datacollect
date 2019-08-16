package com.qmtec.common.page;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * 分页查询参数类
 *
 */
public class PageInfo {

    //包含前端的查询条件
    protected String searchParams;

    protected int page;

    protected int limit;

    public Map<String, Object> getSearchParams() {
        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);
        return paramMap;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    @JsonIgnore
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @JsonIgnore
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
