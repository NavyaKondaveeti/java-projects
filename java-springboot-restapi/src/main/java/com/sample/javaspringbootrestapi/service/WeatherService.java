package com.sample.javaspringbootrestapi.service;

import com.sample.javaspringbootrestapi.dto.WeatherData;

public interface WeatherService {
	public WeatherData getCurrentWeather(String city, String country);

}
