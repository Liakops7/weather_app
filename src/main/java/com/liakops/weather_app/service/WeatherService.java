package com.liakops.weather_app.service;

import com.liakops.weather_app.model.GeoResult;
import com.liakops.weather_app.model.GeocodingResponse;
import com.liakops.weather_app.model.WeatherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherService {
    private final RestClient forecastClient = RestClient.create("https://api.open-meteo.com");
    private final RestClient geocodingClient = RestClient.create("https://geocoding-api.open-meteo.com");

    public WeatherResponse getForecast(String city) {

        GeoResult location = findCity(city);

        WeatherResponse result = forecastClient
                .get()
                .uri("/v1/forecast?latitude={lat}&longitude={lon}&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,relative_humidity_2m,wind_gusts_10m&models=ecmwf_ifs&timezone=auto&wind_speed_unit=kn&forecast_days=10",
                        location.latitude(), location.longitude())
                .retrieve()
                .body(WeatherResponse.class);
        return result;
    }

    public GeoResult findCity (String city){
        GeocodingResponse response = geocodingClient
                .get()
                .uri("/v1/search?name={name}&count=1&language=el", city)
                .retrieve()
                .body(GeocodingResponse.class);
        if (response == null || response.results() == null || response.results().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found: " + city);
        }
        return response.results().get(0);
    }
}
