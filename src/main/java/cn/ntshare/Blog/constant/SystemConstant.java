package cn.ntshare.Blog.constant;

import cn.ntshare.Blog.util.PropertiesUtil;

/**
 * Created By Seven.wk
 * Description: 系统常量
 * Created At 2018/11/10
 */
public class SystemConstant {

    public static final String CAPTCHA_CODE = "captchaCode";

    public static final String COOKIE_DOMAIN_NAME = PropertiesUtil.getProperty("cookie.domain.name", "localhost");

    public static final String LOGIN_TOKEN = "blog_login_token";

    public static final String TIME_STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final Integer REDIS_EXPIRE_TIME = Integer.parseInt(PropertiesUtil.getProperty("redis.expire.time", "1800"));
}
