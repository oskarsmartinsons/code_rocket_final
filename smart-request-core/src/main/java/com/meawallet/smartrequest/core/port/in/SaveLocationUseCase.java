package com.meawallet.smartrequest.core.port.in;

import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.domain.Location;

public interface SaveLocationUseCase {
    void saveLocation(Location location);
}
