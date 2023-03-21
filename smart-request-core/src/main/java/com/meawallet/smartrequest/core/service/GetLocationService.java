package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
import com.meawallet.smartrequest.core.port.out.GetLocationPort;
import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.domain.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetLocationService implements GetLocationUseCase {
    private final GetLocationPort getLocationPort;
    private final SaveLocationPort saveLocationPort;

    @Override
    public Location getLocationById(Integer id) {
        return getLocationPort.getLocationById(id)
               .orElseThrow(()-> new IllegalArgumentException("Location not found with id" + id));

    }

    @Override
    public Location getLocation(Integer id) {
        return getLocationPort.getLocationById(id)
                .orElse(null);
    }
}
