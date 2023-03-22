package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetTemperatureOutResponse {
    LocalDateTime time;
    Double airTemperature;
}