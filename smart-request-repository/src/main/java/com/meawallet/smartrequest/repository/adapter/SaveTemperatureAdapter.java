package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.SaveTemperaturePort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.converter.TemperatureDomainToTemperatureEntityConverter;
import com.meawallet.smartrequest.repository.converter.TemperatureEntityToTemperatureDomainConverter;
import com.meawallet.smartrequest.repository.entity.TemperatureEntity;
import com.meawallet.smartrequest.repository.repository.TemperatureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class SaveTemperatureAdapter implements SaveTemperaturePort {
    private final TemperatureRepository temperatureRepository;
    private final ConversionService conversionService;

    @Override
    public Temperature saveTemperature(Temperature temperature) {
        var entity = Optional.ofNullable(conversionService.convert(temperature, TemperatureEntity.class))
                .orElseThrow(()->new IllegalArgumentException("Temperature can't be converted, it is null."));

        temperatureRepository.saveAndFlush(entity);
        log.debug("Temperature saved in database: {}", entity);
        return conversionService.convert(entity, Temperature.class);
    }
}
