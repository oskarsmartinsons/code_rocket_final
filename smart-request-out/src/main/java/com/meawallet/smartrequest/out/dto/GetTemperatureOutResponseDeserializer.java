package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetTemperatureOutResponseDeserializer extends JsonDeserializer<GetTemperatureOutResponse> {
    @Override
    public GetTemperatureOutResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        JsonNode node = parser.getCodec().readTree(parser);
        LocalDateTime time = LocalDateTime.parse(node.get("time").asText(), formatter);
        double airTemperature = node.at("/data/instant/details/air_temperature").asDouble();

        return GetTemperatureOutResponse.builder()
                .time(time)
                .airTemperature(airTemperature)
                .build();
    }
}

