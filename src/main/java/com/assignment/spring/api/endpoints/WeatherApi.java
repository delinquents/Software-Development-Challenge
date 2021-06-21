package com.assignment.spring.api.endpoints;

import com.assignment.spring.dto.request.WeatherRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface WeatherApi {

    @GetMapping("/weather")
    ResponseEntity<Object> weather(HttpServletRequest request);

    @GetMapping("/getWeathers")
    ResponseEntity<Object> getWeathers();

    @PutMapping("/updateWeather")
    ResponseEntity<Object> updateWeather(WeatherRequestDto requestDto);

    @DeleteMapping("/deleteWeather")
    ResponseEntity<Object> deleteWeather(Integer cityId);
}
