package com.meawallet.smartrequest.domain;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class Temperature {
    Integer id;
    Double temperature;
    LocalDateTime temperatureAt;
    LocalDateTime createdAt;
    LocalDateTime expirationDate;
}
