package com.assignment.spring.mapper;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.dto.request.WeatherRequestDto;
import com.assignment.spring.dto.response.WeatherResponseDto;
import com.assignment.spring.dto.response.WindResponseDto;
import com.assignment.spring.models.WeatherEntity;
import com.assignment.spring.models.WindEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    public  WeatherEntity mapToEntity(WeatherResponse response) {
        return WeatherEntity.builder()
                .id(response.getId())
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp())
                .visibility(Long.valueOf(response.getVisibility()))
                .description(response.getWeather().get(0).getDescription()).build();
    }
    public  WeatherEntity mapToEntityFromRequestDto(WeatherRequestDto requestWeather) {
        return WeatherEntity.builder()
                .id(requestWeather.getId())
                .city(requestWeather.getCity())
                .country(requestWeather.getCountry())
                .description(requestWeather.getDescription())
                .visibility(requestWeather.getVisibility())
                .temperature(requestWeather.getTemperature()).build();
    }
    public WeatherResponseDto mapToResponseFromEntity(WeatherEntity weatherEntity, WindEntity windEntity) {
        return WeatherResponseDto.builder()
                 .id(weatherEntity.getId())
                .city(weatherEntity.getCity())
                .country(weatherEntity.getCountry())
                .description(weatherEntity.getDescription())
                .temperature(weatherEntity.getTemperature())
                .visibility(weatherEntity.getVisibility())
                .wind(WindResponseDto.builder()
                        .speed(windEntity.getSpeed())
                        .deg(windEntity.getDeg())
                        .build())
                .build();
    }
}
