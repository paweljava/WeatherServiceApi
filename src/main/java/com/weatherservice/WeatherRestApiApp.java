package com.weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WeatherRestApiApp {

    @Autowired
    public static void main(String[] args) {

        SpringApplication.run(WeatherRestApiApp.class, args);
    }
}
