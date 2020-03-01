package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.model.Flight;

import java.util.List;

public interface FlightInformationService {

    List<Flight> getAvailableFlights(String s);
}
