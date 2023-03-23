package com.meawallet.smartrequest.out.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import org.springframework.stereotype.Component;

@Component
public class GetTemperatureOutResponseToTemperatureConverter {
    public Temperature convert (GetTemperatureOutResponse response) {
        return Temperature.builder()
                .temperature(response.getAirTemperature())
                .temperatureAt(response.getTime())
                .build();
    }
}
