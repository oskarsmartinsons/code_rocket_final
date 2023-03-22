package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
import com.meawallet.smartrequest.core.port.in.UpsertTemperatureForLocationUseCase;
import com.meawallet.smartrequest.core.port.out.FindLocationWithValidTemperaturePort;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.domain.Temperature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Slf4j
@Component
@AllArgsConstructor
public class GetLocationTemperatureService implements GetLocationTemperatureUseCase {
    private final FindLocationWithValidTemperaturePort findLocationWithValidTemperaturePort;
    private final UpsertTemperatureForLocationUseCase upsertTemperatureForLocationUseCase;
    private final GetTemperatureFromExtApiPort getTemperatureFromExtApiPort;

    @Override
    public Temperature getTemperatureByCoordinates(Double latitude, Double longitude) {

        var date = LocalDate.now();
        log.debug("Date: {}", date);

        getTemperatureFromExtApiPort.getTemperatureFromExtApi(latitude, longitude);

        var location = findLocationWithValidTemperaturePort.findLocationWithValidTemperature(latitude, longitude, date);
        log.debug("Found Location with valid temperature: {}", location);

        return location.isEmpty() ?
                upsertTemperatureForLocationUseCase.upsertTemperatureForLocation(latitude, longitude)
                :
                location.get().getTemperature();
    }
}
