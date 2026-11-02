package com.liakops.weather_app.model;

// Open-Meteo forecast API response
public record WeatherResponse(
      double latitude,
      double longitude,
      String timezone,

      HourlyData hourly

) {}
