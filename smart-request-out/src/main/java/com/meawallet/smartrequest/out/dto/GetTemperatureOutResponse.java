package com.meawallet.smartrequest.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetTemperatureOutResponse (
//    @JsonProperty(value = "id")
//    String externalId,
/*    @JsonProperty(value = "temperature")
    String temperature,*/
    @JsonProperty(value = "time")
    String time


){
}

/*public class WeatherData {
    private List<TimeSeries> timeseries;

    // getters and setters
}

public class TimeSeries {
    private String time;
    private Data data;

    // getters and setters
}*/

/*public class Data {
    private Instant instant;

    // getters and setters
}

public class Instant {
    private Details details;

    // getters and setters
}

public class Details {
    @JsonProperty("air_temperature")
    private double airTemperature;

    // getters and setters
}*/

