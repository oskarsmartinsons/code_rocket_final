package com.meawallet.smartrequest.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class Temperature {
    Integer id;
    Double temperature;
    String unit;
    LocalDateTime lastTimeUpdated;
    LocalDate timeStamp;
}
