package com.seven.Blog.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created By Seven.wk
 * Description: 配置文件工具类
 * Created At 2018/09/21
 */
@PropertySource( value = {"classpath:system.properties"})
@Component
public class PropertiesUtil {

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private Integer port;

    @Value("${imgServerName}")
    private String imgServerName;

    public PropertiesUtil() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getImgServerName() {
        return imgServerName;
    }
}
