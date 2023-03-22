package com.meawallet.smartrequest.core.port.out;

import com.meawallet.smartrequest.domain.Location;

import java.time.LocalDate;
import java.util.Optional;

public interface FindLocationWithValidTemperaturePort {
    Optional<Location> findLocationWithValidTemperature(Double latitude, Double longitude, LocalDate date);
}
