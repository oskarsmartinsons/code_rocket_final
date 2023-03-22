package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.UpsertTemperatureForLocationUseCase;
import com.meawallet.smartrequest.core.port.out.FindByLatitudeAndLongitudePort;
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
    private final FindByLatitudeAndLongitudePort findByLatitudeAndLongitudePort;
    private final GetTemperatureExternalService getTemperatureExternalService;

    private final GetTemperatureFromExtApiPort getTemperatureFromExtApiPort;

    @Override
    public Temperature upsertTemperatureForLocation(Double latitude, Double longitude) {
     //   var temperature = getTemperatureExternalService.getExtTemperature();
        var temperature = getTemperatureFromExtApiPort.getTemperatureFromExtApi(latitude, longitude);
        log.debug("Temperature from 3rd party service: {}", temperature);

        var savedTemperature = saveTemperaturePort.saveTemperature(temperature);
        log.debug("Saved Temperature: {}", savedTemperature);

        var locationWithTemperature = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .temperature(savedTemperature)
                .build();

        upsertLocation(locationWithTemperature);

        //  upsertLocation(latitude, longitude, savedTemperature);

        return savedTemperature;

    }

    private void upsertLocation(Location location) {
        var existingLocation = findByLatitudeAndLongitudePort
                .findLocationByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());

        if (existingLocation.isEmpty()) {
            saveLocationPort.saveLocation(location);
        } else {
            saveLocationPort.saveLocation(existingLocation.get());

        }
    }

/*    private void upsertLocation(Double latitude, Double longitude, Temperature savedTemperature) {
        var location = findByLatitudeAndLongitudePort.findLocationByLatitudeAndLongitude(latitude, longitude)
                .orElseGet(Location::new);
        log.debug("This is findLocationByLatLon result: {}", location);

        var locationWithTemperature = Location.builder()
                .id(location.getId())
                .latitude(latitude)
                .longitude(longitude)
                .temperature(savedTemperature)
                .build();
        log.debug("This is locationWithTemperature result: {}", locationWithTemperature);

        saveLocationPort.saveLocation(locationWithTemperature);
    }*/
}
