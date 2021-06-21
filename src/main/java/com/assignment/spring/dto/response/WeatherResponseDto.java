package com.assignment.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherResponseDto {
    private Integer id;
    private String city;
    private String country;
    private Double temperature;

}
