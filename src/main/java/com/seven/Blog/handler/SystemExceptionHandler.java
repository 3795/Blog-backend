package com.seven.Blog.handler;

import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created By Seven.wk
 * Description: 系统异常处理器
 * Created At 2018/11/10
 */
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServerResponse handleException(Exception e) {
        log.error("系统出错，异常信息：{}", e);
        return ServerResponse.error(ResponseCodeEnum.PAGE_NOT_FOUND);
    }
}
