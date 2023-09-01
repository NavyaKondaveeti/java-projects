package com.sample.javaspringbootrestapi.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.javaspringbootrestapi.dto.WeatherData;
import com.sample.javaspringbootrestapi.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/api")
public class WeatherApiController {
	
	@Autowired
	public WeatherService weatherService;
	
	@RequestMapping(value = "/weather/info", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getWeatherInfo() {
		String weatherInfo = "It's COOL!!!";
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> entity = new ResponseEntity<>(weatherInfo, headers, HttpStatus.CREATED);
		return entity;
		
	}
	
	//Sample url: http://localhost:8088/api/weather/US/Cincinnati
	@RequestMapping(value = "/weather/{country}/{city}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<WeatherData> getCurrentWeather(@PathVariable String country,
			@PathVariable String city) {
		WeatherData currentWeather = weatherService.getCurrentWeather(city, country);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<WeatherData> entity = new ResponseEntity<>(currentWeather, headers, HttpStatus.CREATED);
		return entity;
		
	}
	
	@RequestMapping(value = "password/encoder", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> encodePassword() {
		String password = "user";
		String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> entity = new ResponseEntity<>(encodedPassword, headers, HttpStatus.CREATED);
		return entity;
		
	}
	
	@RequestMapping(value = "password/decoder", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> decodePassword() {
		String password = "cGFzc3dvcmQ=";
		byte[] decodedPasswordBytes = Base64.getDecoder().decode(password);
		String decodedString = new String(decodedPasswordBytes);
		System.out.println(decodedString);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> entity = new ResponseEntity<>(decodedString, headers, HttpStatus.CREATED);
		return entity;
		
	}

}
