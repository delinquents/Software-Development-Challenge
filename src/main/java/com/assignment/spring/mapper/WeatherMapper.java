package com.assignment.spring.mapper;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.dto.request.WeatherRequestDto;
import com.assignment.spring.dto.response.WeatherResponseDto;
import com.assignment.spring.models.WeatherEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    public  WeatherEntity mapToEntity(WeatherResponse response) {
        return WeatherEntity.builder()
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp()).build();
    }
    public  WeatherEntity mapToEntityFromRequestDto(WeatherRequestDto requestWeather) {
        return WeatherEntity.builder()
                .city(requestWeather.getCity())
                .country(requestWeather.getCountry())
                .temperature(requestWeather.getTemperature()).build();
    }
    public WeatherResponseDto mapToResponseFromEntity(WeatherEntity entity) {
        return WeatherResponseDto.builder()
                .city(entity.getCity())
                .country(entity.getCountry())
                .temperature(entity.getTemperature()).build();
    }
}
