package com.liakops.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record HourlyData(
    List<Date> time,
    List<Double> temperature_2m,
    List<Double> rain,
    List<Double> wind_speed_10m,
    List<Integer> wind_direction_10m,
    List<Integer> relative_humidity_2m,
    List<Double> wind_gusts_10m
    //@JsonProperty("temperature_2m") Units temperature2M
) {}
