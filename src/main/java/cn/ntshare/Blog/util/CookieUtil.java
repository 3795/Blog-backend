package cn.ntshare.Blog.util;

import cn.ntshare.Blog.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By Seven.wk
 * Description: Cookie工具
 * Created At 2018/11/11
 */
@Slf4j
public class CookieUtil {

    /**
     * 获取Cookie值
     * @param request
     * @param token
     * @return
     */
    public static String readCookie(HttpServletRequest request, String token) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), token)) {
                    return cookie.getValue();
                }
            }
        }
        log.info("Cookie {} 未找到", token);
        return null;
    }

    /**
     * 向客户端写入Cookie
     * @param response
     * @param token
     * @param value
     */
    public static void writeCookie(HttpServletResponse response, String token, String value) {
        Cookie cookie = new Cookie(token, value);
        cookie.setDomain(SystemConstant.COOKIE_DOMAIN_NAME);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.parseInt(PropertiesUtil.getProperty("cookie.max.age", "86400")));
        response.addCookie(cookie);
    }


    /**
     * 删除Cookie
     * @param request
     * @param response
     * @param token
     */
    public static void delCookie(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String token) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), token)) {
                    cookie.setDomain(SystemConstant.COOKIE_DOMAIN_NAME);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

    }
}
