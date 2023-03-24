package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.in.GetTemperatureFromExtApiUseCase;
import com.meawallet.smartrequest.core.port.out.FindLocationWithValidTemperaturePort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class GetLocationTemperatureServiceTest {
    @Mock
    FindLocationWithValidTemperaturePort findLocationWithValidTemperaturePort;
    @Mock
    GetTemperatureFromExtApiService getTemperatureFromExtApiService;
    @InjectMocks
    GetLocationTemperatureService getLocationTemperatureService;

    @Test
    void shouldReturnTemperatureFromCacheIfFound() {
        var expectedTemperature = getTemperature();
        var location = getLocation(expectedTemperature);

        when(findLocationWithValidTemperaturePort.findLocationWithValidTemperature(any(), any()))
                .thenReturn(Optional.of(location));

        var actualTemperature = getLocationTemperatureService.getTemperatureByCoordinates(any(), any());

        assertEquals(expectedTemperature, actualTemperature);
    }

    @Test
    void shouldReturnTemperatureFromExternalApiIfNotFound() {
        var expectedTemperature = getTemperature();

        when(findLocationWithValidTemperaturePort.findLocationWithValidTemperature(any(), any()))
                .thenReturn(Optional.empty());

        when(getTemperatureFromExtApiService.getTemperatureFromExtApi(any(), any()))
                .thenReturn(expectedTemperature);

        var actualTemperature = getLocationTemperatureService.getTemperatureByCoordinates(any(), any());
        assertEquals(expectedTemperature, actualTemperature);
    }

    private Location getLocation(Temperature temperature) {
        return Location.builder()
                .id(1)
                .latitude(66.65)
                .longitude(33.33)
                .temperature(temperature)
                .build();
    }

    private Temperature getTemperature() {
         return Temperature.builder()
                .id(1)
                .temperature(2.3)
                .temperatureAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusHours(1))
                .build();
    }
}