package com.virgin.flights.flightsavailability.advice;

import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import com.virgin.flights.flightsavailability.exception.UnableToProcessRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightInformationControllerAdvice {

    @ExceptionHandler(UnableToProcessRequestException.class)
    public ResponseEntity handleBadException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ApplicationDataLoadException.class)
    public ResponseEntity handleServiceException() {
        return ResponseEntity.status(503).build();
    }
}
