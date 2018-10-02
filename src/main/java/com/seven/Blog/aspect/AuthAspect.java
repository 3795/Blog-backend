package com.seven.Blog.aspect;

import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.utils.ConstUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Seven.wk
 * Description: 用户登录验证切面
 * Created At 2018/08/07
 */
@Component
@Aspect
public class AuthAspect {

    @Pointcut("within(com.seven.Blog.controller.manage.ManageController)")
    public void verify() {}

    @Pointcut("within(com.seven.Blog.controller.api.manage.*Controller)")
    public void apiVerify() {}

    @Around("verify()")
    public Object doVerify(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer userId = (Integer) request.getSession().getAttribute(ConstUtil.USER_ID);
        if(userId == null)
            return new ModelAndView("redirect:/manage/login");
        return joinPoint.proceed();
    }

    @Around("apiVerify()")
    public Object doApiVerify(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer userId = (Integer) request.getSession().getAttribute(ConstUtil.USER_ID);
        if(userId == null)
            return ServerResponse.error(ResponseCodeEnum.PERMISSION_DENIED);
        return joinPoint.proceed();
    }
}
