package com.assignment.spring.models.repository;

import com.assignment.spring.models.entities.WindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindRepository extends JpaRepository<WindEntity, Integer> {

    @Query("SELECT W FROM WindEntity W WHERE W.weatherId = :weatherId")
    WindEntity findWindByWeatherId(@Param("weatherId")Integer weatherId);

    @Query("DELETE from WindEntity w where w.weatherId = :weatherId")
    @Modifying
    void deleteWindByWeatherId(@Param("weatherId")Integer weatherId);
}
