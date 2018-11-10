package com.seven.Blog.util;

/**
 * Created By Seven.wk
 * Description: 系统中的常量信息
 * Created At 2018/08/07
 */
public class ConstUtil {

    public static final String USER_ID = "userId";

    public static final String CAPTCHA_CODE = "captchaCode";

    public static final String DEFAULT_CATEGORY_NAME = "暂无分类";
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

    public enum NavigationStatus {
        DISABLE(0, "禁用"),
        ABLE(1, "启用"),
        ;
        int code;
        String desc;
        NavigationStatus(int code, String desc) {
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

    /**
     * Ueditor的配置文件
     * @return
     */
    public static String ueditorConfig() {
        return "{\n" +
                "        \"imageActionName\": \"uploadimage\",\n" +
                "            \"imageFieldName\": \"file\",\n" +
                "            \"imageMaxSize\": 2048000,\n" +
                "            \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "        \"imageCompressEnable\": true,\n" +
                "            \"imageCompressBorder\": 1600,\n" +
                "            \"imageInsertAlign\": \"none\",\n" +
                "            \"imageUrlPrefix\": \"\",\n" +
                "            \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "\n" +
                "        /* 上传文件配置 */\n" +
                "        \"fileActionName\": \"uploadfile\",\n" +
                "            \"fileFieldName\": \"file\",\n" +
                "            \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "            \"fileUrlPrefix\": \"\",\n" +
                "            \"fileMaxSize\": 51200000,\n" +
                "            \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "                \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "                \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "                \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "                \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"]\n" +
                "    }";
    }


}
