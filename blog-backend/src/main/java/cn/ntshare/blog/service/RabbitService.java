package cn.ntshare.blog.service;

import cn.ntshare.blog.bo.Sms;
import cn.ntshare.blog.bo.Email;
import cn.ntshare.blog.pojo.Message;

public interface RabbitService {

    void sendEmail(Email email);

    void sendCaptchaSms(Sms captchaSms);

    void sendNotice(Message message);
}
