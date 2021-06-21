package com.assignment.spring.controllers;

import com.assignment.spring.api.endpoints.WeatherApi;
import com.assignment.spring.constants.Constants;
import com.assignment.spring.dto.request.WeatherRequestDto;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.models.WeatherEntity;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.services.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
    private WeatherMapper mapper;
    private WeatherService service;

    public ResponseEntity<Object> weather(HttpServletRequest request) {
        try {
            String city = request.getParameter("city");
            if (city.equals("")) {
                return new ResponseEntity<> ("City is required.", HttpStatus.BAD_REQUEST);
            } else {
                String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
                ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
                return new ResponseEntity<> (service.saveWeather(mapper.mapToEntity(Objects.requireNonNull(response.getBody()))), HttpStatus.OK);}
        } catch (Exception e) {
                log.error(e.getCause().getCause().getMessage());
                return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getWeathers() {
        return new ResponseEntity<> (service.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateWeather(@RequestBody WeatherRequestDto requestDto) {
        try {
             WeatherEntity response = service.findWeatherByCityId(requestDto.getId());
             if (response == null) {
                return new ResponseEntity<> ("There is no weather by given city id", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<> (service.saveWeather(mapper.mapToEntityFromRequestDto(requestDto)), HttpStatus.OK);
             }
        } catch (Exception e) {
            log.error(e.getCause().getCause().getMessage());
            return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteWeather(@RequestParam("cityId") Integer cityId) {
        try {
            if (service.findWeatherByCityId(cityId) == null) {
                return new ResponseEntity<> (HttpStatus.NO_CONTENT);
            } else {
                service.deleteByCityId(cityId);
                return new ResponseEntity<> ("Weather by given cityId deleted successfully.", HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

