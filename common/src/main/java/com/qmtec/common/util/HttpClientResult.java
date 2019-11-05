package com.qmtec.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description: 封装httpClient响应结果
 */

@AllArgsConstructor
@NoArgsConstructor
public class HttpClientResult implements Serializable {
    private static final long serialVersionUID = 320388803801193203L;

    /**
     * 响应状态码
     */
    @Getter
    @Setter
    private int code;

    /**
     * 响应数据
     */
    @Getter
    @Setter
    private String content;

    public HttpClientResult(int code) {
        this.code = code;
    }
}
