package cn.ntshare.Blog.component;

import cn.ntshare.Blog.bo.EmailBO;
import cn.ntshare.Blog.bo.SmsBO;
import cn.ntshare.Blog.service.MailService;
import cn.ntshare.Blog.service.MessageService;
import cn.ntshare.Blog.service.SmsService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RabbitMqReceiver {

    @Autowired
    private MailService mailService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SmsService smsService;

    @RabbitListener(queues = "email")
    public void emailProcess(EmailBO email, Channel channel, Message message) throws IOException {
        mailService.sendMail(email.getTo(), email.getSubject(), email.getContent());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "sms")
    public void smsProcess(SmsBO sms, Channel channel, Message message) throws IOException {
        smsService.sendSms(sms.getSmsType(), sms.getPhone(), sms.getContent());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "notice")
    public void noticeProcess(cn.ntshare.Blog.pojo.Message msg, Channel channel, Message message) throws IOException {
        messageService.insert(msg.getTitle(), msg.getContent());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
