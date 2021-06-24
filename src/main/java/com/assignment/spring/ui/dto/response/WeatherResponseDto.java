package com.assignment.spring.ui.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
        "weather",
        "cod",
})
public class WeatherResponseDto {
    private Integer id;
    private String city;
    private String country;
    private Long visibility;
    private Double temperature;
    private WindResponseDto wind;
    private CoordResponseDto coord;
    @JsonProperty("weather")
    private List<WeatherInfoResponseDto> weatherInfos;
    private Integer cod;

    public WeatherResponseDto(String city,Double temperature) {
        this.city = city;
        this.temperature = temperature;
    }
}
