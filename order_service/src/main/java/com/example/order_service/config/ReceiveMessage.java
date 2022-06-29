package com.example.order_service.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.orderservice.config.MessageConfig.QUEUE_ORDER;
@Log4j2
@Component
public class ReceiveMessage {
    @Autowired
    ConsumerService consumerService;
    @RabbitListener(queues = {QUEUE_ORDER})
    public void getMessage(OrderEvent orderEvent) {
        System.out.println(orderEvent);
       consumerService.getMessage(orderEvent);
    }
}
