package com.meawallet.smartrequest.itest;

import com.meawallet.smartrequest.app.SmartRequestApp;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import com.meawallet.smartrequest.repository.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(classes= SmartRequestApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private LocationRepository locationRepository;



    @org.junit.jupiter.api.Test
    void saveTemperature() {
    }

    @org.junit.jupiter.api.Test
    public void should_find_no_tutorials_if_repository_is_empty() {
//        var temperature = temperatureRepository.findById(6);
//        var location = locationRepository.fin
//
//
//        assertThat(tutorials).isEmpty();
    }
}
