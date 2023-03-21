package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.UpsertTemperatureForLocationUseCase;
import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.core.port.out.SaveTemperaturePort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpsertTemperatureForLocationService implements UpsertTemperatureForLocationUseCase {
    private final SaveTemperaturePort saveTemperaturePort;
    private final SaveLocationPort saveLocationPort;
    private final GetTemperatureExternalService getTemperatureExternalService;
    @Override
    public Temperature upsertTemperatureForLocation(Location location) {
        var temperature = getTemperatureExternalService.getExtTemperature();
        log.debug("Temperature from 3rd party service: {}", temperature);

        var savedTemperature = saveTemperaturePort.saveTemperature(temperature);

        var locationWithTemperature = location.toBuilder()
                .temperature(savedTemperature)
                .build();

        saveLocationPort.saveLocation(locationWithTemperature);

        return savedTemperature;

    }
}
