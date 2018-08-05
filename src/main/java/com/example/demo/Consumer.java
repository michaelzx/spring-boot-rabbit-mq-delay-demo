package com.example.demo;

import com.example.demo.config.RabbitMQ;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQ.AUTO_CLOSE_ORDER_QUEUE)
public class Consumer {
    @RabbitHandler
    public void handler(String content) {
        System.out.println(content);
    }

}
