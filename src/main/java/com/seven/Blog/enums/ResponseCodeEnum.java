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
    ERROR(0, "Error"),
    SUCCESS(10, "Success"),

    LOGIN_SUCCESS(11, "登录成功"),
    LOGIN_FAILED(12, "账号或密码不正确"),
    VERIFICATION_CODE_ERROR(13, "验证码错误"),
    FILE_UPLOAD_SUCCESS(14, "文件上传成功"),
    FILE_UPLOAD_FAILED(15, "文件上传失败"),
    FILE_CANNOT_BE_EMPTY(16, "文件不能为空"),
    PERMISSION_DENIED(17, "请登录系统"),
    CONN_FTP_FAIL(18, "连接FTP服务器失败"),
    LOGOUT_SUCCESS(19, "退出登录成功"),

    INSERT_SUCCESS(20, "添加成功"),
    INSERT_FAILED(21, "添加失败"),
    UPDATE_SUCCESS(22, "修改成功"),
    UPDATE_FAILED(23, "修改失败"),
    DELETE_SUCCESS(24, "删除成功"),
    DELETE_FAILED(25, "删除失败"),

    PAGE_NOT_FOUND(404, "页面不存在"),
    SERVER_ERROR(500, "出了小问题，紧急抢救中..."),
    ;

    Integer code;
    String msg;
}
