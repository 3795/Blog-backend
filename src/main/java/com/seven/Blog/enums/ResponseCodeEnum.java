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
    LOGIN_FAILED(14, "账号或密码不正确"),
    VERIFICATION_CODE_ERROR(16, "验证码错误"),
    LOGIN_SUCCESS(13, "登录成功"),
    LOGOUT_SUCCESS(15, "退出登录成功"),

    FILE_UPLOAD_SUCCESS(17, "文件上传成功"),
    FILE_UPLOAD_FAILED(18, "文件上传失败"),
    FILE_CANNOT_BE_EMPTY(20, "文件不能为空"),
    CONN_FTP_FAIL(22, "连接FTP服务器失败"),

    INSERT_SUCCESS(23, "添加成功"),
    INSERT_FAILED(24, "添加失败"),
    UPDATE_SUCCESS(25, "修改成功"),
    UPDATE_FAILED(26, "修改失败"),
    DELETE_SUCCESS(27, "删除成功"),
    DELETE_FAILED(28, "删除失败"),

    PAGE_NOT_FOUND(40, "页面不存在"),
    SERVER_ERROR(50, "出了小问题，紧急抢救中..."),
    ;

    Integer code;
    String msg;
}
