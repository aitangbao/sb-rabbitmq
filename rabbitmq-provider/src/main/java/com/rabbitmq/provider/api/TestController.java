package com.rabbitmq.provider.api;

import com.rabbitmq.provider.config.RabbitConfig;
import com.rabbitmq.provider.domin.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/")
@Component
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    // 交换机名称
    @Value("${spring.rabbitmq.exchange}")
    public String EXCHANG;

    @RequestMapping(value = "sender")
    @ResponseBody
    public String test() {
        User user = User.builder().username("name").password("password").build();
        rabbitTemplate.convertAndSend(EXCHANG, RabbitConfig.KEY_COMPANY, user.toString());
        return "success";
    }
}
