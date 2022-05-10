package com.github.theCoolerSuptelov.rabbitMQSpringTutorial.RabbitMQSpringTutorial;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqSpringTutorialApplication {

	// Defined topic exchange name
	static final String topicExchangeName = "spring-boot-exchange";
	// Defined queue
	static final String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	/**
	 Receiver receiver - our Receiver object
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		// Default listener method - our public method, defined in class Receiver
		// redefine hardcoded string to get attribute receiver object
		return new MessageListenerAdapter(receiver, receiver.getDefaultReceiverMethod());
	}
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSpringTutorialApplication.class, args);
	}

}
