package com.liakops.weather_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class WeatherController {

    private final RestClient restClient = RestClient.create("https://api.open-meteo.com");

    @GetMapping("/getWeather")
    public String getWeather(){
        String result = restClient
                .get()
                .uri("/v1/forecast?latitude=37.97&longitude=23.73&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,relative_humidity_2m,wind_gusts_10m&models=ecmwf_ifs&timezone=auto&wind_speed_unit=kn&forecast_days=10")
                .retrieve()
                .body(String.class);

        return result;
    }

}
