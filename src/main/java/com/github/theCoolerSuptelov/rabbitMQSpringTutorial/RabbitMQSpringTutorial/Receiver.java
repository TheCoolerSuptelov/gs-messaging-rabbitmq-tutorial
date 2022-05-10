package com.github.theCoolerSuptelov.rabbitMQSpringTutorial.RabbitMQSpringTutorial;

import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Scope("prototype")
public class Receiver {
    private String title = UUID.randomUUID().toString();
    private final String defaultReceiverMethod = "receiveMessage";

    public Receiver() {
    }

    public String getDefaultReceiverMethod() {
        return defaultReceiverMethod;
    }
    public void receiveMessage(String message){
        System.out.println("Received: " + this.title + message);
    }
}
