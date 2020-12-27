package com.weather.weatherdataconsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.weather.weatherdataconsumer.config.ProjectDetails;

@SpringBootApplication
public class WeatherDataConsumerApplication {

	@Autowired
	ProjectDetails projectDetails;	
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherDataConsumerApplication.class, args);
	}

	@Bean
	public TopicExchange Exchange() {
		return new TopicExchange(projectDetails.getExachangeName());
	}

	@Bean
	public Queue Queue() {
		return new Queue(projectDetails.getQueueName());
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(Queue()).to(Exchange()).with(projectDetails.getRoutingKey());
	}

	// Below two methods are used for JSON instead of default serialization / de-serialization
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
