package com.weatherservice.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseWeatherDto {

    private final String cityName;
    private final List<WeatherDto> weatherDtoList;

    public ResponseWeatherDto(String cityName, List<WeatherDto> weatherDtoList) {
        this.cityName = cityName;
        this.weatherDtoList = weatherDtoList;
    }

    @Override
    public String toString() {
        return "ResponseWeatherDto{" +
                "city nameeeeeeeee = " + cityName + "\n" +
                ", responseWeatherDto = " + weatherDtoList + weatherDtoList +
                "}";
    }
}
