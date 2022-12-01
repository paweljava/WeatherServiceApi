package com.weatherservice.service;

import com.weatherservice.dto.WeatherBitResponseDto;
import com.weatherservice.model.City;
import com.weatherservice.model.Weather;
import com.weatherservice.repository.CityRepository;
import com.weatherservice.webclient.WeatherClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WeatherServiceTest {

    private final WeatherClient weatherClient = mock(WeatherClient.class);
    private final CityRepository cityRepository = mock(CityRepository.class);
    private final WeatherService weatherService = new WeatherService(weatherClient, cityRepository);

    private final String date = "2022-11-14";

    final City city1 = new City(3097421L, "Jastarnia", "82", "PL", 54.69606, 18.67873);
    final City city2 = new City(3374036L, "Bridgetown", "08", "BB", "Barbados", 13.10732, -59.62021);
    final List<City> cityList = List.of(city1, city2);

    private final Weather weather1 = new Weather("2022-11-01", 16, 20);
    private final Weather weather2 = new Weather("2022-11-02", 15, 13);
    private final Weather weather3 = new Weather("2022-11-03", 14, 20);
    private final Weather weather4 = new Weather("2022-11-04", 13, 3);
    private final Weather weather5 = new Weather("2022-11-05", 12, 20);
    private final Weather weather6 = new Weather("2022-11-06", 11, 3);
    private final Weather weather7 = new Weather("2022-11-07", 10, 20);
    private final Weather weather8 = new Weather("2022-11-08", 9, 3);
    private final Weather weather9 = new Weather("2022-11-09", 8, 20);
    private final Weather weather10 = new Weather("2022-11-10", 7, 3);
    private final Weather weather11 = new Weather("2022-11-11", 6, 20);
    private final Weather weather12 = new Weather("2022-11-12", 5, 3);
    private final Weather weather13 = new Weather("2022-11-13", 4, 20);
    private final Weather weather14 = new Weather("2022-11-14", 3, 3);
    private final Weather weather15 = new Weather("2022-11-15", 2, 20);
    private final Weather weather16 = new Weather("2022-11-16", 1, 6);

    private final List<Weather> weatherList1 = List.of(weather1, weather2, weather3, weather4,
            weather5, weather6, weather7, weather8, weather9, weather10, weather11,
            weather12, weather13, weather14, weather15, weather16);

    private final Weather weather21 = new Weather("2022-11-01", 1, 20);
    private final Weather weather22 = new Weather("2022-11-02", 2, 3);
    private final Weather weather23 = new Weather("2022-11-03", 3, 20);
    private final Weather weather24 = new Weather("2022-11-04", 4, 3);
    private final Weather weather25 = new Weather("2022-11-05", 5, 20);
    private final Weather weather26 = new Weather("2022-11-06", 6, 3);
    private final Weather weather27 = new Weather("2022-11-07", 7, 20);
    private final Weather weather28 = new Weather("2022-11-08", 8, 3);
    private final Weather weather29 = new Weather("2022-11-09", 9, 20);
    private final Weather weather30 = new Weather("2022-11-10", 10, 3);
    private final Weather weather31 = new Weather("2022-11-11", 11, 20);
    private final Weather weather32 = new Weather("2022-11-12", 12, 3);
    private final Weather weather33 = new Weather("2022-11-13", 13, 20);
    private final Weather weather34 = new Weather("2022-11-14", 14, 8);
    private final Weather weather35 = new Weather("2022-11-15", 15, 20);
    private final Weather weather36 = new Weather("2022-11-16", 16, 6);

    private final List<Weather> weatherList2 = List.of(weather21, weather22, weather23, weather24,
            weather25, weather26, weather27, weather28, weather29, weather30, weather31,
            weather32, weather33, weather34, weather35, weather36);

    private final WeatherBitResponseDto weatherBitResponseDto1 = new WeatherBitResponseDto(city1.getCityName(), weatherList1);
    private final WeatherBitResponseDto weatherBitResponseDto2 = new WeatherBitResponseDto(city2.getCityName(), weatherList2);
    private final List<WeatherBitResponseDto> weatherBitResponseDtoList = List.of(weatherBitResponseDto1, weatherBitResponseDto2);

    @Test
    void should_get_city_by_formula() {
        // given
        // when
        final var result = weatherService.getCityWeather(weatherBitResponseDtoList);

        // then
        assertEquals(weatherBitResponseDto1.getCity_name(), result.getCity_name());
        assertEquals(weatherBitResponseDto1.getData(), result.getData());
        assertNotEquals(weatherBitResponseDto2.getCity_name(), result.getCity_name());
        assertNotEquals(weatherBitResponseDto2.getData(), result.getData());
    }

  /*  @Test
    void should_get_city_by_requirement() {
        // given
        // when
        final var result = weatherService.getCitiesWeathers(weatherBitResponseDtoList, date);

        // then
        assertEquals(weather34.getForecastDate(), result.get(0).getData().get(0).getForecastDate());
        assertEquals(weather34.getAverageTemp(), result.get(0).getData().get(0).getAverageTemp());
        assertEquals(weather34.getWindSpeed(), result.get(0).getData().get(0).getWindSpeed());
        assertNotEquals(weather21.getForecastDate(), result.get(0).getData().get(0).getForecastDate());
        assertNotEquals(weather13.getAverageTemp(), result.get(0).getData().get(0).getAverageTemp());
        assertNotEquals(weather4.getWindSpeed(), result.get(0).getData().get(0).getWindSpeed());
    }*/

    @Test
    void should_get_weather() {
        // given
        given(cityRepository.getCityList()).willReturn(cityList);
        given(weatherClient.getForecastByCoordinates(city1.getLat(), city1.getLon())).willReturn(weatherBitResponseDto1);
        given(weatherClient.getForecastByCoordinates(city2.getLat(), city2.getLon())).willReturn(weatherBitResponseDto2);

        // when
        final var result = weatherService.getWeather(date);

        // then
        assertEquals(city2.getCityName(), result.getCity_name());
        assertEquals(weather34.getForecastDate(), result.getData().get(0).getForecastDate());
        assertEquals(weather34.getAverageTemp(), result.getData().get(0).getAverageTemp());
        assertEquals(weather34.getWindSpeed(), result.getData().get(0).getWindSpeed());
        assertNotEquals(weather1.getForecastDate(), result.getData().get(0).getForecastDate());
        assertNotEquals(weather10.getAverageTemp(), result.getData().get(0).getAverageTemp());
        assertNotEquals(weather4.getWindSpeed(), result.getData().get(0).getWindSpeed());
    }
}