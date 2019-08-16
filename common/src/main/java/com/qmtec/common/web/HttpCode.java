package com.qmtec.common.web;

public enum HttpCode {
    /**
     * 请求成功
     */
    CODE_200(200),
    CODE_300(300),
    /**
     * 参数错误
     */
    CODE_400(400),
    /**
     * 访问未授权
     */
    CODE_401(401),
    /**
     * 访问权限不足
     */
    CODE_403(403),
    /**
     * url无效/资源访问受限
     */
    CODE_404(404),
    /**
     * （冲突）  服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息
     */
    CODE_409(409),
    /**
     * 程序异常
     */
    CODE_500(500),
    /**
     * 请求被禁止
     */
    CODE_501(501);

    private int value;

    private HttpCode(int value) {
        this.value = value;
    }

    public int toValue() {
        return value;
    }
}
