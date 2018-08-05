package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {

    public static final String AUTO_CLOSE_ORDER_EXCHANGE  = "auto_close_order.exchange";

    public static final String AUTO_CLOSE_ORDER_TTL_QUEUE = "auto_close_order.ttl.queue";
    public static final String AUTO_CLOSE_ORDER_TTL_BIND  = "auto_close_order.ttl.bind";

    public static final String AUTO_CLOSE_ORDER_QUEUE     = "auto_close_order.queue";
    public static final String AUTO_CLOSE_ORDER_BIND      = "auto_close_order.bind";

    @Bean
    public Queue autoCloseOrderTtlQueue() {
        return QueueBuilder
                .durable(AUTO_CLOSE_ORDER_TTL_QUEUE)
                // // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", AUTO_CLOSE_ORDER_EXCHANGE)
                // // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", AUTO_CLOSE_ORDER_BIND)
                .build();
    }

    @Bean
    public DirectExchange autoCloseOrderExchange () {
        return (DirectExchange) ExchangeBuilder
                .directExchange(AUTO_CLOSE_ORDER_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    Binding autoCloseOrderTtlBinding() {
        return BindingBuilder
                .bind(autoCloseOrderTtlQueue())
                .to(autoCloseOrderExchange())
                .with(AUTO_CLOSE_ORDER_TTL_BIND);
    }

    @Bean
    Queue autoCloseOrderQueue() {
        return QueueBuilder
                .durable(AUTO_CLOSE_ORDER_QUEUE)
                .build();
    }

    @Bean
    Binding autoCloseOrderBinding() {
        return BindingBuilder
                .bind(autoCloseOrderQueue())
                .to(autoCloseOrderExchange())
                .with(AUTO_CLOSE_ORDER_BIND);
    }


}
