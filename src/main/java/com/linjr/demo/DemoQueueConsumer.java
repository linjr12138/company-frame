package com.linjr.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "demoQueue")
public class DemoQueueConsumer {
    @RabbitHandler
    public void recieved(String msg) {

        System.out.println("[demoQueue] recieved message: " + msg);
    }
}
