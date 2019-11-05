package com.qmtec.common.web;


import com.qmtec.common.exception.CustomException;

public class ResultModel<T> {
    private int code = HttpCode.CODE_200.toValue();
    private String message;
    private int subCode;
    private T data;
    private int count ;

    public ResultModel() {
        super();
        this.message = "";
    }

    public ResultModel(T data) {
        this.data = data;
        this.message = "";
    }

    public ResultModel(T data,int count) {
        this.data = data;
        this.message = "";
        this.count = count;
    }


    public ResultModel(HttpCode code, String message) {
        this.code = code.toValue();
        this.message = message;
    }

    public ResultModel(HttpCode code, String message, T data) {
        this.code = code.toValue();
        this.message = message;
        this.data = data;
    }

    public ResultModel(HttpCode code, String message, T data, SubCode subCode) {
        this.code = code.toValue();
        this.message = message;
        this.data = data;
        this.subCode = subCode.toValue();
    }


    public ResultModel(CustomException ex) {
        this.code = ex.getHttpCode().toValue();
        this.message = ex.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getSubCode() {
        return subCode;
    }

    public void setSubCode(int subCode) {
        this.subCode = subCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * ADD BY liaochou
     */
    public enum SubCode {

        //失败
        Fail(1),
        //参数不能为空
        ParamsIsEmpty(1001),
        //没有权限执行此操作
        NoPermission(1002),
        //无效的参数
        ParamsInvalid(1003),
        //重复的名称
        DuplicatedName(1004),
        //用户
        //编码名称已经存在
        OrganizationExistFailed(5002),
        //该账号已经被使用了
        LoginNameExistFailed(5003),
        //该邮箱已经注册过了
        UserEmailExistFailed(5004),
        //邮箱格式不正确
        UserEmailFormatFailed(5007),
        //两次密码输入不一致
        TwicePwdImparityFailed(5008),
        //原密码输入不正确
        OldPwdMatchingFailed(5011),
        //新旧密码一致
        NewOldPwdIdenticalFailed(5009),
        //登录凭证过期, 需要重新登录
        UserTokenExpired(5009),
        //登录名或者密码为空
        NameOrPasswordIsEmpty(5010),
        //用户不存在
        UserNotExists(5011);

        private int value;

        private SubCode(int value) {
            this.value = value;
        }

        public int toValue() {
            return value;
        }
    }

    public boolean isSuccess() {
        return this.code == HttpCode.CODE_200.toValue();
    }
}
