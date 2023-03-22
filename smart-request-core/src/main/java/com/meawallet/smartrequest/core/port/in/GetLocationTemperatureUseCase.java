package com.meawallet.smartrequest.core.port.in;

import com.meawallet.smartrequest.domain.Temperature;

public interface GetLocationTemperatureUseCase {
    Temperature getTemperatureByCoordinates(Double latitude, Double longitude);
}
