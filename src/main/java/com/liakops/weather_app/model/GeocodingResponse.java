package com.liakops.weather_app.model;

public record GeocodingResponse (
        List<WeatherResponse> result
){}
