package com.weatherservice.webclient;

import com.weatherservice.dto.WeatherBitResponseDto;
import com.weatherservice.utility.Checks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.weatherservice.common.ExceptionMessages.NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE;

@Component
public class WeatherClient {

    @Value("${api_host}")
    private String apiHost;
    public static String API_KEY = "f1cf185d994f48bc8ece50716d0ade8d";
    //private static String WEATHER_URL = "https://api.weatherbit.io/v2.0/forecast/daily";
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherBitResponseDto getForecastByCoordinates(double lat, double lon) {
        final var response = callGetMethod("/v2.0/forecast/daily?lat={lat}&lon={lon}&key={API_KEY}&lang=pl&",
                WeatherBitResponseDto.class,
                lat, lon, API_KEY);
        Checks.checkThat(!response.getData().isEmpty(), NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE);
        return response;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(this.apiHost + url, responseType, objects);
    }
}