package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.core.port.in.SaveTemperatureUseCase;
import com.meawallet.smartrequest.core.port.out.SaveTemperaturePort;
import com.meawallet.smartrequest.domain.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveTemperatureService implements SaveTemperatureUseCase {
    private final SaveTemperaturePort saveTemperaturePort;
    @Override
    public Temperature saveTemperature(Temperature temperature) {
        return saveTemperaturePort.saveTemperature(temperature);
    }
}
