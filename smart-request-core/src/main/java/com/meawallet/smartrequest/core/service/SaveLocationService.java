package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.domain.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveLocationService implements SaveLocationUseCase {
    private final SaveLocationPort saveLocationPort;

    @Override
    public void saveLocation(Location location) {

        saveLocationPort.saveLocation(location);
    }
}
