package com.assignment.spring.services;

import com.assignment.spring.models.WeatherEntity;

import java.util.List;

public interface WeatherService {
    WeatherEntity saveWeather(WeatherEntity entity);
    WeatherEntity findWeatherByCityId(Integer cityId);
    List<WeatherEntity> findAll();
    void deleteByCityId(Integer cityId);
}
