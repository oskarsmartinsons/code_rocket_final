package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.in.dto.GetTemperatureInResponse;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
public class TemperatureControllerTest {
    @Mock
    private GetLocationTemperatureUseCase getLocationTemperatureUseCase;
    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private TemperatureController temperatureController;

    @Test
    public void shouldReturnTemperatureWhenValidCoordinates() {
        var mockTemperature = temperature();
        var expectedTemperatureInResponse = response();

        when(getLocationTemperatureUseCase.getTemperatureByCoordinates(any(), any()))
                .thenReturn(mockTemperature);
        when(conversionService.convert(mockTemperature, GetTemperatureInResponse.class))
                .thenReturn(response());

        ResponseEntity<GetTemperatureInResponse> actualTemperatureInResponse = temperatureController.getLocationTemperatureByCoordinates(any(), any());

        assertEquals(HttpStatus.OK, actualTemperatureInResponse.getStatusCode());
        assertNotNull(actualTemperatureInResponse.getBody());
        assertEquals(expectedTemperatureInResponse, actualTemperatureInResponse.getBody());
    }

    private Temperature temperature() {
        return Temperature.builder()
                .id(1)
                .temperature(2.3)
                .temperatureAt(LocalDateTime.parse("2023-03-24T17:24:21"))
                .build();
    }

    private  GetTemperatureInResponse response() {
        return new GetTemperatureInResponse(temperature().getTemperature(), temperature().getTemperatureAt());
    }

//    @Test
//    public void getLocationTemperatureByCoordinates_withNullLatitude_shouldThrowException() {
//        assertThrows(MethodArgumentNotValidException.class, () -> {
//            temperatureController.getLocationTemperatureByCoordinates(10000.7, -74.005974);
//        });
//    }


}





