package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.IntegrationTestBase;
import com.virgin.flights.flightsavailability.model.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

public class FlightDataInformationServiceImplIT extends IntegrationTestBase {

    @Autowired
    private FlightInformationService flightInformationService;

    @Test
    public void shouldReturnDataForAvailableDate() throws ParseException {
        List<Flight> availableFlightData = flightInformationService.getAvailableFlights("2020-01-01");
        Assert.assertEquals("09:00", availableFlightData.get(0).getDepartureTime());
        Assert.assertEquals("Antigua", availableFlightData.get(0).getDestination());
        Assert.assertEquals("ANU", availableFlightData.get(0).getDestinationAirportIATA());
        Assert.assertEquals("VS033", availableFlightData.get(0).getFlightNo());
    }
}