package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@JsonDeserialize(using = GetTemperatureOutResponseDeserializer.class)
public class GetTemperatureOutResponse {
    LocalDateTime time;
    Double airTemperature;
}