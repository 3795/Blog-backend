package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 邮件服务实现
 * Created At 2019/01/08
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

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
}
