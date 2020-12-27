package com.weather.weatherdataconsumer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.weather.weatherdataconsumer.dto.ObservatoryCount;
import com.weather.weatherdataconsumer.entity.WeatherData;
import com.weather.weatherdataconsumer.enums.ObservatoryEnum;
import com.weather.weatherdataconsumer.enums.Temperature;
import com.weather.weatherdataconsumer.repository.WeatherDataRepository;

@Service
public class WeatherDataService {

	private static final Logger log = LoggerFactory.getLogger(WeatherDataService.class);

	private final WeatherDataRepository weatherDataRepository;

	public WeatherDataService(WeatherDataRepository weatherDataRepository) {
		this.weatherDataRepository = weatherDataRepository;
	}

	public Double getMaxTemperature(LocalDateTime startDate, LocalDateTime endDate) {

		if (startDate != null && endDate != null) {
			return weatherDataRepository.maxTemperatureWithTimestamp(startDate, endDate);
		} else {
			return weatherDataRepository.maxTemperature();
		}
	}

	public Double meanTemperature(LocalDateTime startDate, LocalDateTime endDate) {

		if (startDate != null && endDate != null) {
			return weatherDataRepository.meanTemperatureWithTimestamp(startDate, endDate);
		} else {
			return weatherDataRepository.meanTemperature();
		}

	}

	public Double getMinTemperature(LocalDateTime startDate, LocalDateTime endDate) {

		if (startDate != null && endDate != null) {
			return weatherDataRepository.minTemperatureWithTimestamp(startDate, endDate);
		} else {
			return weatherDataRepository.minTemperature();
		}

	}
	
	public List<ObservatoryCount> getObservations(LocalDateTime startDate, LocalDateTime endDate) {

		List<ObservatoryCount> observations = null;
		if (startDate != null && endDate != null) {
			observations = weatherDataRepository.findObservationsCountWithTimestamp(startDate, endDate);
		} else {
			observations = weatherDataRepository.findObservationsCount();
		}

		return observations;
	}

	public Double getMeanTemperature(LocalDateTime startDate, LocalDateTime endDate) {

		if (startDate != null && endDate != null) {
			return weatherDataRepository.meanTemperatureWithTimestamp(startDate, endDate);
		} else {
			return weatherDataRepository.meanTemperature();
		}

	}

	/*
	 * Need to implemented
	 * 
	 */
	public Double distance(LocalDateTime startDate, LocalDateTime endDate) {
		
		
		return 10.0;
	}

	public void covertMessage(String[] weatherDatafields) {

		WeatherData weatherData = new WeatherData();
		weatherData.setTimestamp(LocalDateTime.parse(weatherDatafields[0]));

		String observatory = weatherDatafields[3];

		if (observatory != null) {
			ObservatoryEnum observatoryEnum = ObservatoryEnum.fromString(observatory);
			weatherData.setObservatory(observatoryEnum);

			// Setting X and Y Co-ordinates
			String location = weatherDatafields[1];
			String[] locationCoOrdinates = location.split(",");
			weatherData.setLocationX(locationCoOrdinates[0]);
			weatherData.setLocationY(locationCoOrdinates[1]);

			// Getting Temperature and converting into Standard Kelvin metric
			Temperature temperature = observatoryEnum.getTemperature();
			weatherData.setTemperature(temperature.toKelvin(Integer.parseInt(weatherDatafields[2])));
		}
		weatherDataRepository.save(weatherData);
	}
}
