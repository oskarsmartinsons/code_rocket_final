package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TemperatureDomainToTemperatureEntityConverter implements Converter<Temperature, TemperatureEntity> {
    @Override
    public TemperatureEntity convert(Temperature temperature) {
        return TemperatureEntity.builder()
                .id(temperature.getId())
                .temperature(temperature.getTemperature())
                .temperatureAt(temperature.getTemperatureAt())
                .createdAt(temperature.getCreatedAt())
                .expirationDate(temperature.getExpirationDate())
                .build();
    }
}
