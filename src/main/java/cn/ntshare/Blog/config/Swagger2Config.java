package cn.ntshare.Blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created By Seven.wk
 * Description: Swagger2 配置文件
 * Created At 2018/12/27
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ntshare.Blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("seven.wk", "http://www.ntshare.cn", "seven.wk@foxmail.com");
        return new ApiInfoBuilder()
                .title("Blog项目API文档")
                .description("Blog项目API文档")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
