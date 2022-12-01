package com.weatherservice.dto;

import lombok.Getter;

@Getter
public class WeatherDto {

    private final String data;
    private final float averageTemp;
    private final float windSpeed;

    public WeatherDto(String data, float averageTemp, float windSpeed) {
        this.data = data;
        this.averageTemp = averageTemp;
        this.windSpeed = windSpeed;
    }
}
