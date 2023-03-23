package com.meawallet.smartrequest.repository.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TemperatureEntityToTemperatureDomainConverter implements Converter<TemperatureEntity, Temperature> {
    @Override
    public Temperature convert(TemperatureEntity entity) {
        return Temperature.builder()
                .id(entity.getId())
                .temperature(entity.getTemperature())
                .temperatureAt(entity.getTemperatureAt())
                .createdAt(entity.getCreatedAt())
                .expirationDate(entity.getExpirationDate())
                .build();
    }
}
