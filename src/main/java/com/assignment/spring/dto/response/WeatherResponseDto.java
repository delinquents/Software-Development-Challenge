package com.assignment.spring.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "city",
        "country",
        "temperature",
        "wind",
        "visibility",
        "description",
})
public class WeatherResponseDto {
    private Integer id;
    private String city;
    private String country;
    private String description;
    private Long visibility;
    private Double temperature;
    private WindResponseDto wind;

    public WeatherResponseDto(String city, String description, Double temperature) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
    }
}
