package com.meawallet.smartrequest.core.port.in;

import com.meawallet.smartrequest.domain.Location;

import java.util.Optional;

public interface GetLocationUseCase {
    Location getLocationById(Integer id);
}
