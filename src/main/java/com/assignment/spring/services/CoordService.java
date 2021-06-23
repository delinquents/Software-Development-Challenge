package com.assignment.spring.services;

import com.assignment.spring.dto.response.CoordResponseDto;
import com.assignment.spring.models.CoordEntity;

public interface CoordService {
    CoordEntity saveWeather(CoordEntity entity);
    CoordResponseDto getCordByWeatherId(Integer weatherId);
    void deleteCoordByWeatherId(Integer weatherId);
}
