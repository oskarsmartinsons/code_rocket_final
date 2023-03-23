package com.meawallet.smartrequest.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.config.WeatherApiConfig;
import com.meawallet.smartrequest.out.converter.GetTemperatureOutResponseToTemperatureConverter;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import com.meawallet.smartrequest.out.dto.TemperatureDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Library;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class GetTemperatureFromExtApiAdapter implements GetTemperatureFromExtApiPort {
    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;
    private final ConversionService conversionService;
    @Override
    public Temperature getTemperatureFromExtApi(Double latitude, Double longitude) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "SmartRequest1.0");
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            String urlWeatherApi = weatherApiConfig.getWeatherUrl() + "?lat=" + latitude + "&lon=" + longitude;

            String jsonResponse = restTemplate.exchange(urlWeatherApi, HttpMethod.GET, entity, String.class).getBody();

          //  var getTemperatureOutResponse = returnTemperatureForCurrentHour(jsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            List<TemperatureDto> temps = mapper.readValue(
                    jsonResponse,
                    mapper.getTypeFactory().constructCollectionType(List.class, TemperatureDto.class)
            );

            System.out.println(temps);

         //   return conversionService.convert(getTemperatureOutResponse, Temperature.class);
            return null;

        } catch (RestClientException restClientException) {
            log.error("Received error from guard API: {}", restClientException.getMessage());
            throw new RuntimeException(restClientException);

        } catch (JsonProcessingException e) {
            log.error("Received error when parsing Json response: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private GetTemperatureOutResponse returnTemperatureForCurrentHour(String jsonResponse) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode filteredJsonNodes = objectMapper.readTree(jsonResponse).at("/properties/timeseries");

        var getTemperatureOutResponse = new GetTemperatureOutResponse();

        for (JsonNode node : filteredJsonNodes) {
            String time = node.get("time").asText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            LocalDateTime timeInResponse = LocalDateTime.parse(time, formatter);

            if(timeInResponse.equals(getCurrentTimeInRoundHours())) {

                double airTemperature = node.at("/data/instant/details/air_temperature").asDouble();

                getTemperatureOutResponse = getTemperatureOutResponse.toBuilder()
                        .airTemperature(airTemperature)
                        .time(timeInResponse)
                        .build();

                log.debug("Found air temperature in json response {}:", getTemperatureOutResponse);
            }
        }
        return getTemperatureOutResponse;
    }

    private LocalDateTime getCurrentTimeInRoundHours() {
        return LocalDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .withMinute(0)
                .withSecond(0);
    }
}
