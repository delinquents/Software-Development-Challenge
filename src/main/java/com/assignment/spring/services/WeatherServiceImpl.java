package com.assignment.spring.services;

import com.assignment.spring.models.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements  WeatherService{

    private WeatherRepository weatherRepository;

    @Override
    @Transactional
    public WeatherEntity saveWeather(WeatherEntity entity) {
        return weatherRepository.save(entity);
    }

    @Override
    public WeatherEntity findWeatherByCityId(Integer cityId) {
        Optional<WeatherEntity> returnValue =  weatherRepository.findById(cityId);
        return returnValue.orElse(null);
    }
    @Override
    public List<WeatherEntity> findAll() {
        return weatherRepository.findAll(Sort.by("city").ascending());
    }

    @Override
    @Transactional
    @Modifying
    public void deleteByCityId(Integer cityId) {
        weatherRepository.deleteById(cityId);
    }

}
