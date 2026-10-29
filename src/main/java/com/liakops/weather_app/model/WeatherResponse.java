package com.liakops.weather_app.model;

import java.time.LocalDateTime;
import java.util.List;

public Hourly(String time, List<LocalDateTime> time){}

public record WeatherResponse(
      double latitude,
      double longitude,
      String timezone,

      Hourly hourly

) {}
