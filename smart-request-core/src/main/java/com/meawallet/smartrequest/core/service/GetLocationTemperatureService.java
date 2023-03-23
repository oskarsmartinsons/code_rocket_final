package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
import com.meawallet.smartrequest.core.port.in.UpsertTemperatureForLocationUseCase;
import com.meawallet.smartrequest.core.port.out.FindLocationWithValidTemperaturePort;
import com.meawallet.smartrequest.domain.Temperature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GetLocationTemperatureService implements GetLocationTemperatureUseCase {
    private final FindLocationWithValidTemperaturePort findLocationWithValidTemperaturePort;
    private final UpsertTemperatureForLocationUseCase upsertTemperatureForLocationUseCase;

    @Override
    public Temperature getTemperatureByCoordinates(Double latitude, Double longitude) {
        var locationWithValidTemperature = findLocationWithValidTemperaturePort.findLocationWithValidTemperature(latitude, longitude);
        log.debug("Found Location with valid temperature: {}", locationWithValidTemperature);

        return locationWithValidTemperature.isEmpty()
                ? upsertTemperatureForLocationUseCase.upsertTemperatureForLocation(latitude, longitude)
                : locationWithValidTemperature.get().getTemperature();
    }
}
