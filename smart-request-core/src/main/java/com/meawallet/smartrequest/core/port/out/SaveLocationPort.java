package com.meawallet.smartrequest.core.port.out;

import com.meawallet.smartrequest.domain.Location;

public interface SaveLocationPort {
    void saveLocation(Location location);
}
