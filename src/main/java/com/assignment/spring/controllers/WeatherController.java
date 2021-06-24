package com.assignment.spring.controllers;

import com.assignment.spring.api.endpoints.WeatherApi;
import com.assignment.spring.constants.Constants;
import com.assignment.spring.ui.dto.request.WeatherRequestDto;
import com.assignment.spring.ui.dto.response.WeatherResponseDto;
import com.assignment.spring.mapper.CoordMappper;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.mapper.WindMapper;
import com.assignment.spring.models.entities.CoordEntity;
import com.assignment.spring.models.entities.WeatherEntity;
import com.assignment.spring.api.templates.WeatherResponse;
import com.assignment.spring.models.entities.WindEntity;
import com.assignment.spring.services.CoordService;
import com.assignment.spring.services.WeatherService;
import com.assignment.spring.services.WindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private CoordMappper coordMappper;
    private WeatherService weatherService;
    private WindService windService;
    private CoordService coordService;

    public ResponseEntity<Object> weather(HttpServletRequest request, @RequestParam("city") String city ) {
        try {
            if (city.equals("")) {
                return new ResponseEntity<> ("City is required.", HttpStatus.BAD_REQUEST);
            } else {
                String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
                ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
                WeatherEntity weatherEntity = weatherService.saveWeather(weatherMapper.mapToEntity(Objects.requireNonNull(response.getBody())));
                WindEntity windEntity = windService.saveWeather(windMapper.mapToEntity(Objects.requireNonNull(response.getBody())));
                CoordEntity coordEntity = coordService.saveWeather(coordMappper.mapToEntity(Objects.requireNonNull(response.getBody()),weatherEntity));
                return new ResponseEntity<> (weatherMapper.mapToResponseFromEntity(weatherEntity, windEntity, coordEntity), HttpStatus.OK);}
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

    public ResponseEntity<Object> updateWeather(@Valid @RequestBody WeatherRequestDto requestDto) {
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
                coordService.deleteCoordByWeatherId(weatherId);
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
        return new ResponseEntity<> (weatherService.getColdestCitiesInCountry(country), HttpStatus.OK);
    }
}

