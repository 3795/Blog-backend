package com.seven.Blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Seven.wk
 * Description: 系统响应码枚举
 * Created At 2018/08/06
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    ERROR(10, "Error"),
    SUCCESS(11, "Success"),

    PERMISSION_DENIED(12, "请登录系统"),
    LOGIN_SUCCESS(13, "登录成功"),
    LOGIN_FAILED(14, "账号或密码不正确"),
    LOGOUT_SUCCESS(15, "退出登录成功"),
    VERIFICATION_CODE_ERROR(16, "验证码错误"),
    USER_NOT_EXISTS(18, "用户不存在"),

    FILE_CANNOT_BE_EMPTY(20, "文件不能为空"),
    FILE_UPLOAD_SUCCESS(21, "文件上传成功"),
    FILE_UPLOAD_FAILED(22, "文件上传失败"),
    CONN_FTP_FAIL(24, "连接FTP服务器失败"),

    INSERT_FAILED(30, "添加失败"),
    INSERT_SUCCESS(31, "添加成功"),
    UPDATE_FAILED(32, "更新失败"),
    UPDATE_SUCCESS(33, "更新成功"),
    DELETE_FAILED(34, "删除失败"),
    DELETE_SUCCESS(35, "删除成功"),

    WRONG_PASSWORD(36, "旧密码错误"),

    PAGE_NOT_FOUND(404, "页面不存在"),
    SERVER_ERROR(500, "出了小问题，紧急抢救中..."),
    ;

    Integer code;
    String msg;
}
