package com.assignment.spring.api.endpoints;

import com.assignment.spring.dto.request.WeatherRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface WeatherApi {

    @ApiOperation(value = "This is weather endpoint.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Response Headers",
                    responseHeaders = {
                            @ResponseHeader(name = "city",
                                    description = "<Enter city name here>",
                                    response = String.class)
                    })
    })
    @GetMapping("/weather")
    ResponseEntity<Object> weather(HttpServletRequest request, String city );

    @GetMapping("/getWeathers")
    ResponseEntity<Object> getWeathers();

    @PutMapping("/updateWeather")
    ResponseEntity<Object> updateWeather(WeatherRequestDto requestDto);

    @DeleteMapping("/deleteWeather")
    ResponseEntity<Object> deleteWeather(Integer cityId);

    @GetMapping("/getWarmestCities")
    ResponseEntity<Object> getTop3WarmestCitiesInCountry(@RequestParam("country") String country);

    @GetMapping("/getColdestCities")
    ResponseEntity<Object> getTop3ColdestCitiesInCountry(@RequestParam("country") String country);
}
