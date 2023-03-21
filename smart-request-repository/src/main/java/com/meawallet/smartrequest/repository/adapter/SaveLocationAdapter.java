package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.converter.LocationDomainToLocationEntityConverter;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationRepository locationRepository;
    private final LocationDomainToLocationEntityConverter locationDomainToLocationEntityConverter;
    @Override
    public void saveLocation(Location location) {
        log.debug("saveLocation in Adapter received: {}", location);

        var entity = locationDomainToLocationEntityConverter.convert(location);

        log.debug("Converted Location to Entity: {}", entity);

        locationRepository.save(entity);

        log.debug("Location entity Saved: {}", entity);

    }


}
