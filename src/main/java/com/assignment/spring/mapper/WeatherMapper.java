package com.assignment.spring.mapper;

import com.assignment.spring.api.templates.WeatherResponse;
import com.assignment.spring.ui.dto.request.WeatherRequestDto;
import com.assignment.spring.ui.dto.response.CoordResponseDto;
import com.assignment.spring.ui.dto.response.WeatherResponseDto;
import com.assignment.spring.ui.dto.response.WindResponseDto;
import com.assignment.spring.models.entities.CoordEntity;
import com.assignment.spring.models.entities.WeatherEntity;
import com.assignment.spring.models.entities.WeatherInfoEntity;
import com.assignment.spring.models.entities.WindEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class WeatherMapper {

    private WeatherInfoMapper weatherInfoMapper;
    private GenericMapper genericMapper;

    public  WeatherEntity mapToEntity(WeatherResponse response) {
        List<WeatherInfoEntity> wInfos = new ArrayList<>();
        response.getWeather().forEach(weather -> {
            wInfos.add(WeatherInfoEntity.builder()
                    .id(weather.getId())
                    .description(weather.getDescription())
                    .icon(weather.getIcon())
                    .main(weather.getMain())
                    .weather(WeatherEntity.builder().id(response.getId()).build())
                    .build());
        });
        return WeatherEntity.builder()
                .id(response.getId())
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp())
                .visibility(Long.valueOf(response.getVisibility()))
                .weatherInfos(wInfos)
                .cod(response.getCod())
                .build();
    }
    public  WeatherEntity mapToEntityFromRequestDto(WeatherRequestDto requestWeather) {
        return WeatherEntity.builder()
                .id(requestWeather.getId())
                .city(requestWeather.getCity())
                .country(requestWeather.getCountry())
                .visibility(requestWeather.getVisibility())
                .temperature(requestWeather.getTemperature())
                .cod(requestWeather.getCod())
                .build();
    }
    public WeatherResponseDto mapToResponseFromEntity(WeatherEntity weatherEntity, WindEntity windEntity, CoordEntity coordEntity) {
        return WeatherResponseDto.builder()
                .id(weatherEntity.getId())
                .city(weatherEntity.getCity())
                .country(weatherEntity.getCountry())
                .temperature(weatherEntity.getTemperature())
                .visibility(weatherEntity.getVisibility())
                .cod(weatherEntity.getCod())
                .weatherInfos(genericMapper.toList(weatherEntity.getWeatherInfos(), x-> weatherInfoMapper.mapToResponse(x)))
                .wind(WindResponseDto.builder()
                        .speed(windEntity.getSpeed())
                        .deg(windEntity.getDeg())
                        .build())
                .coord(CoordResponseDto.builder()
                        .lon(coordEntity.getLon())
                        .lat(coordEntity.getLat())
                        .build()
                )
                .build();
    }


}
