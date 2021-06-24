package com.assignment.spring.services;

import com.assignment.spring.models.entities.WindEntity;

public interface WindService {
    WindEntity saveWeather(WindEntity entity);
    void deleteWindByWeatherId(Integer weatherId);
}
