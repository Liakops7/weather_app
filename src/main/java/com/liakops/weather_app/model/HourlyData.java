package com.liakops.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
public record HourlyData(
    List<LocalDateTime> time,
    List<Double> rain,

    @JsonProperty("temperature_2m") List<Double> temperature2M,
    @JsonProperty("wind_speed_10m") List<Double> windSpeed10M,
    @JsonProperty("wind_direction_10m") List<Integer> windDirection10M,
    @JsonProperty("relative_humidity_2m") List<Integer> relativeHumidity2M,
    @JsonProperty("wind_gusts_10m") List<Double> windGusts10M

) {}
