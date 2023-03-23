package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.converter.LocationDomainToLocationEntityConverter;
import com.meawallet.smartrequest.repository.converter.LocationEntityToLocationDomainConverter;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@AllArgsConstructor
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationRepository locationRepository;
    private final LocationDomainToLocationEntityConverter locationDomainToLocationEntityConverter;
    private final LocationEntityToLocationDomainConverter locationEntityToLocationDomainConverter;
    @Override
    public Location saveLocation(Location location) {

        var entity = locationDomainToLocationEntityConverter.convert(location);
        locationRepository.saveAndFlush(entity);

        log.debug("Location saved in database: {}", entity);

        return locationEntityToLocationDomainConverter.convert(entity);
    }
}
