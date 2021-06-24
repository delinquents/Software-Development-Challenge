package com.assignment.spring.mapper;

import com.assignment.spring.api.templates.WeatherResponse;

import com.assignment.spring.models.entities.WindEntity;
import org.springframework.stereotype.Component;

@Component
public class WindMapper {

    public  WindEntity mapToEntity(WeatherResponse response) {

        return  WindEntity.builder()
                    .deg(response.getWind().getDeg())
                    .speed(response.getWind().getSpeed())
                    .weatherId(response.getId())
                    .build();
    }

}
