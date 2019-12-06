package com.rabbitmq.provider.config;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // 队列名称
    public final static String QUEUE = "test_queue2";
    // 交换机名称
    public final static String EXCHANG = "test_exchange2";
    // 关键字
    public final static String KEY = "test_key2";

    // 声明队列
    @Bean(QUEUE)
    public Queue queue() {
        return new Queue(QUEUE);
    }

    // 声明交换机
    @Bean(EXCHANG)
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANG).durable(true).build();
    }

    // 绑定交换机和队列
    @Bean
    public Binding binding(@Qualifier(EXCHANG) Exchange exchange,@Qualifier(QUEUE) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY).noargs();
    }
}