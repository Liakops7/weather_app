package com.liakops.weather_app.model;

import java.util.List;

public record GeocodingResponse (
        List<GeoResult> results
){}
