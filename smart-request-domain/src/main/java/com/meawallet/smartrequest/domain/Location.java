package com.meawallet.smartrequest.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Location {
    Integer id;
    String latitude;
    String longitude;
    Temperature temperature;
}
