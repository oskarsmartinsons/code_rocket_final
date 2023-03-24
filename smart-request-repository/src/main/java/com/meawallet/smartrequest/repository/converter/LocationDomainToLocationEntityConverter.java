package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
@AllArgsConstructor
public class LocationDomainToLocationEntityConverter{
    private final ConversionService conversionService;

    public LocationEntity convert(Location location) {
        var ent = conversionService.convert(location.getTemperature(), TemperatureEntity.class);

        return LocationEntity.builder()
                .id(location.getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .temperature(ent)
                .build();
    }
}
