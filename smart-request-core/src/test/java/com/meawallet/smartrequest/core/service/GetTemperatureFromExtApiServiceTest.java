package com.meawallet.smartrequest.core.service;

import com.meawallet.smartrequest.core.port.out.FindLocationByLatitudeAndLongitudePort;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.core.port.out.SaveLocationPort;
import com.meawallet.smartrequest.core.port.out.SaveTemperaturePort;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MockitoSettings
class GetTemperatureFromExtApiServiceTest {
    @Mock
    private SaveTemperaturePort saveTemperaturePort;
    @Mock
    private SaveLocationPort saveLocationPort;
    @Mock
    private FindLocationByLatitudeAndLongitudePort findLocationByLatitudeAndLongitudePort;
    @Mock
    private GetTemperatureFromExtApiPort getTemperatureFromExtApiPort;
    @InjectMocks
    private GetTemperatureFromExtApiService getTemperatureFromExtApiService;
    @Captor
    private ArgumentCaptor<Location> captor;

    @Test
    void shouldReturnTemperatureFromExtApi() {
        var expectedTemperature = temperature();

        when(getTemperatureFromExtApiPort.getTemperatureFromExtApi(any(),any()))
                .thenReturn(expectedTemperature);
        when(saveTemperaturePort.saveTemperature(any()))
                .thenReturn(expectedTemperature);

        var actualTemperature = getTemperatureFromExtApiService.getTemperatureFromExtApi(any(),any());

        assertEquals(expectedTemperature, actualTemperature);
        verify(saveTemperaturePort,times(1))
                .saveTemperature(any());
        verify(findLocationByLatitudeAndLongitudePort, times(1))
                .findLocationByLatitudeAndLongitude(any(),any());
        verify(saveLocationPort, times(1))
                .saveLocation(any());
    }

    @Test
    void shouldSaveNewLocationWhenNotFound(){
        var latitude = 11.11;
        var longitude = 99.99;
        when(saveTemperaturePort.saveTemperature(any()))
                .thenReturn(temperature());
        when(findLocationByLatitudeAndLongitudePort.findLocationByLatitudeAndLongitude(latitude,longitude))
                .thenReturn(Optional.empty());

        getTemperatureFromExtApiService.getTemperatureFromExtApi(latitude,longitude);

        verify(saveLocationPort).saveLocation(captor.capture());

        Location capturedLocation = captor.getValue();
        assertEquals(latitude, capturedLocation.getLatitude(), "Latitude failed.");
        assertEquals(longitude, capturedLocation.getLongitude(), "Longitude failed.");
        assertEquals(temperature(), capturedLocation.getTemperature(),"Temperature failed.");
    }

    @Test
    void shouldUpdateExistingLocationWhenFound() {
        var latitude = 66.66;
        var longitude = 33.33;
        when(saveTemperaturePort.saveTemperature(any()))
                .thenReturn(temperature());
        when(findLocationByLatitudeAndLongitudePort.findLocationByLatitudeAndLongitude(latitude,longitude))
                .thenReturn(Optional.of(location()));

        getTemperatureFromExtApiService.getTemperatureFromExtApi(latitude,longitude);

        verify(saveLocationPort).saveLocation(captor.capture());

        Location capturedLocation = captor.getValue();
        assertEquals(latitude, capturedLocation.getLatitude(), "Latitude failed.");
        assertEquals(longitude, capturedLocation.getLongitude(), "Longitude failed.");
        assertEquals(temperature(), capturedLocation.getTemperature(),"Temperature failed.");

    }

    private Temperature temperature() {
        return Temperature.builder()
                .id(1)
                .temperature(2.3)
                .temperatureAt(LocalDateTime.parse("2023-03-24T17:24:21"))
                .build();
    }
    private Location location() {
        return Location.builder()
                .id(1)
                .latitude(66.66)
                .longitude(33.33)
                .temperature(temperature())
                .build();
    }
}