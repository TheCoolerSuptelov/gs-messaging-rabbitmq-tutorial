package com.github.theCoolerSuptelov.rabbitMQSpringTutorial.RabbitMQSpringTutorial;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    public Runner(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        int countDown = 0;
        while (countDown < 100){
            String routingKey;
            if (countDown % 2 == 0) {
                routingKey = "foo.bar.baz";
            } else {
                routingKey = "joo.coo.faz";
            }
            rabbitTemplate.convertAndSend(RabbitMqSpringTutorialApplication.topicExchangeName, routingKey, "Hello from RabbitMQ!" + countDown);
            countDown++;
        }
    }
}
