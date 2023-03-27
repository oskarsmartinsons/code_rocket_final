package com.meawallet.smartrequest.in.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.in.dto.GetTemperatureInResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TemperatureToGetTemperatureInResponseConverter implements Converter<Temperature, GetTemperatureInResponse> {
    @Override
    public GetTemperatureInResponse convert(Temperature temperature) {
        return new GetTemperatureInResponse(
                temperature.getTemperature());
    }
}
