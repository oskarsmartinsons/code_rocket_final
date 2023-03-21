package com.meawallet.smartrequest.repository.controllers;

import com.meawallet.smartrequest.repository.entity.LocationEntity;
//import com.meawallet.smartrequest.repository.services.GetLocationUseCase;
//import com.meawallet.smartrequest.repository.services.LocationService;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
public class LocationController {
 //   private final GetLocationUseCase getLocationUseCase;
      private final LocationRepository locationRepository;

//    @GetMapping(value = "/locations/{id}")
//    public LocationEntity getLocationById(@PathVariable Integer id) {
//        return getLocationUseCase.getLocationById(id);
//    }


      @PostMapping(value = "/locations")
      public void saveLocation(@RequestBody LocationEntity entity) {
          locationRepository.save(entity);
      }

      @GetMapping(value = "/locations/{id}")
      public LocationEntity getLocationById(@PathVariable Integer id) {
          log.debug("Received find LOCATION by id request: {}", id);
          return locationRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No location with this id was found."));
      }


      @GetMapping(value = "/test")
      public String test() {
        log.debug("Test request received");
        return "Test";
     }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        return e.getMessage();
    }
}
