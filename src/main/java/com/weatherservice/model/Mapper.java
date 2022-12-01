package com.weatherservice.model;

import com.weatherservice.dto.WeatherBitResponseDto;
import com.weatherservice.dto.ResponseWeatherDto;
import com.weatherservice.dto.WeatherDto;

import java.util.stream.Collectors;

public class Mapper {

    public static ResponseWeatherDto mapRequestDtoToResponseDto(WeatherBitResponseDto weatherBitResponseDto) {
        return new ResponseWeatherDto(
                weatherBitResponseDto.getCity_name(),
                weatherBitResponseDto.getData().stream().map(data -> new WeatherDto(
                                data.getForecastDate(),
                                data.getAverageTemp(),
                                data.getWindSpeed()))
                        .collect(Collectors.toList()));
    }

    /*public static List<ResponseWeatherDto> mapRequestDtoToResponseDto(List<RequestWeatherDto> requestWeatherDto) {
        return requestWeatherDto.stream().map(
                        data -> new ResponseWeatherDto(
                                data.getCity_name(),
                                data.getData().stream().map(
                                                weather -> new WeatherDto(
                                                        weather.getForecastDate(),
                                                        weather.getAverageTemp(),
                                                        weather.getWindSpeed()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }*/

    /*public static ResponseWeatherDto mapRequestDtoToResponseDto2(RequestWeatherDto requestWeatherDto) {
        return new ResponseWeatherDto(
                requestWeatherDto.getCity_name(),
                requestWeatherDto.getData().stream().map(
                                weather -> new WeatherDto(
                                        weather.getForecastDate(),
                                        weather.getAverageTemp(),
                                        weather.getWindSpeed()))
                        .collect(Collectors.toList()));

    }*/
}

/*    public static List<Weather> mapRequestWeatherDtoToWeather(RequestWeatherDto requestWeatherDto) {
        return requestWeatherDto.getData().stream().map(
                        data -> new Weather(
                                data.getForecastDate(),
                                data.getAverageTemp(),
                                data.getWindSpeed()))
                .collect(Collectors.toList());
    }

    public static List<Weather> mapRequestWeatherDtoToWeather2(List<RequestWeatherDto> requestWeatherDto) {
        return requestWeatherDto.stream().map(
                        data -> new Weather(
                                data.getCity_name(),
                                data.getData()))
                .collect(Collectors.toList());
    }

    public static List<RequestWeatherDto> mapRequestWeatherDtoForecastToRequestWeatherDto(RequestWeatherDtoForecast requestWeatherDtoForecast) {
        var response = requestWeatherDtoForecast.getRequestWeatherDtoList().stream().map(
                        weatherList -> new RequestWeatherDto(
                                weatherList.getCity_name(),
                                weatherList.getData()))
                .collect(Collectors.toList());
        return response;
    }

    public static List<WeatherDto> mapWeatherToResponseWeatherDto(List<Weather> weather) {
        return weather.stream().map(
                data -> new WeatherDto(
                        data.getForecastDate(),
                        data.getAverageTemp(),
                        data.getWindSpeed())).collect(Collectors.toList());
    }

    public static List<ResponseWeatherDto> mapWeatherToResponseWeatherDto2(List<RequestWeatherDto> requestWeatherDto) {
        return requestWeatherDto.stream().map(
                        data -> new ResponseWeatherDto(
                                data.getCity_name(),
                                data.getData().stream().map(
                                                weather -> new WeatherDto(
                                                        weather.getForecastDate(),
                                                        weather.getAverageTemp(),
                                                        weather.getWindSpeed()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }*/