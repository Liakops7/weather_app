package com.liakops.weather_app.model;

public record GeoResult (
        String name,
        double latitude,
        double longitude,
        String country
) {}
