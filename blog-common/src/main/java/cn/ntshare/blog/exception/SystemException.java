package cn.ntshare.blog.exception;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import lombok.Getter;

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

    public SystemException(Exception e) {
        super(e.getMessage());
        this.code = ResponseCodeEnum.SERVER_ERROR.getCode();
    }
}
