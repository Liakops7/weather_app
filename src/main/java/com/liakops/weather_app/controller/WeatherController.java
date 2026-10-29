package com.liakops.weather_app.controller;

import com.liakops.weather_app.model.WeatherResponse;
import com.liakops.weather_app.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(){


        return weatherService.weatherService;
    }

}
