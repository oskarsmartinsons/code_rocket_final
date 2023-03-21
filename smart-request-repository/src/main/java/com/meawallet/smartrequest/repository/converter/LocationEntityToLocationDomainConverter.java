package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationEntityToLocationDomainConverter implements Converter<LocationEntity, Location> {
    private final ConversionService conversionService;
    @Override
    public Location convert(LocationEntity entity) {
        var temperature = conversionService.convert(entity.getTemperature(), Temperature.class);
        return Location.builder()
                .id(entity.getId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .temperature(temperature)
                .build();
    }
}
