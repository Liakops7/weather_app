package com.liakops.weather_app.service;

import com.liakops.weather_app.model.WeatherResponse;
import org.springframework.web.client.RestClient;

public class WeatherService {
    private final RestClient restClient = RestClient.create("https://api.open-meteo.com");

    public WeatherResponse getForecast(){
        WeatherResponse result = restClient
                        .get()
                        .uri("/v1/forecast?latitude=37.97&longitude=23.73&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,relative_humidity_2m,wind_gusts_10m&models=ecmwf_ifs&timezone=auto&wind_speed_unit=kn&forecast_days=10")
                        .retrieve()
                        .body(WeatherResponse.class);
    }
}
