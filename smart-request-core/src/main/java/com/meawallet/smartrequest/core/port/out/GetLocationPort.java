package com.meawallet.smartrequest.core.port.out;

import com.meawallet.smartrequest.domain.Location;

import java.util.Optional;

public interface GetLocationPort {
    Optional<Location> getLocationById(Integer id);
}
