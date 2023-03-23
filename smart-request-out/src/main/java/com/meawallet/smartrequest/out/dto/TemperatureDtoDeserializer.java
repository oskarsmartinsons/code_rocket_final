package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemperatureDtoDeserializer extends JsonDeserializer<TemperatureDto> {
    @Override
    public TemperatureDto deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = parser.getCodec().readTree(parser);
        String time = node.get("properties/timeseries/time").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime timeInResponse = LocalDateTime.parse(time, formatter);

        return new TemperatureDto(timeInResponse);
    }
}

