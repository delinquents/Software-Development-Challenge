package com.assignment.spring.ui.dto.request;

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
    private String description;
    private Long visibility;
    private Double temperature;
    private Integer cod;
}
