package com.liakops.weather_app.service;

import com.liakops.weather_app.model.GeoResult;
import com.liakops.weather_app.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {
    private final RestClient forecastClient = RestClient.create("https://api.open-meteo.com");
    private final RestClient geocodingClient = RestClient.create("https://geocoding-api.open-meteo.com");

    public WeatherResponse getForecast() {
        WeatherResponse result = forecastClient
                .get()
                .uri("/v1/forecast?latitude=37.97&longitude=23.73&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,relative_humidity_2m,wind_gusts_10m&models=ecmwf_ifs&timezone=auto&wind_speed_unit=kn&forecast_days=10")
                .retrieve()
                .body(WeatherResponse.class);
        return result;
    }

    public GeoResult findCity (String city){
        GeoResult response = geocodingClient
                .get()
                .uri("/v1/search?name={name}&count=1&language=el", city)
                .retrieve()
                .body(GeoResult.class)
        return
    }
}
