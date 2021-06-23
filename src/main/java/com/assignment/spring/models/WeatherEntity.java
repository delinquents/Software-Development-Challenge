package com.assignment.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "weather")
public class WeatherEntity {

    @Id
    private Integer id;

    @Column(nullable=false)
    @NotBlank(message = "City is mandatory")
    private String city;

    @Column(nullable=false)
    @Size(min = 2, max = 2, message
            = "Country must be 2 characters")
    @NotBlank(message = "Country is mandatory")
    private String country;

    private Long visibility;

    private Double temperature;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private Integer cod;



}
