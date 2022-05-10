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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqSpringTutorialApplication {

	// Defined topic exchange name
	static final String topicExchangeName = "spring-boot-exchange";
	// Defined queue
	static final String queueName = "spring-boot";
	static final String queueNameForJoo = "JooCaz";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	Queue queueJoo(){
		return new Queue(queueNameForJoo, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.baz");
	}
	@Bean(name = "joocoofaz")
	Binding bindingjoocoofaz(Queue queueJoo, TopicExchange exchange) {
		return BindingBuilder.bind(queueJoo).to(exchange).with("joo.coo.faz");
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											Receiver receiver) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(new MessageListenerAdapter(receiver, receiver.getDefaultReceiverMethod()));
		return container;
	}

	@Bean
	SimpleMessageListenerContainer containerJoo(ConnectionFactory connectionFactory,
												Receiver receiver) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueNameForJoo);
		container.setMessageListener(new MessageListenerAdapter(receiver, receiver.getDefaultReceiverMethod()));
		return container;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSpringTutorialApplication.class, args);
	}

}
