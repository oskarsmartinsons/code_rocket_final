package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
//import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.domain.Temperature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class LocationController {
    private final GetLocationUseCase getLocationUseCase;
   // private final SaveLocationUseCase saveLocationUseCase;
    private final GetLocationTemperatureUseCase getLocationTemperatureUseCase;

/*    @PostMapping(value = "/locations")
    public void saveLocation(@RequestBody Location location) {
        log.debug("Received save request for LOCATION: {}", location);
        saveLocationUseCase.saveLocation(location);
    }*/

    @GetMapping(value = "/locations/{id}")
    public Location getLocationById(@PathVariable Integer id) {
        log.debug("Received find LOCATION by id request: {}", id);
        return getLocationUseCase.getLocationById(id);
    }
    @GetMapping(value = "/weather")
    public Temperature getLocationTemperature(@RequestParam("lat") Double latitude, @RequestParam("lon") Double longitude) {
        log.debug("Received GET Temp by coordinates: {} , {}", latitude, longitude);

        return getLocationTemperatureUseCase.getTemperatureByCoordinates(latitude, longitude);
    }

    @ExceptionHandler
  //  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {

        return e.getMessage() + e.getCause();
    }
}
