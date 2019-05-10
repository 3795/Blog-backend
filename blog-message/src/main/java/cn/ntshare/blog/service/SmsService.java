package cn.ntshare.blog.service;

/**
 * Created By Seven.wk
 * Description: 短信服务
 * Created At 2019/01/09
 */
public interface SmsService {

    /**
     * 发送短信验证码
     * @param phoneNumber       电话号码
     * @param captchaCode       验证码内容
     * @return
     */
    Boolean sendCaptchaSms(String phoneNumber, String captchaCode);

}
