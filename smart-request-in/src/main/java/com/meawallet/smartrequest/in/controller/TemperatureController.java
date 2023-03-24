package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
//import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.in.dto.GetTemperatureInResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class TemperatureController {
    private final GetLocationUseCase getLocationUseCase;
   // private final SaveLocationUseCase saveLocationUseCase;
    private final GetLocationTemperatureUseCase getLocationTemperatureUseCase;

    private final ConversionService conversionService;

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
    public ResponseEntity<GetTemperatureInResponse> getTemperatureByCoordinates(@RequestParam("lat") Double latitude, @RequestParam("lon") Double longitude) {
        log.debug("Received GET request by coordinates: {} , {}", latitude, longitude);
        var temperature = getLocationTemperatureUseCase.getTemperatureByCoordinates(latitude, longitude);
        return ResponseEntity.ok(conversionService.convert(temperature, GetTemperatureInResponse.class));
    }

}
