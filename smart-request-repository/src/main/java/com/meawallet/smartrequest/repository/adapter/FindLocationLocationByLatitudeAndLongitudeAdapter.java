package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.FindLocationByLatitudeAndLongitudePort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.converter.LocationEntityToLocationDomainConverter;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindLocationLocationByLatitudeAndLongitudeAdapter implements FindLocationByLatitudeAndLongitudePort {
    private final LocationRepository locationRepository;
    private final LocationEntityToLocationDomainConverter locationEntityToLocationDomainConverter;
    @Override
    public Optional<Location> findLocationByLatitudeAndLongitude(Double latitude, Double longitude) {
        return locationRepository.findByLatitudeAndLongitude(latitude,longitude)
                .map(locationEntityToLocationDomainConverter::convert);
    }
}
