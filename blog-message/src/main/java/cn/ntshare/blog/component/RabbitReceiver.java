package cn.ntshare.blog.component;

import cn.ntshare.blog.bo.Sms;
import cn.ntshare.blog.bo.Email;
import cn.ntshare.blog.service.MailService;
import cn.ntshare.blog.service.MessageService;
import cn.ntshare.blog.service.SmsService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitReceiver {

    @Autowired
    private MailService mailService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SmsService smsService;

    @RabbitListener(queues = "email")
    public void emailProcess(Email email, Channel channel, Message message) {
        mailService.sendMail(email.getTo(), email.getSubject(), email.getContent());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "sms")
    public void smsProcess(Sms sms, Channel channel, Message message) {
        smsService.sendCaptchaSms(sms.getPhone(), sms.getContent());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "notice")
    public void noticeProcess(cn.ntshare.blog.pojo.Message msg, Channel channel, Message message) {
        messageService.insert(msg.getTitle(), msg.getContent(), msg.getCreateTime());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
