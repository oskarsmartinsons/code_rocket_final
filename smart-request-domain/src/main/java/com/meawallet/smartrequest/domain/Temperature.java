package com.meawallet.smartrequest.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class Temperature {
    Integer id;
    BigDecimal temperature;
    String unit;
    LocalDate timeStamp;
}
