package com.seven.Blog.utils;

/**
 * Created By Seven.wk
 * Description: 系统中的常量信息
 * Created At 2018/08/07
 */
public class Const {

    public static final String USER_ID = "userId";

    public static final String CAPTCHA_CODE = "captchaCode";

    /**
     * 文章分类的状态
     */
    public enum CategoryStatus {
        DISABLE(0, "禁用"),
        ABLE(1, "启用"),
        ;
        int code;
        String desc;
        CategoryStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 文章发布状态
     */
    public enum ArticleStatus {
        UNPUBLISHED(0, "未发布"),
        PUBLISHED(1, "已发布"),
        ;
        int code;
        String desc;
        ArticleStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
