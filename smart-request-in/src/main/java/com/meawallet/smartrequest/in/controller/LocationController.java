package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.core.port.in.SaveTemperatureUseCase;
import com.meawallet.smartrequest.core.service.GetTemperatureExternalService;
import com.meawallet.smartrequest.core.service.UpsertTemperatureForLocationService;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import com.meawallet.smartrequest.repository.adapter.GetLocationByIdAdapter;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class LocationController {
    private final GetLocationUseCase getLocationUseCase;
    private final SaveLocationUseCase saveLocationUseCase;
    private final SaveTemperatureUseCase saveTemperatureUseCase;
    private final UpsertTemperatureForLocationService upsertTemperatureForLocationService;
    private final GetTemperatureExternalService getTemperatureExternalService;

    @PostMapping(value = "/locations")
    public void saveLocation(@RequestBody Location location) {
        log.debug("Received save request for LOCATION: {}", location);
        saveLocationUseCase.saveLocation(location);
    }

    @GetMapping(value = "/locations/{id}")
    public Location getLocationById(@PathVariable Integer id) {
        log.debug("Received find LOCATION by id request: {}", id);
        return getLocationUseCase.getLocationById(id);
    }
    @GetMapping(value = "/weather")
    public Temperature getLocationTemperature(@RequestParam("id") Integer number, @RequestParam("id2") Double number2) {
       // log.debug("Received find LOCATION by id request: {}", id);
        var location = getLocationUseCase.getLocation(number);

        var loc = Location.builder()
                .latitude(1.0)
                .longitude(2.0)
                .build();

        // if valid return if not - upsert temperature and return

//        var temp = location.getTemperature() != null
//                ? location.getTemperature() : getTemperatureExternalService.getExtTemperature();

//        var savedTemp = saveTemperatureUseCase.saveTemperature(temp);
//
//        var locationWithTemperature = location.toBuilder()
//                .temperature(savedTemp)
//                .build();
//
//        saveLocationUseCase.saveLocation(locationWithTemperature);
        log.debug("This is my location in memory: {}", location);

        return  location != null
                ? location.getTemperature() : upsertTemperatureForLocationService.upsertTemperatureForLocation(loc);
    }

    @GetMapping(value = "/test")
    public String test() {log.debug("Test request received");
        return "Test";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        return e.getMessage();
    }
}
