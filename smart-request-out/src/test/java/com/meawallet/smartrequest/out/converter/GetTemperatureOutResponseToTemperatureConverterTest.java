package com.meawallet.smartrequest.out.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class GetTemperatureOutResponseToTemperatureConverterTest {
    @Mock
    private GetTemperatureOutResponse getTemperatureOutResponse;
    @InjectMocks
    private GetTemperatureOutResponseToTemperatureConverter converter;

    @Test
    void shouldReturnTemperature() {
        var expectedTemperature = temperature();
        when(getTemperatureOutResponse.getAirTemperature()).thenReturn(25.5);
        when(getTemperatureOutResponse.getTime()).thenReturn(LocalDateTime.parse("2023-03-25T12:00:00"));

        var actualTemperature  = converter.convert(getTemperatureOutResponse);

        assertNotNull(actualTemperature);
        assertEquals(expectedTemperature.getTemperature(), actualTemperature.getTemperature());
        assertEquals(expectedTemperature.getTemperatureAt(), actualTemperature.getTemperatureAt());
    }
    private Temperature temperature() {
        return Temperature.builder()
                .id(1)
                .temperature(25.5)
                .temperatureAt(LocalDateTime.parse("2023-03-25T12:00:00"))
                .build();
    }
}