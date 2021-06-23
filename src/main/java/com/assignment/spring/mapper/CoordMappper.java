package com.assignment.spring.mapper;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.models.CoordEntity;
import com.assignment.spring.models.WeatherEntity;
import org.springframework.stereotype.Component;

@Component
public class CoordMappper {
    public CoordEntity mapToEntity(WeatherResponse response, WeatherEntity weatherEntity) {
        return  CoordEntity.builder()
                .lon(response.getCoord().getLon())
                .lat(response.getCoord().getLat())
                .weather(weatherEntity)
                .build();
    }
}
