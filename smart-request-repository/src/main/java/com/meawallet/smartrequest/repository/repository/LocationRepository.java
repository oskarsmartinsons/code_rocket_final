package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.repository.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

//    Optional<LocationEntity> findById(Integer id);

    // TODO - implement method

    @Query("SELECT l " +
            "FROM LocationEntity l " +
            "WHERE l.latitude = :latitude " +
            "AND l.longitude = :longitude " +
            "AND l.temperature.currentHour >= CURRENT_TIMESTAMP")
    LocationEntity getLocationByFilter(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude);


}
