package com.assignment.spring.mapper;


import com.assignment.spring.api.templates.Weather;
import com.assignment.spring.ui.dto.response.WeatherInfoResponseDto;
import com.assignment.spring.models.entities.WeatherInfoEntity;
import org.springframework.stereotype.Component;



@Component
public class WeatherInfoMapper {

    public WeatherInfoEntity mapToEntity(Weather response) {
        return WeatherInfoEntity.builder()
                .id(response.getId())
                .main(response.getMain())
                .description(response.getDescription())
                .icon(response.getIcon())
                .build();
    }

    public WeatherInfoResponseDto mapToResponse(WeatherInfoEntity entity) {
        return WeatherInfoResponseDto.builder()
                .id(entity.getId())
                .main(entity.getMain())
                .description(entity.getDescription())
                .icon(entity.getIcon())
                .build();
    }
}
