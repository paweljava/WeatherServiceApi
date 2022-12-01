package com.weatherservice.service;

public final class WeatherValidator {

    public static final float MIN_TEMP = 1;
    public static final float MAX_TEMP = 35;
    public static final float MIN_WIND = 1;
    public static final float MAX_WIND = 18;

    public static boolean validateWeatherConditions(float averageTemp, float windSpeed) {
        return averageTemp > MIN_TEMP  &&
                averageTemp < MAX_TEMP &&
                windSpeed > MIN_WIND &&
                windSpeed < MAX_WIND;
    }
}
