package cn.ntshare.Blog.vo;

import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created By Seven.wk
 * Description: 服务器响应的信息
 * Created At 2018/08/06
 */
@Getter
@ToString
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = -4929052749136069903L;

    private Integer code;       //状态码

    private String msg;         //描述信息

    private T data;             //返回的数据信息

    private ServerResponse() {}

    private ServerResponse(Integer code, String msg) {
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
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse success(T data) {
        return new ServerResponse<>(ResponseCodeEnum.SUCCESS, data);
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
    public static <T> ServerResponse success(ResponseCodeEnum codeEnum, T data) {
        return new ServerResponse<>(codeEnum, data);
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

    /**
     * 返回失败信息
     * @param systemException
     * @return
     */
    public static ServerResponse error(SystemException systemException) {
        return new ServerResponse(systemException.getCode(), systemException.getMessage());
    }


}
