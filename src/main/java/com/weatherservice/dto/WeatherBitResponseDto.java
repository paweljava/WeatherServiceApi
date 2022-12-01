package com.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weatherservice.model.Weather;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*public record RequestWeatherDto(List<Weather> data) {
}*/

@Getter
@Setter
public class WeatherBitResponseDto {

    @JsonProperty("city_name")
    private String city_name;
    @JsonProperty("data")
    private List<Weather> data;

    public WeatherBitResponseDto() {
    }

    public WeatherBitResponseDto(String city_name, List<Weather> data) {
        this.city_name = city_name;
        this.data = data;
    }

    public String getCity_name() {
        return city_name;
    }

    public List<Weather> getData() {
        return data;
    }
}