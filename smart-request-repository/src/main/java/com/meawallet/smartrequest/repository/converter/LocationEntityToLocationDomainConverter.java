package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class LocationEntityToLocationDomainConverter{
    private final ConversionService conversionService;
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
