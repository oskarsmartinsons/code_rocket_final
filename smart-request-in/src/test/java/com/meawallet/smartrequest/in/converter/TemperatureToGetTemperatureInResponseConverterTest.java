package com.meawallet.smartrequest.in.converter;

import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.in.dto.GetTemperatureInResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class TemperatureToGetTemperatureInResponseConverterTest {
    @Mock
    private Temperature temperature;
    @InjectMocks
    private TemperatureToGetTemperatureInResponseConverter converter;

    @Test
    void shouldReturnGetTemperatureInResponse() {
        var expectedResponse = response();

        when(temperature.getTemperature()).thenReturn(33.33);

        var actualResponse  = converter.convert(temperature);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.temperature(), actualResponse.temperature());
    }

    private GetTemperatureInResponse response() {
        return new GetTemperatureInResponse(33.33);
    }
}