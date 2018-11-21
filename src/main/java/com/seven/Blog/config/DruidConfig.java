package com.seven.Blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Seven.wk
 * Description: Druid连接池的配置文件
 * Created At 2018/11/20
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * Druid监控功能的页面设置
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        //设置初始化参数
        Map<String,String> initMap = new HashMap<>();
        initMap.put("loginUsername","admin");
        initMap.put("loginPassword","123456");
        initMap.put("allow","");
        initMap.put("deny","");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }

    /**
     * Druid监控功能的过滤器设置
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        //设置初始化参数
        Map<String,String> initMap = new HashMap<>();
        initMap.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }
}
