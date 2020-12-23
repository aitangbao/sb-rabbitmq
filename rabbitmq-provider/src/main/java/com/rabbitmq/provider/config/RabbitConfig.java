package com.rabbitmq.provider.config;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // 队列名称
    @Value("${spring.rabbitmq.company.queue}")
    public String COMPANY_QUEUE;
    // 队列名称
    @Value("${spring.rabbitmq.messageVisit.queue}")
    public String MESSAGE_VISIT_QUEUE;
    // 交换机名称
    @Value("${spring.rabbitmq.exchange}")
    public String EXCHANG;
    // 关键字
    public final static String KEY_MESSAGE_VISIT = "message_visit";

    public final static String KEY_COMPANY = "company";

    // 声明队列
    @Bean("${spring.rabbitmq.company.queue}")
    public Queue companyQueue() {
        return new Queue(COMPANY_QUEUE);
    }

    // 声明队列
    @Bean("${spring.rabbitmq.messageVisit.queue}")
    public Queue messageQueue() {
        return new Queue(MESSAGE_VISIT_QUEUE);
    }

    // 声明交换机
    @Bean("${spring.rabbitmq.exchange}")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANG).durable(true).build();
    }

    // 绑定交换机和队列
    @Bean
    public Binding bindingCompany(@Qualifier("${spring.rabbitmq.exchange}") Exchange exchange, @Qualifier("${spring.rabbitmq.company.queue}") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY_COMPANY).noargs();
    }

    // 绑定交换机和队列
    @Bean
    public Binding bindingMessage(@Qualifier("${spring.rabbitmq.exchange}") Exchange exchange, @Qualifier("${spring.rabbitmq.messageVisit.queue}") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY_MESSAGE_VISIT).noargs();
    }
}