package com.seven.Blog.enums;

/**
 * Created By Seven.wk
 * Description: 系统响应码枚举
 * Created At 2018/08/06
 */
public enum ResponseCodeEnum {
    ERROR(0, "error"),
    SUCCESS(10, "success"),
    LOGIN_SUCCESS(11, "登录成功"),
    LOGIN_FAILED(12, "账号或密码不正确"),
    VERIFICATION_CODE_ERROR(13, "验证码错误"),
    ;

    int code;
    String msg;
    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
