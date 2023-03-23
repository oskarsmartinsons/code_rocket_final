package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;


@Component
@AllArgsConstructor
public class LocationDomainToLocationEntityConverter implements Converter<Location, LocationEntity> {
    private final ConversionService conversionService;
    private final TemperatureDomainToTemperatureEntityConverter temperatureDomainToTemperatureEntityConverter;

    @Override
    public LocationEntity convert(Location location) {

        var temp = Optional.ofNullable(location.getTemperature())
                .map(temperatureDomainToTemperatureEntityConverter::convert)
                .orElse(null);

        return LocationEntity.builder()
                .id(location.getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .temperature(temp)
                .build();
    }
}
