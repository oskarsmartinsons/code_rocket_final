package com.meawallet.smartrequest.core.port.in;

import com.meawallet.smartrequest.domain.Temperature;

public interface GetTemperatureFromExtApiUseCase {
    Temperature getTemperatureFromExtApi(Double latitude, Double longitude);
}
