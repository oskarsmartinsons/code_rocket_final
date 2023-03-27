package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.GetTemperatureFromExtApiUseCase;
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
public class GetTemperatureFromExtApiService implements GetTemperatureFromExtApiUseCase {
    private final SaveTemperaturePort saveTemperaturePort;
    private final SaveLocationPort saveLocationPort;
    private final FindLocationByLatitudeAndLongitudePort findLocationByLatitudeAndLongitudePort;
    private final GetTemperatureFromExtApiPort getTemperatureFromExtApiPort;

    @Override
    public Temperature getTemperatureFromExtApi(Double latitude, Double longitude) {
        var temperature = getTemperatureFromExtApiPort.getTemperatureFromExtApi(latitude, longitude);
        log.debug("Retrieved Temperature from external API: {}", temperature);

        var savedTemperature = saveTemperaturePort.saveTemperature(temperature);

        upsertLocationWithTemperature(latitude,longitude,savedTemperature);

        return savedTemperature;
    }

        private void upsertLocationWithTemperature(Double latitude, Double longitude, Temperature temperature) {
            var location = findLocationByLatitudeAndLongitudePort
                    .findLocationByLatitudeAndLongitude(latitude, longitude)
                    .map(existingLocation -> existingLocation.toBuilder()
                            .temperature(temperature)
                            .build())
                    .orElseGet(() -> Location.builder()
                            .latitude(latitude)
                            .longitude(longitude)
                            .temperature(temperature)
                            .build());

            saveLocationPort.saveLocation(location);
        }
}
