/*
package com.meawallet.smartrequest.repository.repository;

import com.meawallet.smartrequest.repository.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetLocationByIdAdapter implements GetLocationPort{
    public final LocationRepository locationRepository;
    @Override
    public LocationEntity getLocationById(Integer id) {
        var entity = locationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Location not found."));
        System.out.println(entity);

        return  entity;
    }
}
*/
