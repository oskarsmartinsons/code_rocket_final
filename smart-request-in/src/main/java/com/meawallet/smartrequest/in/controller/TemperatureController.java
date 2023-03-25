package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationTemperatureUseCase;
//import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
//import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
//import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.in.dto.GetTemperatureInResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@AllArgsConstructor
public class TemperatureController {
  //  private final GetLocationUseCase getLocationUseCase;
   // private final SaveLocationUseCase saveLocationUseCase;
    private final GetLocationTemperatureUseCase getLocationTemperatureUseCase;

    private final ConversionService conversionService;

/*    @PostMapping(value = "/locations")
    public void saveLocation(@RequestBody Location location) {
        log.debug("Received save request for LOCATION: {}", location);
        saveLocationUseCase.saveLocation(location);
    }*/

//    @GetMapping(value = "/locations/{id}")
//    public Location getLocationById(@PathVariable Integer id) {
//        log.debug("Received find LOCATION by id request: {}", id);
//        return getLocationUseCase.getLocationById(id);
//    }
    @GetMapping(value = "/weather")
    public ResponseEntity<GetTemperatureInResponse> getLocationTemperatureByCoordinates (
            @Valid
            @NotNull(message = "Latitude cannot be null")
            @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90")
            @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90")
            @RequestParam("lat") Double latitude,

            @Valid
            @NotNull(message = "Longitude cannot be null")
            @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180")
            @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180")
            @RequestParam("lon") Double longitude)  {

        log.debug("Received GET request by coordinates: {} , {}", latitude, longitude);
        var temperature = getLocationTemperatureUseCase.getTemperatureByCoordinates(latitude, longitude);
        return ResponseEntity.ok(conversionService.convert(temperature, GetTemperatureInResponse.class));
    }

}
