package cn.ntshare.Blog.aspect;

import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.util.CookieUtil;
import cn.ntshare.Blog.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Seven.wk
 * Description: 用户登录验证切面
 * Created At 2018/08/07
 */
@Aspect
@Component
public class AuthAspect {

    /**
     * 访问管理系统Api时权限验证
     */
    @Pointcut("within(cn.ntshare.Blog.controller.api.v1.backend.*ControllerV1) &&" +
            "!within(cn.ntshare.Blog.controller.api.v1.backend.BLoginControllerV1)")
    public void apiVerify() {}

    @Around("apiVerify()")
    public Object doApiVerify(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new SystemException(ResponseCodeEnum.PERMISSION_DENIED);
        }

        String userJson = RedisUtil.get(token);
        if (StringUtils.isEmpty(userJson)) {
            throw new SystemException(ResponseCodeEnum.PERMISSION_DENIED);
        }

        // 登录状态，在管理系统的每一步操作都会重置token过期时间
        RedisUtil.expire(token, SystemConstant.REDIS_EXPIRE_TIME);

        return joinPoint.proceed();
    }
}
