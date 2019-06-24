package cn.ntshare.Blog.service;

import cn.ntshare.Blog.bo.EmailBO;
import cn.ntshare.Blog.bo.SmsBO;
import cn.ntshare.Blog.pojo.Message;

public interface RabbitMqService {

    void sendEmail(EmailBO email);

    void sendSms(SmsBO sms);

    void sendNotice(Message message);
}
