package com.assignment.spring.models.entities;

import lombok.*;

import javax.persistence.*;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter@Setter
@Table(name = "coord")
public class CoordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double lon;

    private Double lat;

    @OneToOne(targetEntity = WeatherEntity.class, cascade = CascadeType.ALL)
    private WeatherEntity weather;
}
