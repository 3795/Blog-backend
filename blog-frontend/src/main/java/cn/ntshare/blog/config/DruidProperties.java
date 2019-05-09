package cn.ntshare.blog.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created By Q.Hao
 * Description: Druid属性
 * Created At 2019/5/4
 */
@ConfigurationProperties(prefix = "spring.druid")
@NoArgsConstructor
@Data
public class DruidProperties {
    private String loginUsername;
    private String loginPassword;
}
