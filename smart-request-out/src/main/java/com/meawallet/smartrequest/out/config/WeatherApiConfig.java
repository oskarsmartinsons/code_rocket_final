package com.meawallet.smartrequest.out.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
public class WeatherApiConfig {
    @NotBlank
    private String weatherUrl;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
