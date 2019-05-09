package cn.ntshare.blog.filter;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.util.CookieUtil;
import cn.ntshare.blog.util.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String prefix = "/blog/v1/backend";
        String pattern = "/blog/v1/backend/.*";

        // Backend模块路径
        if (Pattern.matches(pattern, request.getRequestURI())) {
            if ((prefix + "/captcha").equalsIgnoreCase(request.getRequestURI())) {
                return false;
            } else if ((prefix + "/sms").equalsIgnoreCase(request.getRequestURI())) {
                return false;
            } else return !(prefix + "/login").equalsIgnoreCase(request.getRequestURI());
        }
        // File模块路径
//        return Pattern.matches("/blog/v1/file/.*", request.getRequestURI());
        return false;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String token = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new SystemException(ResponseCodeEnum.PERMISSION_DENIED);
        }

        String userJson = RedisUtil.get(token);
        if (StringUtils.isEmpty(userJson)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            try {
                requestContext.getResponse().getWriter().write("No Access");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 登录状态，在管理系统的每一步操作都会重置token过期时间
        RedisUtil.expire(token, SystemConstant.REDIS_EXPIRE_TIME);
        return null;
    }
}
