package com.meawallet.smartrequest.out.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WeatherApiConfig {
    @NotBlank
    private String weatherUrl;
    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;
}
