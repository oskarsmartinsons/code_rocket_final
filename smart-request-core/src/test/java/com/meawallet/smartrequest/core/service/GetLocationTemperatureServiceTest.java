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
    private FindLocationWithValidTemperaturePort findLocationWithValidTemperaturePort;
    @Mock
    private GetTemperatureFromExtApiUseCase getTemperatureFromExtApiUseCase;
    @InjectMocks
    private GetLocationTemperatureService getLocationTemperatureService;

    @Test
    void shouldReturnTemperatureFromCacheWhenFound() {
        var expectedTemperature = temperature();

        when(findLocationWithValidTemperaturePort.findLocationWithValidTemperature(any(), any()))
                .thenReturn(Optional.of(location()));

        var actualTemperature = getLocationTemperatureService.getTemperatureByCoordinates(any(), any());

        assertEquals(expectedTemperature, actualTemperature);
    }

    @Test
    void shouldReturnTemperatureFromExternalApiWhenNotFound() {
        var expectedTemperature = temperature();

        when(findLocationWithValidTemperaturePort.findLocationWithValidTemperature(any(), any()))
                .thenReturn(Optional.empty());
        when(getTemperatureFromExtApiUseCase.getTemperatureFromExtApi(any(), any()))
                .thenReturn(expectedTemperature);

        var actualTemperature = getLocationTemperatureService.getTemperatureByCoordinates(any(), any());

        assertEquals(expectedTemperature, actualTemperature);
    }

    private Location location() {
        return Location.builder()
                .id(1)
                .latitude(66.65)
                .longitude(33.33)
                .temperature(temperature())
                .build();
    }

    private Temperature temperature() {
         return Temperature.builder()
                .id(1)
                .temperature(2.3)
                .temperatureAt(LocalDateTime.parse("2023-03-24T17:00:00"))
                .build();
    }
}