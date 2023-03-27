package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Integer> {
}
