package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.UpsertTemperatureForLocationUseCase;
import com.meawallet.smartrequest.core.port.out.FindLocationByLatitudeAndLongitudePort;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
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
    private final FindLocationByLatitudeAndLongitudePort findLocationByLatitudeAndLongitudePort;
    private final GetTemperatureFromExtApiPort getTemperatureFromExtApiPort;

    @Override
    public Temperature upsertTemperatureForLocation(Double latitude, Double longitude) {

        var temperature = getTemperatureFromExtApiPort.getTemperatureFromExtApi(latitude, longitude);
        log.debug("Retrieved Temperature from 3rd party service: {}", temperature);

        var savedTemperature = saveTemperaturePort.saveTemperature(temperature);

        var locationWithTemperature = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .temperature(savedTemperature)
                .build();

        upsertLocation(locationWithTemperature);

        return savedTemperature;

    }

    private void upsertLocation(Location location) {
        var existingLocation = findLocationByLatitudeAndLongitudePort
                .findLocationByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());
        log.debug("Found existing Location: {}", existingLocation);

        if (existingLocation.isEmpty()) {
            saveLocationPort.saveLocation(location);
        } else {
            saveLocationPort.saveLocation(existingLocation.get());
        }
    }
}
