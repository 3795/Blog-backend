package cn.ntshare.Blog.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created By Seven.wk
 * Description: 读取配置文件的工具类
 * Created At 2018/11/10
 */
@Slf4j
public class PropertiesUtil {

    private static Properties properties;

    static {
        String fileName = "system-dev.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class
                    .getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取失败", e);
        }
    }


    /**
     * 根据key值读取属性
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 根据key值读取属性
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key.trim(), defaultValue);
        if (StringUtils.isBlank(value)) {
            return defaultValue.trim();
        }
        return value.trim();
    }

}

