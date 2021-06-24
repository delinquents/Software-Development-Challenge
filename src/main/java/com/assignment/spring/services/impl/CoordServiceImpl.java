package com.assignment.spring.services.impl;

import com.assignment.spring.ui.dto.response.CoordResponseDto;
import com.assignment.spring.models.entities.CoordEntity;
import com.assignment.spring.models.repository.CoordRepository;
import com.assignment.spring.services.CoordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CoordServiceImpl implements CoordService {

    private CoordRepository coordRepository;

    @Override
    @Transactional
    public CoordEntity saveWeather(CoordEntity entity) {
        return coordRepository.save(entity);
    }

    @Override
    public CoordResponseDto getCordByWeatherId(Integer weatherId) {
        return coordRepository.getCordByWeatherId(weatherId);
    }

    @Override
    @Transactional
    public void deleteCoordByWeatherId(Integer weatherId) {
        coordRepository.deleteCoordByWeatherId(weatherId);
    }
}
