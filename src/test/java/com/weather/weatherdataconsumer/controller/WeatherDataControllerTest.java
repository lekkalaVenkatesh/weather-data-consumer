package com.weather.weatherdataconsumer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherDataControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void minTemperature() throws Exception {
		mockMvc.perform(get("/weather/minTemperature")).andExpect(status().isOk());
	}

	@Test
	public void maxTemperature() throws Exception {
		mockMvc.perform(get("/weather/maxTemperature")).andExpect(status().isOk());
	}

	@Test
	public void meanTemperature() throws Exception {
		mockMvc.perform(get("/weather/meanTemperature")).andExpect(status().isOk());
	}

	@Test
	public void observations() throws Exception {
		mockMvc.perform(get("/weather/observations")).andExpect(status().isOk());
	}

	@Test
	public void distance() throws Exception {
		mockMvc.perform(get("/weather/distance")).andExpect(status().isOk());
	}

	@Test
	public void minTemperatureWithParameters() throws Exception {
		mockMvc.perform(get("/weather/minTemperature").param("startDate", LocalDateTime.now().toString())
				.param("endDate", LocalDateTime.now().toString())).andExpect(status().isOk());
	}

	@Test
	public void maxTemperatureWithParameters() throws Exception {
		mockMvc.perform(get("/weather/maxTemperature").param("startDate", LocalDateTime.now().toString())
				.param("endDate", LocalDateTime.now().toString())).andExpect(status().isOk());
	}

	@Test
	public void meanTemperatureWithParameters() throws Exception {
		mockMvc.perform(get("/weather/meanTemperature").param("startDate", LocalDateTime.now().toString())
				.param("endDate", LocalDateTime.now().toString())).andExpect(status().isOk());
	}

	@Test
	public void observationsWithParameters() throws Exception {
		mockMvc.perform(get("/weather/observations").param("startDate", LocalDateTime.now().toString()).param("endDate",
				LocalDateTime.now().toString())).andExpect(status().isOk());
	}

	@Test
	public void distanceWithParameters() throws Exception {
		mockMvc.perform(get("/weather/distance").param("startDate", LocalDateTime.now().toString()).param("endDate",
				LocalDateTime.now().toString())).andExpect(status().isOk());
	}

}
