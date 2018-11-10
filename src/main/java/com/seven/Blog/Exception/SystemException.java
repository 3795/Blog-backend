package com.seven.Blog.Exception;

import com.seven.Blog.enums.ResponseCodeEnum;
import lombok.Getter;

/**
 * Created By Seven.wk
 * Description: 自定义的系统异常
 * Created At 2018/11/10
 */
@Getter
public class SystemException extends RuntimeException {

    private Integer code;

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
    }
}
