package com.rabbitmq.cosumer.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listen {
    @RabbitListener(queues = {"test_queue2"})
    public void listen(String msg){
        log.info(msg);
    }
}
