package com.meawallet.smartrequest.out.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GetTemperatureOutResponseToTemperatureConverter {
    public Temperature convert (GetTemperatureOutResponse response) {
        return Temperature.builder()
                .temperature(BigDecimal.valueOf(response.getAirTemperature()))
              //  .unit(response.unit())
                .timeStamp(response.getTime().toLocalDate())
                .build();
    }
}
