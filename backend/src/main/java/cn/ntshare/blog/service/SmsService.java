package cn.ntshare.blog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    Boolean sendCaptchaSms(HttpServletResponse response, String phoneNumber, String captchaCode);

    /**
     * 验证短信验证码
     * @param request
     * @param captchaCode
     * @return
     */
    Boolean verifyCaptchaCode(HttpServletRequest request, String captchaCode);
}
