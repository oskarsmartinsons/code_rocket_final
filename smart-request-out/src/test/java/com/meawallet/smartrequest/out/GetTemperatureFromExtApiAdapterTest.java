package com.meawallet.smartrequest.out;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.config.WeatherApiConfig;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetTemperatureFromExtApiAdapterTest {
    private GetTemperatureFromExtApiPort getTemperatureFromExtApiAdapter;
    @Mock
    private RestTemplate restTemplateMock;
    @Mock
    private WeatherApiConfig weatherApiConfigMock;
    @Mock
    private ConversionService conversionServiceMock;
    @Mock
    private ObjectMapper objectMapperMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getTemperatureFromExtApiAdapter = new GetTemperatureFromExtApiAdapter(restTemplateMock, weatherApiConfigMock, conversionServiceMock, objectMapperMock);
    }

    @Test
    void testGetTemperatureFromExtApiSuccess() throws IOException {
        Double latitude = 0.0;
        Double longitude = 0.0;
        List<GetTemperatureOutResponse> responses = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        var response = getTemperatureOutResponse();
        response.setTime(now);
        responses.add(response);

        when(weatherApiConfigMock.getUserAgent()).thenReturn("test");
        when(weatherApiConfigMock.getWeatherUrl()).thenReturn("http://example.com");
        when(restTemplateMock.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(ResponseEntity.ok("test"));
        when(objectMapperMock.readTree(anyString())).thenReturn(null);
        JavaType type = objectMapperMock.getTypeFactory().constructCollectionType(List.class, GetTemperatureOutResponse.class);
        when(objectMapperMock.readValue(anyString(), type)).thenReturn(responses);

        when(conversionServiceMock.convert(response, Temperature.class)).thenReturn(temperature());

        Temperature temperature = getTemperatureFromExtApiAdapter.getTemperatureFromExtApi(latitude, longitude);

        verify(restTemplateMock, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
        verify(objectMapperMock, times(1)).readTree(anyString());
    //    verify(objectMapperMock, times(1)).readValue(anyString(), any());
        verify(conversionServiceMock, times(1)).convert(any(), any());
        assertEquals(Temperature.class, temperature.getClass());
    }


        private Temperature temperature() {
        return Temperature.builder()
                .temperature(2.3)
                .temperatureAt(LocalDateTime.parse("2023-03-24T17:00:00"))
                .build();
    }

    private GetTemperatureOutResponse getTemperatureOutResponse() {
        return GetTemperatureOutResponse.builder()
                .airTemperature(2.3)
                .time(LocalDateTime.parse("2023-03-24T17:00:00"))
                .build();
    }
}