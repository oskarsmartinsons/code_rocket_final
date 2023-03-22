package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Integer> {

/*    // TODO - implement method
    @Query("SELECT t FROM TemperatureEntity t " +
            "JOIN t.location l " +
            "WHERE l.latitude = :lat AND l.longitude = :lon " +
            "AND t.currentHour <= :currentHour " +
            "ORDER BY t.currentHour DESC")
    Optional<TemperatureEntity> findByLatLonAndCurrentHour(
            @Param("lat") Double latitude,
            @Param("lon") Double longitude,
            @Param("currentHour") LocalDateTime currentHour
    );*/
}
