package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
import com.meawallet.smartrequest.core.port.out.GetLocationPort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.converter.LocationEntityToLocationDomainConverter;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetLocationByIdAdapter implements GetLocationPort {
    private final LocationRepository locationRepository;
    private final LocationEntityToLocationDomainConverter locationEntityToLocationDomainConverter;


    @Override
    public Optional<Location> getLocationById(Integer id) {
        return locationRepository.findById(id)
                .map(locationEntityToLocationDomainConverter::convert);
    }
}
