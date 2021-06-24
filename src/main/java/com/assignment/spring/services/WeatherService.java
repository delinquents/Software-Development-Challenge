package com.assignment.spring.services;

import com.assignment.spring.ui.dto.response.WeatherResponseDto;
import com.assignment.spring.models.entities.WeatherEntity;

import java.util.List;

public interface WeatherService {
    WeatherEntity saveWeather(WeatherEntity entity);
    WeatherEntity findWeatherByWeatherId(Integer cityId);
    List<WeatherResponseDto> findAll();
    void deleteByWeatherId(Integer weatherId);
    List<WeatherResponseDto> getWarmestCitiesInCountry(String country);
    List<WeatherResponseDto> getColdestCitiesInCountry(String country);
}
