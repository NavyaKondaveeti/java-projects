package com.sample.javaspringbootrestapi.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class WeatherData {
	 private Coordinates coord;
	 private ArrayList < Object > weather = new ArrayList < Object > ();
	 private String base;
	 private Main main;
	 private float visibility;
	 private Wind wind;
	 private Clouds clouds;
	 private float dt;
	 private Sys sys;
	 private float timezone;
	 private float id;
	 private String name;
	 private float cod;
}