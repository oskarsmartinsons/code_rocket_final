package com.meawallet.smartrequest.repository.adapter;

import com.meawallet.smartrequest.core.port.out.SaveTemperaturePort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.converter.TemperatureDomainToTemperatureEntityConverter;
import com.meawallet.smartrequest.repository.converter.TemperatureEntityToTemperatureDomainConverter;
import com.meawallet.smartrequest.repository.repository.TemperatureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SaveTemperatureAdapter implements SaveTemperaturePort {
    private final TemperatureRepository temperatureRepository;
    private final TemperatureDomainToTemperatureEntityConverter temperatureDomainToTemperatureEntityConverter;
    private final TemperatureEntityToTemperatureDomainConverter temperatureEntityToTemperatureDomainConverter;
    @Override
    public Temperature saveTemperature(Temperature temperature) {
        log.debug("saveTemp in Adapter received: {}", temperature);

        var entity = temperatureDomainToTemperatureEntityConverter.convert(temperature);

        log.debug("Converted Temp to Entity: {}", entity);

        temperatureRepository.saveAndFlush(entity);

        log.debug("Temp entity Saved: {}", entity);

        return temperatureEntityToTemperatureDomainConverter.convert(entity);
    }
}
