package cn.ntshare.blog.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created By Seven.wk
 * Description: Druid的配置属性项
 * Created At 2018/12/29
 */
@ConfigurationProperties(prefix = "spring.druid")
@NoArgsConstructor
@Getter
@Setter
public class DruidProperties {
    private String loginUsername;
    private String loginPassword;
}
