package cn.ntshare.blog.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created By Q.Hao
 * Description: Druid配置项
 * Created At 2019/5/4
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource initDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        List<Filter> filters = new ArrayList<>();
        filters.add(wallFilter);
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }

    /**
     * Druid监控功能页面
     * @param prop
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(DruidProperties prop) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.setUrlMappings(Collections.singletonList("/druid/*"));
        //设置初始化参数
        Map<String,String> initMap = new HashMap<>();
        initMap.put("loginUsername", prop.getLoginUsername());
        initMap.put("loginPassword", prop.getLoginPassword());
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
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        // 设置初始化参数
        Map<String, String> initMap = new HashMap<>();
        initMap.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }
}
