package com.sample.javaspringbootrestapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.sample.javaspringbootrestapi.dto.WeatherData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Value("${api.openweathermap.key}")
    private String apiKey;
	
	@Value("${weather.api.url}")
    private String weatherUrl;
	
	@Value("${weather.api.path}")
    private String weatherApiPath;

	
	@Autowired
	@Qualifier("weatherApi")
    private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    

	@Override
	public WeatherData getCurrentWeather(String city, String country) {

		String url = weatherUrl + weatherApiPath;
		String location = city + ',' + country;
		url = url.replace("xxxlocationxxx", location);
		url = url.replace("xxxAPPIDxxx", apiKey);
		log.warn("****Weather API URL: " + url);
		ResponseEntity<String> weatherResponse = restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(), String.class);
		Gson gson = new Gson();
		WeatherData weatherData = new WeatherData();
		weatherData = gson.fromJson(weatherResponse.getBody(), WeatherData.class);
		return weatherData;
	}
	
	private HttpEntity<String> getRequestEntity(){
		String json = "{\"json\":\"object\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		return requestEntity;
		
	}

}
