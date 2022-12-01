package com.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class Weather {

    @JsonProperty("valid_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String forecastDate;
    @JsonProperty("temp")
    private float averageTemp;
    @JsonProperty("wind_spd")
    private float windSpeed;

    public Weather() {
    }

    public Weather(String valid_date, float temp, float wind_spd) {
        this.forecastDate = valid_date;
        this.averageTemp = temp;
        this.windSpeed = wind_spd;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "forecast date = " + forecastDate +
                ", average temperature = " + averageTemp +
                ", wind speed = " + windSpeed +
                '}';
    }
}