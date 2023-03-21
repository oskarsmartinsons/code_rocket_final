package com.meawallet.smartrequest.in.controller;

import com.meawallet.smartrequest.core.port.in.GetLocationUseCase;
import com.meawallet.smartrequest.core.port.in.SaveLocationUseCase;
import com.meawallet.smartrequest.domain.Location;
import com.meawallet.smartrequest.repository.entity.LocationEntity;
import com.meawallet.smartrequest.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@Slf4j
@RestController
@AllArgsConstructor
public class LocationController {
    private final GetLocationUseCase getLocationUseCase;
    private final SaveLocationUseCase saveLocationUseCase;

    private final LocationRepository locationRepository;

    @PostMapping(value = "/locations")
    public void saveLocation(@RequestBody Location entity) {
        log.debug("Received save request for LOCATION: {}", entity);
        var ent = entity.toBuilder()
                  .latitude(1.3)
                  .longitude(2.1)
                  .build();
        log.debug("Modified entity save request for LOCATION: {}", ent);

        // locationRepository.save(ent);

        saveLocationUseCase.saveLocation(ent);
    }

    @PostMapping(value = "/location")
    public void save(@RequestBody LocationEntity entity) {
        log.debug("Received save request for LOCATION: {}", entity);
        locationRepository.save(entity);
    }

     @GetMapping(value = "/locations/{id}")
     public Location getLocationById(@PathVariable Integer id) {
         log.debug("Received find LOCATION by id request: {}", id);

         return getLocationUseCase.getLocationById(id);
     }


      @GetMapping(value = "/test")
      public String test() {
        log.debug("Test request received");
        return "Test";
     }

/*    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        return e.getMessage();
    }*/
}
