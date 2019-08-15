package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.bo.EmailBO;
import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.service.CaptchaCodeService;
import cn.ntshare.Blog.service.MailService;
import cn.ntshare.Blog.service.RabbitMqService;
import cn.ntshare.Blog.util.CookieUtil;
import cn.ntshare.Blog.util.RandomUtil;
import cn.ntshare.Blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By Seven.wk
 * Description: 邮件服务实现
 * Created At 2019/01/08
 */
@Service
public class MailServiceImpl implements MailService, CaptchaCodeService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private RabbitMqService rabbitMqService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Boolean sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(this.from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

        return true;
    }

    @Override
    public String[] createSendToken(String account) {
        String emailToken = RandomUtil.getUniqueKey();
        RedisUtil.setExpireTime(emailToken, account, 10 * SystemConstant.MINUTE);
        return new String[]{account, emailToken};
    }

    @Override
    public Boolean sendCaptcha(HttpServletResponse response, String account, String captchaCode) {
        String key = RandomUtil.getUniqueKey();
        // 写入Redis
        RedisUtil.setExpireTime(key, captchaCode, SystemConstant.EMAIL_EXPIRE_TIME);
        // 写入Cookie
        CookieUtil.writeCookie(response, SystemConstant.EMAIL_TOKEN, key, SystemConstant.EMAIL_EXPIRE_TIME);

        // 发送邮件
        EmailBO emailBO = new EmailBO(account,
                "异地登录验证",
                "您正尝试异地登录系统，你的验证码为 " + captchaCode + " ," +
                "有效期为 " + (SystemConstant.EMAIL_EXPIRE_TIME/60) + "分钟");
        rabbitMqService.sendEmail(emailBO);

        // 将邮箱账号写入redis防刷
        RedisUtil.setExpireTime(account, "1", SystemConstant.MINUTE);

        return true;
    }

    @Override
    public Boolean verifyCaptchaCode(HttpServletRequest request, String captchaCode) {
        String key = CookieUtil.readCookie(request, SystemConstant.EMAIL_TOKEN);
        if (key == null) {
            throw new SystemException(ResponseCodeEnum.EMAIL_CODE_EXPIRED);
        }

        String value = RedisUtil.get(key);
        if (value == null) {
            throw new SystemException(ResponseCodeEnum.EMAIL_CODE_EXPIRED);
        }

        if (!value.equals(captchaCode)) {
            throw new SystemException(ResponseCodeEnum.EMAIL_CODE_ERROR);
        }

        return true;
    }
}
