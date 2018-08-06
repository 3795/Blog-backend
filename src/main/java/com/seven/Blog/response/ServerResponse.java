package com.seven.Blog.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.seven.Blog.enums.ResponseCodeEnum;

/**
 * Created By Seven.wk
 * Description: 服务器响应的信息
 * Created At 2018/08/06
 */
public class ServerResponse<T> {

    private int code;       //状态码

    private String msg;         //描述信息

//    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private T data;             //返回的数据信息

    private ServerResponse() {}

    private ServerResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    private ServerResponse(ResponseCodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    /**
     * 返回成功信息
     *
     * @param success
     * @param msg       成功描述
     * @return
     */
    public static ServerResponse success(ResponseCodeEnum success, String msg) {
        return new ServerResponse(ResponseCodeEnum.SUCCESS.getCode(), msg);
    }

    /**
     * 返回成功信息
     * @param codeEnum
     * @return
     */
    public static ServerResponse success(ResponseCodeEnum codeEnum) {
        return new ServerResponse(codeEnum);
    }

    /**
     * 返回成功并且附加返回信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse success(T data) {
        return new ServerResponse<>(ResponseCodeEnum.SUCCESS, data);
    }

    /**
     *返回失败信息
     * @param msg
     * @return
     */
    public static ServerResponse error(String msg) {
        return new ServerResponse(ResponseCodeEnum.ERROR.getCode(), msg);
    }

    /**
     * 返回失败信息
     * @param codeEnum
     * @return
     */
    public static ServerResponse error(ResponseCodeEnum codeEnum) {
        return new ServerResponse(codeEnum);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
