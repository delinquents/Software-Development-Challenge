package com.assignment.spring.services.impl;


import com.assignment.spring.models.entities.WindEntity;
import com.assignment.spring.models.repository.WindRepository;
import com.assignment.spring.services.WindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class WindServiceImpl implements WindService {

    private WindRepository windRepository;


    @Override
    @Transactional
    public WindEntity saveWeather(WindEntity entity) {
        return windRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteWindByWeatherId(Integer weatherId) {
        windRepository.deleteWindByWeatherId(weatherId);
    }

}
