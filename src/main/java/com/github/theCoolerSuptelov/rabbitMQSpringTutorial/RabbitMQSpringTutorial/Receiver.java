package com.github.theCoolerSuptelov.rabbitMQSpringTutorial.RabbitMQSpringTutorial;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public String getDefaultReceiverMethod() {
        return defaultReceiverMethod;
    }

    private final String defaultReceiverMethod = "receiveMessage";

    public void receiveMessage(String message){
        System.out.println("Received: " + message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
