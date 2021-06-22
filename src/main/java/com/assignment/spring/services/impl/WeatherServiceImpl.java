package com.assignment.spring.services.impl;

import com.assignment.spring.dto.response.WeatherResponseDto;
import com.assignment.spring.dto.response.WindResponseDto;
import com.assignment.spring.models.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.services.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository;

    @Override
    @Transactional
    public WeatherEntity saveWeather(WeatherEntity entity) {
        return weatherRepository.save(entity);
    }

    @Override
    public WeatherEntity findWeatherByWeatherId(Integer weatherId) {
        Optional<WeatherEntity> returnValue =  weatherRepository.findById(weatherId);
        return returnValue.orElse(null);
    }
    @Override
    public List<WeatherResponseDto> findAll() {
       List<Object[]> weatherResponseList = weatherRepository.weatherResponseList();
        List<WeatherResponseDto> responseDtos = new ArrayList<>();
       weatherResponseList.forEach(x -> {
           responseDtos.add( WeatherResponseDto.builder()
                   .id((Integer) x[0])
                   .city((String) x[1])
                   .country((String) x[2])
                   .description((String) x[3])
                   .temperature((Double) x[4])
                   .visibility((Long) x[5])
                   .wind(WindResponseDto.builder()
                           .speed((Double) x[6])
                           .deg((Integer) x[7]).build())
                   .build());
       });
        return responseDtos;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteByWeatherId(Integer weatherId) {
        weatherRepository.deleteById(weatherId);
    }

    @Override
    public List<WeatherResponseDto> getWarmestCitiesInCountry(String country) {
        return weatherRepository.getWarmestCitiesInCountry(country);
    }

    @Override
    public List<WeatherResponseDto> getColdestCitiesInCountry(String country) {
        return weatherRepository.getColdestCitiesInCountry(country);
    }
}
