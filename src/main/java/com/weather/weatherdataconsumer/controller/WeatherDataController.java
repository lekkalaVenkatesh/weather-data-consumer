package com.weather.weatherdataconsumer.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherdataconsumer.dto.ObservatoryCount;
import com.weather.weatherdataconsumer.service.WeatherDataService;

@RestController
@RequestMapping("/weather")
public class WeatherDataController {

	private final WeatherDataService weatherDataService;

	WeatherDataController(WeatherDataService weatherDataService) {
		this.weatherDataService = weatherDataService;
	}

	/*
	 * The minimum temperature.
	 */
	@GetMapping("/minTemperature")
	public Double minTemperature(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return weatherDataService.getMinTemperature(startDate, endDate);
	}

	/*
	 * The maximum temperature.
	 */
	@GetMapping("/maxTemperature")
	public Double maxTemperature(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return weatherDataService.getMaxTemperature(startDate, endDate);
	}

	/*
	 * The mean temperature.
	 */
	@GetMapping("/meanTemperature")
	public Double meanTemperature(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDateTime endDate) {
		return weatherDataService.meanTemperature(startDate, endDate);
	}

	/*
	 * The number of observations from each observatory.
	 */
	@GetMapping("/observations")
	public List<ObservatoryCount> observations(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return weatherDataService.getObservations(startDate, endDate);
	}

	/*
	 * The total distance travelled.
	 */
	@GetMapping("/distance")
	public Double distance(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return weatherDataService.distance(startDate, endDate);
	}

}
