package cn.ntshare.Blog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue("email");
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("sms");
    }

    @Bean
    public Queue noticeQueue() {
        return new Queue("notice");
    }

    @Bean
    public TopicExchange messageExchange() {
        return new TopicExchange("message");
    }

    @Bean
    Binding bindingExchangeEmail(Queue emailQueue, TopicExchange messageExchange) {
        return BindingBuilder.bind(emailQueue).to(messageExchange).with("blog.email");
    }

    @Bean
    Binding bindingExchangeSms(Queue smsQueue, TopicExchange messageExchange) {
        return BindingBuilder.bind(smsQueue).to(messageExchange).with("blog.sms");
    }

    @Bean
    Binding bindingExchangeNotice(Queue noticeQueue, TopicExchange messageExchange) {
        return BindingBuilder.bind(noticeQueue).to(messageExchange).with("blog.notice");
    }
}
