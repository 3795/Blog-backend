package cn.ntshare.Blog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码或邮件验证码服务
 */
public interface CaptchaCodeService {

    /**
     * 生成准许发送的token
     * @param account 短信方式的话，account为手机号，邮件方式的话，account为邮箱账号
     * @return
     */
    String[] createSendToken(String account);

    /**
     * 发送验证码
     * @param response
     * @param account 短信方式的话，account为手机号，邮件方式的话，account为邮箱账号
     * @param code 验证码内容
     */
    Boolean sendCaptcha(HttpServletResponse response, String account, String code);

    /**
     * 验证验证码是否正确
     * @param request
     * @param captchaCode
     * @return
     */
    Boolean verifyCaptchaCode(HttpServletRequest request, String captchaCode);


}
