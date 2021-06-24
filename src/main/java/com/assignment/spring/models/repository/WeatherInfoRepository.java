package com.assignment.spring.models.repository;

import com.assignment.spring.models.entities.WeatherInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfoEntity, Integer> {
}
