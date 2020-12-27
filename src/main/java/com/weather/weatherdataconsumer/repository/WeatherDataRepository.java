package com.weather.weatherdataconsumer.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.weather.weatherdataconsumer.dto.ObservatoryCount;
import com.weather.weatherdataconsumer.entity.WeatherData;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Long>{
	
	@Query(value = "SELECT min(temperature) FROM WeatherData")
	public Double minTemperature();
	
	@Query(value = "SELECT max(temperature) FROM WeatherData")
	public Double maxTemperature();
	
	@Query(value = "SELECT avg(temperature) FROM WeatherData")
	public Double meanTemperature();
	
	@Query("select new com.weather.weatherdataconsumer.dto.ObservatoryCount(w.observatory , count(w) as cnt) FROM WeatherData w group by w.observatory")
	public List<ObservatoryCount> findObservationsCount();
	
	
	@Query(value = "SELECT min(temperature) FROM WeatherData"
			  + " where timestamp BETWEEN :startDate AND :endDate ")
	public Double minTemperatureWithTimestamp(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	@Query(value = "SELECT max(temperature) FROM WeatherData"
			  + " where timestamp BETWEEN :startDate AND :endDate ")
	public Double maxTemperatureWithTimestamp(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	@Query(value = "SELECT avg(temperature) FROM WeatherData"
	        + " where timestamp BETWEEN :startDate AND :endDate ")
	public Double meanTemperatureWithTimestamp(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	@Query("select new com.weather.weatherdataconsumer.dto.ObservatoryCount(w.observatory , count(w) as cnt) FROM WeatherData w "
			+ " where timestamp BETWEEN :startDate AND :endDate "
			+ " group by w.observatory ")
	public List<ObservatoryCount> findObservationsCountWithTimestamp(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	
}
