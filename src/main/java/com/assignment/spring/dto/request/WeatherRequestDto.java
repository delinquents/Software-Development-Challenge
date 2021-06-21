package com.assignment.spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherRequestDto {
    private Integer id;
    private String city;
    private String country;
    private Double temperature;
}
