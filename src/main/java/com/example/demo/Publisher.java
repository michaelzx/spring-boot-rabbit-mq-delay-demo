package com.example.demo;

import com.example.demo.config.RabbitMQ;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: cunzhi
 * Date: 2018-08-02
 * Time: 15:39
 */
@Component
public class Publisher {

    private static final long delayTimes = 20 * 1000;

    @Autowired
    private AmqpTemplate rabbitMqTemplate;

    public void sendAutoCloseOrderMessage(Object messageContent) {
        // 执行发送消息到指定队列
        rabbitMqTemplate.convertAndSend(RabbitMQ.AUTO_CLOSE_ORDER_EXCHANGE, RabbitMQ.AUTO_CLOSE_ORDER_TTL_BIND, messageContent, message -> {
            // 设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        });
    }


}
