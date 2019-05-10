package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.bo.Sms;
import cn.ntshare.blog.bo.Email;
import cn.ntshare.blog.pojo.Message;
import cn.ntshare.blog.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private final String exchange = "message";

    // todo 做RabbitMQ发送消息失败后的处理
    // 参考：https://blog.csdn.net/m0_37556444/article/details/82631370

    @Override
    public void sendEmail(Email email) {
        rabbitTemplate.convertAndSend(exchange, "blog.email", email);
    }

    @Override
    public void sendCaptchaSms(Sms sms) {
        rabbitTemplate.convertAndSend(exchange, "blog.sms", sms);
    }

    @Override
    public void sendNotice(Message message) {
        rabbitTemplate.convertAndSend(exchange, "blog.notice", message);
    }
}
