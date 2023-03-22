package com.meawallet.smartrequest.core.port.in;

import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;

public interface UpsertTemperatureForLocationUseCase {
    Temperature upsertTemperatureForLocation(Double latitude, Double longitude);
}
