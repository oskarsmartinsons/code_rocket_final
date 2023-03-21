package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
@AllArgsConstructor
public class LocationDomainToLocationEntityConverter implements Converter<Location, LocationEntity> {
    private final ConversionService conversionService;
    private final TemperatureDomainToTemperatureEntityConverter temperatureDomainToTemperatureEntityConverter;

    @Override
    public LocationEntity convert(Location location) {

       //  var temperatureEntity = conversionService.convert(location.getTemperature(), TemperatureEntity.class);
        var temperatureEntity = temperatureDomainToTemperatureEntityConverter.convert(location.getTemperature());

        return LocationEntity.builder()
                .id(location.getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .temperature(temperatureEntity)
                .build();
    }
}
