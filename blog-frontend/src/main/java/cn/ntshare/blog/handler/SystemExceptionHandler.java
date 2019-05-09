package cn.ntshare.blog.handler;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SystemExceptionHandler {

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse handleSystemException(SystemException se) {
        log.warn("系统异常，异常代码：{}, 异常信息： {}", se.getCode(), se.getMessage());
        return ServerResponse.error(se);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse handleException(Exception e) {
        log.error("系统出错，异常信息：{}", e);
        return ServerResponse.error(ResponseCodeEnum.SERVER_ERROR);
    }
}
