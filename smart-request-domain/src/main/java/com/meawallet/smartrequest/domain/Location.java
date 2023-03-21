package com.meawallet.smartrequest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    Integer id;
    Double latitude;
    Double longitude;
    Temperature temperature;
}
