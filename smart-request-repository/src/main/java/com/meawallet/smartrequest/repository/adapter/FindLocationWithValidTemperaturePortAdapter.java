package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.FindLocationWithValidTemperaturePort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.converter.LocationEntityToLocationDomainConverter;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class FindLocationWithValidTemperaturePortAdapter implements FindLocationWithValidTemperaturePort {
    private final LocationRepository locationRepository;
    private final LocationEntityToLocationDomainConverter locationEntityToLocationDomainConverter;

    @Override
    public Optional<Location> findLocationWithValidTemperature(Double latitude, Double longitude) {

        return locationRepository.findLocationWithValidTemperature(latitude, longitude)
                .map(locationEntityToLocationDomainConverter::convert);
    }
}
