package com.meawallet.smartrequest.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.config.WeatherApiConfig;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class GetTemperatureFromExtApiAdapter implements GetTemperatureFromExtApiPort {
    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;
    private final ConversionService conversionService;
    private final ObjectMapper objectMapper;
    private final Clock clock;

    @Override
    public Temperature getTemperatureFromExtApi(Double latitude, Double longitude) {
        try {
            var listOfGetTemperatureOutResponse = getResponseFromWeatherApi(latitude, longitude);
            var getTemperatureOutResponse =  listOfGetTemperatureOutResponse.stream()
                    .filter(response->response.getTime().equals(getCurrentTimeInRoundHours(clock)))
                    .findFirst()
                    .orElseThrow(()-> new RestClientException("Temperature not Found in external API response."));
            log.debug("Found air temperature in external API response {}:", getTemperatureOutResponse);

            return conversionService.convert(getTemperatureOutResponse, Temperature.class);

        } catch (RestClientException restClientException) {
            log.error("Received error from weather API: {}", restClientException.getMessage());
            throw new RuntimeException(restClientException);

        } catch (IOException ioException) {
            log.error("Received error when parsed weather API response: {}", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }

    private List<GetTemperatureOutResponse> getResponseFromWeatherApi(Double latitude, Double longitude) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", weatherApiConfig.getUserAgent());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        var urlWeatherApi = weatherApiConfig.getWeatherUrl() + "?lat="+ latitude + "&lon=" + longitude;

        var fullResponse = restTemplate.exchange(urlWeatherApi, HttpMethod.GET, entity, String.class).getBody();

        JsonNode filteredResponse = objectMapper.readTree(fullResponse).at("/properties/timeseries");

        return objectMapper.readValue(
                filteredResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, GetTemperatureOutResponse.class));
    }

    private LocalDateTime getCurrentTimeInRoundHours(Clock clock) {
        return LocalDateTime.now(clock)
                .truncatedTo(ChronoUnit.MINUTES)
                .withMinute(0)
                .withSecond(0);
    }
}
