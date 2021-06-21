package com.assignment.spring.repository;

import com.assignment.spring.models.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {

}
