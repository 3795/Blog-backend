package cn.ntshare.blog.enums;

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
    ERROR(10, "失败"),
    SUCCESS(11, "成功"),

    PERMISSION_DENIED(12, "请登录系统"),
    LOGIN_SUCCESS(13, "登录成功"),
    LOGIN_FAILED(14, "账号或密码不正确"),
    LOGOUT_SUCCESS(15, "退出登录成功"),
    VERIFICATION_CODE_ERROR(16, "验证码错误"),
    USER_NOT_EXISTS(18, "用户不存在"),

    FILE_CANNOT_BE_EMPTY(20, "文件不能为空"),
    FILE_UPLOAD_SUCCESS(21, "文件上传成功"),
    FILE_UPLOAD_FAILED(22, "文件上传失败"),
    CONN_FTP_FAILED(24, "连接FTP服务器失败"),
    FILE_DELETE_SUCCESS(25, "文件删除成功"),
    FILE_DELETE_FAILED(26, "文件删除失败"),

    INSERT_FAILED(30, "添加失败"),
    INSERT_SUCCESS(31, "添加成功"),
    UPDATE_FAILED(32, "更新失败"),
    UPDATE_SUCCESS(33, "更新成功"),
    DELETE_FAILED(34, "删除失败"),
    DELETE_SUCCESS(35, "删除成功"),

    WRONG_PASSWORD(36, "旧密码错误"),

    SMS_SEND_ERROR(40, "发送短信时出错了"),
    SMS_SEND_SUCCESS(41, "短信发送成功"),
    SMS_SEND_FAILED(42, "短信发送失败"),
    SMS_CODE_EXPIRED(44, "短信验证码已过期"),
    SMS_CODE_ERROR(46, "短信验证码错误"),


    NO_ACCESS(50, "拒绝访问"),
    IP_NOT_VERIFIED(52, "需要异地登录验证"),
    REQUEST_TOO_FREQUENTLY(54, "请求太频繁，请稍后再试"),
    INVALID_TOKEN(56, "Token已过期，请刷新重试"),

    HTTP_REQUEST_FAILED(58, "HTTP请求发送失败"),


    PAGE_NOT_FOUND(404, "页面不存在"),
    SERVER_ERROR(500, "出了小问题，紧急抢救中..."),
    ;

    Integer code;
    String msg;
}
