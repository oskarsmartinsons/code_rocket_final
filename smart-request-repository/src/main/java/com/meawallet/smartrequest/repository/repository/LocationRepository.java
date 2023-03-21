package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.repository.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

//    Optional<LocationEntity> findById(Integer id);

}
