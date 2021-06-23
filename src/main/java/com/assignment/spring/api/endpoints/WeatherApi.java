package com.assignment.spring.api.endpoints;

import com.assignment.spring.dto.request.WeatherRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface WeatherApi {

    @ApiOperation(value = "This is weather endpoint.",
            notes = "Endpoint for inserting weather to database from weather cloud")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "ok",
                    responseHeaders = {
                            @ResponseHeader(name = "city",
                                    description = "Enter city name here",
                                    response = String.class)
                    }),
            @ApiResponse(code = 400, message = "Bad Request. Invalid city name, validation error.")
    })
    @GetMapping("/weather")
    ResponseEntity<Object> weather(HttpServletRequest request, String city );


    @ApiOperation(value = "This is for all weathers currently in database endpoint.",
            notes = "Endpoint for fetching weathers from database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "ok")
    })
    @GetMapping("/getWeathers")
    ResponseEntity<Object> getWeathers();


    @PutMapping("/updateWeather")
    ResponseEntity<Object> updateWeather(WeatherRequestDto requestDto);


    @DeleteMapping("/deleteWeather")
    ResponseEntity<Object> deleteWeather(Integer cityId);

    @ApiOperation(value = "Shows warmest cities",
            notes = "Endpoint returns 3 coldest cities by give country name. Note: given country name must be abbreviate, example: Germany should be DE, Netherlands  - NL")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "ok"),
            @ApiResponse(code = 204, message = "There is no content for given country name", response = String.class),
    })
    @GetMapping("/getWarmestCities")
    ResponseEntity<Object> getTop3WarmestCitiesInCountry(@RequestParam("country") String country);


    @ApiOperation(value = "Shows coldest cities",
            notes = "Endpoint returns 3 coldest cities by give country name. Note: given country name must be abbreviate, example: Germany should be DE, Netherlands  - NL")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "ok"),
            @ApiResponse(code = 204, message = "There is no content for given country name", response = String.class),
    })
    @GetMapping("/getColdestCities")
    ResponseEntity<Object> getTop3ColdestCitiesInCountry(@RequestParam("country") String country);
}
