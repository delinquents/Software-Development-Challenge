package com.assignment.spring.controllers;

import com.assignment.spring.api.endpoints.WeatherApi;
import com.assignment.spring.constants.Constants;
import com.assignment.spring.dto.request.WeatherRequestDto;
import com.assignment.spring.dto.response.WeatherResponseDto;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.mapper.WindMapper;
import com.assignment.spring.models.WeatherEntity;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.models.WindEntity;
import com.assignment.spring.services.WeatherService;
import com.assignment.spring.services.WindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  @Author: Veljko Siracki
 *  information about payload for endpoints is in package-info.java
 **/

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController implements WeatherApi {


    private RestTemplate restTemplate;
    private WeatherMapper weatherMapper;
    private WindMapper windMapper;
    private WeatherService weatherService;
    private WindService windService;

    public ResponseEntity<Object> weather(HttpServletRequest request, @RequestParam("city") String city ) {
        try {
            if (city.equals("")) {
                return new ResponseEntity<> ("City is required.", HttpStatus.BAD_REQUEST);
            } else {
                String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
                ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
                WeatherEntity weatherEntity = weatherService.saveWeather(weatherMapper.mapToEntity(Objects.requireNonNull(response.getBody())));
                WindEntity windEntity = windService.saveWeather(windMapper.mapToEntity(Objects.requireNonNull(response.getBody())));
                return new ResponseEntity<> (weatherMapper.mapToResponseFromEntity(weatherEntity, windEntity), HttpStatus.OK);}
        } catch (Exception e) {
                log.error(e.getCause().getCause().getMessage());
                return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getWeathers() {
        List<WeatherResponseDto> response = weatherService.findAll();
        if (response.size() > 0) {
            return new ResponseEntity<> (weatherService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<> ("No content", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> updateWeather(@RequestBody WeatherRequestDto requestDto) {
        try {
             WeatherEntity response = weatherService.findWeatherByWeatherId(requestDto.getId());
             if (response == null) {
                return new ResponseEntity<> ("There is no weather by given city id", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<> (weatherService.saveWeather(weatherMapper.mapToEntityFromRequestDto(requestDto)), HttpStatus.OK);
             }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteWeather(@RequestParam("weatherId") Integer weatherId) {
        try {
            if (weatherService.findWeatherByWeatherId(weatherId) == null) {
                return new ResponseEntity<> (HttpStatus.NO_CONTENT);
            } else {
                weatherService.deleteByWeatherId(weatherId);
                windService.deleteWindByWeatherId(weatherId);
                return new ResponseEntity<> ("Weather by given city deleted successfully.", HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTop3WarmestCitiesInCountry(@RequestParam("country") String country) {
        return new ResponseEntity<> (weatherService.getWarmestCitiesInCountry(country), HttpStatus.OK);
    }

    public ResponseEntity<Object> getTop3ColdestCitiesInCountry(@RequestParam("country") String country) {
        return new ResponseEntity<> (weatherService.getWarmestCitiesInCountry(country), HttpStatus.OK);
    }
}

