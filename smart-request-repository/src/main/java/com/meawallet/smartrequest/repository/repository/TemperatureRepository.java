package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Integer> {
}
