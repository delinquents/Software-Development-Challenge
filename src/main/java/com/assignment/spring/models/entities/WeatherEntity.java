package com.assignment.spring.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


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

    @OneToMany(mappedBy="weather", cascade = CascadeType.ALL)
    private List<WeatherInfoEntity> weatherInfos;

    private Integer cod;


    public WeatherEntity(Integer id) {
        this.id = id;
    }
}
