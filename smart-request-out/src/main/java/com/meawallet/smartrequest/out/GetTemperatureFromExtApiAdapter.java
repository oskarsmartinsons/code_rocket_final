package com.meawallet.smartrequest.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meawallet.smartrequest.core.port.out.GetTemperatureFromExtApiPort;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.out.config.WeatherApiConfig;
import com.meawallet.smartrequest.out.converter.GetTemperatureOutResponseToTemperatureConverter;
import com.meawallet.smartrequest.out.dto.GetTemperatureOutResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Library;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class GetTemperatureFromExtApiAdapter implements GetTemperatureFromExtApiPort {
    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;
    private final GetTemperatureOutResponseToTemperatureConverter getTemperatureOutResponseToTemperatureConverter;

    @Override
    public Temperature getTemperatureFromExtApi(Double latitude, Double longitude) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "SmartRequest1.0"); // Set a unique identifier as the User-Agent header

            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            ResponseEntity<String> response = restTemplate.exchange("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=" + latitude + "&lon=" + longitude, HttpMethod.GET, entity, String.class);

/*            var response = restTemplate.getForEntity(weatherApiConfig.getWeatherUrl(), GetTemperatureOutResponse.class)
                    .getBody();*/

            String jsonResponse = response.getBody();

            log.debug("json {}", jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<GetTemperatureOutResponse>> typeReference = new TypeReference<>() {};
            List<GetTemperatureOutResponse> tempData = objectMapper.readValue(jsonResponse, typeReference);

            log.debug("tempData {}", tempData);

          //  log.debug("Received response: {}", response);

         //   return getTemperatureOutResponseToTemperatureConverter.convert(response);
         //   System.out.println(response.getBody());

            return null;

        } catch (RestClientException restClientException) {
            log.error("Received error from guard API: {}", restClientException.getMessage());
            throw new RuntimeException(restClientException);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
