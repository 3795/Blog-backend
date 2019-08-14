package cn.ntshare.Blog.config;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.service.CaptchaCodeService;
import cn.ntshare.Blog.service.impl.MailServiceImpl;
import cn.ntshare.Blog.service.impl.SmsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CaptchaCodeService captchaCodeService() {
        if (SystemConstant.LOGIN_SEND_METHOD.equals("sms")) {
            return new SmsServiceImpl();
        } else {
            return new MailServiceImpl();
        }
    }
}
