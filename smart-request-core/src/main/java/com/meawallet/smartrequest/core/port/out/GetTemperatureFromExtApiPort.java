package com.meawallet.smartrequest.core.port.out;

import com.meawallet.smartrequest.domain.Temperature;

public interface GetTemperatureFromExtApiPort {
    Temperature getTemperatureFromExtApi(Double latitude, Double longitude);
}
