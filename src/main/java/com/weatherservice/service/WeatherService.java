package com.weatherservice.service;

import com.weatherservice.dto.WeatherBitResponseDto;
import com.weatherservice.model.Weather;
import com.weatherservice.repository.CityRepository;
import com.weatherservice.webclient.WeatherClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.weatherservice.common.ExceptionMessages.CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE;
import static com.weatherservice.service.WeatherValidator.validateWeatherConditions;
import static com.weatherservice.utility.Checks.checkThat;

@Service
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;

    public WeatherService(WeatherClient weatherClient, CityRepository cityRepository) {
        this.weatherClient = weatherClient;
        this.cityRepository = cityRepository;
    }

    @Cacheable(cacheNames = "requestWeather")
    public WeatherBitResponseDto getWeather(String date) {
        final var response = cityRepository.getCityList().stream().map(
                        city -> weatherClient.getForecastByCoordinates(city.getLat(), city.getLon()))
                .collect(Collectors.toList());
        return getCityWeather(getCitiesWeathers(response, date));
    }

    public List<WeatherBitResponseDto> getCitiesWeathers(List<WeatherBitResponseDto> cityWeathers, String data) {
        final List<WeatherBitResponseDto> dataList = new ArrayList<>(Collections.emptyList());
        for (final var cityData : cityWeathers) {
            final var cityName = cityData.getCity_name();
            for (final var weatherData : cityData.getData()) {
                if ((weatherData.getForecastDate().equals(data)) &&
                        (validateWeatherConditions(weatherData.getAverageTemp(), weatherData.getWindSpeed()))) {
                    final var newWeatherData = new Weather(
                            weatherData.getForecastDate(),
                            weatherData.getAverageTemp(),
                            weatherData.getWindSpeed());
                    List<Weather> weatherList = new ArrayList<>();
                    weatherList.add(newWeatherData);

                    var newCityData = new WeatherBitResponseDto(cityName, weatherList);
                    dataList.add(newCityData);
                }

            }
        }
        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        return dataList;
    }

    public WeatherBitResponseDto getCityWeather(List<WeatherBitResponseDto> cityWeathers) {
        WeatherBitResponseDto response = null;
        float formulaValue = 0;
        for (final var cityData : cityWeathers) {
            for (final var weather : cityData.getData()) {
                if (applyFormula(weather.getAverageTemp(), weather.getWindSpeed()) > formulaValue) {
                    formulaValue = (applyFormula(weather.getAverageTemp(), weather.getWindSpeed()));
                    response = new WeatherBitResponseDto(cityData.getCity_name(), cityData.getData());
                }
            }
        }
        return response;
    }

    public float applyFormula(float averageTemp, float windSpeed) {
        return averageTemp + windSpeed * 3;
    }
}