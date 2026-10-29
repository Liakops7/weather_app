package com.liakops.weather_app.model;

public record WeatherResponse(
      double latitude,
      double longitude,
      String timezone,

      HourlyData hourly

) {}
