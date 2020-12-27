package com.weather.weatherdataconsumer.rabbitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.weather.weatherdataconsumer.WeatherDataConsumerApplication;
import com.weather.weatherdataconsumer.config.ProjectDetails;
import com.weather.weatherdataconsumer.service.WeatherDataService;

@Service
public class WeatherDataMessageListener {

	private static final Logger log = LoggerFactory.getLogger(WeatherDataMessageListener.class);

	private final WeatherDataService weatherDataService;
	
	private final ProjectDetails projectDetails;
	
	WeatherDataMessageListener(WeatherDataService weatherDataService, ProjectDetails projectDetails){
		this.weatherDataService = weatherDataService;
		this.projectDetails = projectDetails;
	}
	
	@RabbitListener(queues = "appQueue")
	public void receiveMessage(String data) throws IOException {
		
		log.info("Message" +data);
	    // if no message, reject the payload.
	    if (data == null) {
	      throw new AmqpRejectAndDontRequeueException("Received message is null");
	    }
	    
	    String[]  weatherDatafields= data.split("\\|");
	    
	    if(weatherDatafields.length != 4) {
	    	throw new AmqpRejectAndDontRequeueException("Received message is not in expected Format");	
	    }
	    weatherDataService.covertMessage(weatherDatafields);
	}
	
}
	