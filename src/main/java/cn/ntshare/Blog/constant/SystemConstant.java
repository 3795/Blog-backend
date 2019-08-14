package cn.ntshare.Blog.constant;

import cn.ntshare.Blog.util.PropertiesUtil;

/**
 * Created By Seven.wk
 * Description: 系统常量
 * Created At 2018/11/10
 */
public class SystemConstant {

    // 验证码
    public static final String CAPTCHA_CODE = "captchaCode";

    // Cookie所属域名
    public static final String COOKIE_DOMAIN_NAME = PropertiesUtil.getProperty("cookie.domain.name", "localhost");

    // Cookie有效期
    public static final Integer COOKIE_MAX_TIME = Integer.parseInt(PropertiesUtil.getProperty("cookie.max.age", "86400"));

    // 身份验证Token
    public static final String LOGIN_TOKEN = "authToken";

    // 时间戳格式
    public static final String TIME_STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Redis存放数据的有效时间
    public static final Integer LOGIN_EXPIRE_TIME = Integer.parseInt(PropertiesUtil.getProperty("redis.expire.time", "1800"));

    // 短信验证Token
    public static final String SMS_TOKEN = "smsToken";

    // 邮箱验证Token
    public static final String EMAIL_TOKEN = "emailToken";

    // 短信验证码有效期
    public static final Integer SMS_EXPIRE_TIME = Integer.parseInt(PropertiesUtil.getProperty("sms.expire.time", "180"));

    // 邮件验证码有效期
    public static final Integer EMAIL_EXPIRE_TIME = 180;

    public static final Integer MINUTE = 60;

    // 文章缓存前缀
    public static final String ARTICLE_CACHE_PREFIX = "articleId:";

    // 文章链接索引
    public static final String INDEX_LINKS = "indexLinks";

    // 网站的域名URL
    public static final String WEB_URL = PropertiesUtil.getProperty("web.url");

    // 向百度搜索提交链接的URL
    public static final String BAIDU_LINK_SUBMISSION_URL = PropertiesUtil.getProperty("baidu.link.submission.url");

    // 允许同一个用户同时登录几个平台
    public static final Integer ALLOW_LOGIN_NUMBER = Integer.parseInt(PropertiesUtil.getProperty("login.number", "1"));

    // 通过何种方式向用户发送登录验证码
    public static final String LOGIN_SEND_METHOD = PropertiesUtil.getProperty("login.sendMethod", "email");

    public static final String LOGIN_LIST_PREFIX = "login_list:";

    public static final String REDIS_LOCK_KEY = "lock";

    public static final Integer REDIS_LOCK_TIME = 30;

    public static final String RUNTIME_DAYS = "runtime:days";

    public static final String RUNTIME_SECONDS = "runtime:seconds";

}
