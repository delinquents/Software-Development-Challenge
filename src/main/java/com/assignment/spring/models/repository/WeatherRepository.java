package com.assignment.spring.models.repository;


import com.assignment.spring.ui.dto.response.WeatherResponseDto;
import com.assignment.spring.models.entities.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {


    @Query("SELECT we.id, we.city ,we.country, we.temperature, we.visibility, w.speed, w.deg " +
            " FROM WeatherEntity as we, WindEntity as  w where  we.id = w.weatherId")
    List<Object[]> weatherResponseList();

    @Query("SELECT new com.assignment.spring.ui.dto.response.WeatherResponseDto(w.city, w.temperature) from WeatherEntity w " +
            " where w.country = :country and ROWNUM <= 3 order by w.temperature DESC")
    List<WeatherResponseDto> getWarmestCitiesInCountry(@Param("country") String country);

    @Query("SELECT new com.assignment.spring.ui.dto.response.WeatherResponseDto(w.city, w.temperature) from WeatherEntity w " +
                  " where w.country = :country and ROWNUM <= 3 order by w.temperature ASC")
    List<WeatherResponseDto> getColdestCitiesInCountry(@Param("country") String country);

}
