package com.meawallet.smartrequest.domain;

import lombok.*;

@Data
@Builder(toBuilder = true)
public class Location {
    Integer id;
    Double latitude;
    Double longitude;
    Temperature temperature;
}
