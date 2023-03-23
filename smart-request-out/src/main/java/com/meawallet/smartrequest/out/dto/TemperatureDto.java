package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@JsonDeserialize(using = TemperatureDtoDeserializer.class)
public class TemperatureDto {
    LocalDateTime time;
}
