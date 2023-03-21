package com.meawallet.smartrequest.core.port.out;

import com.meawallet.smartrequest.domain.Temperature;

public interface SaveTemperaturePort {
    Temperature saveTemperature(Temperature temperature);
}
