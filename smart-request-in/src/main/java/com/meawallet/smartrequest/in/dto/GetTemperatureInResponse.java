package com.meawallet.smartrequest.in.dto;

import lombok.Value;

import java.time.LocalDateTime;

public record GetTemperatureInResponse(
        Double temperature,
        LocalDateTime temperatureAt
) {
}
