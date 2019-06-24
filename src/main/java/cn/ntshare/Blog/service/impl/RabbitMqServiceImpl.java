package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.bo.EmailBO;
import cn.ntshare.Blog.bo.SmsBO;
import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "message";

    @Override
    public void sendEmail(EmailBO email) {
        rabbitTemplate.convertAndSend(exchange, "blog.email", email);
    }

    @Override
    public void sendSms(SmsBO sms) {
        rabbitTemplate.convertAndSend(exchange, "blog.sms", sms);
    }

    @Override
    public void sendNotice(Message message) {
        rabbitTemplate.convertAndSend(exchange, "blog.notice", message);
    }
}
