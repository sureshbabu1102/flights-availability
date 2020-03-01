package com.virgin.flights.flightsavailability.controller;

import com.virgin.flights.flightsavailability.model.Flight;
import com.virgin.flights.flightsavailability.service.FlightInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightInformationController {

    private FlightInformationService flightInformationService;

    public FlightInformationController(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @GetMapping("/virgin/v1/flights/{date}")
    public ResponseEntity<List<Flight>> getAvailableFlights(@PathVariable(name = "date") String date){
        List<Flight> availableFlights = flightInformationService.getAvailableFlights(date);
        if(CollectionUtils.isEmpty(availableFlights)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(availableFlights);
    }
}
