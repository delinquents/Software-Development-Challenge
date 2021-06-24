package com.assignment.spring.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "weather_info")
public class WeatherInfoEntity {

    @Id
    private Integer id;

    @Column(name="mainW")
    private String main;

    private String description;

    @Column(name="icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name="weatherId", nullable=false)
    private WeatherEntity weather;

}
