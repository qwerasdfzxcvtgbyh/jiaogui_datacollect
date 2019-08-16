package com.qmtec.common.page;

import java.util.ArrayList;
import java.util.List;

public class ListResult<T> {
    private Long total = 0L;

    private List<T> result = new ArrayList<>();

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListResult() {
        super();
    }

    public ListResult(List<T> result) {
        this.result = result;
    }


    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotal() {
        return total == 0L ? result.size() : total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
