package com.sample.javaspringbootrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	   @Bean
	   public RestTemplate restTemplateBean() {
	        return new RestTemplate();
	    }
	   
	   @Bean(name =  "weatherApi")
	   public RestTemplate weatherApiTemplate() {
		   RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	   }

}
