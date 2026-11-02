package com.liakops.weather_app.model;

import java.util.List;

// Open-Meteo geocoding API response
public record GeocodingResponse (
        List<GeoResult> results
){}
