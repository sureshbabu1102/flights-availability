package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.IntegrationTestBase;
import com.virgin.flights.flightsavailability.model.FlightData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CSVDataLoaderIT extends IntegrationTestBase {

    @Autowired
    private CSVDataLoader csvDataLoader;

    @Test
    public void shouldLoadDataFromCSVFile(){
        List<FlightData> flightData = csvDataLoader.loadFlightInformation();
        Assert.assertEquals("09:00", flightData.get(0).getDepartureTime());
        Assert.assertEquals("Antigua", flightData.get(0).getDestination());
        Assert.assertEquals("ANU", flightData.get(0).getDestinationAirportIATA());
        Assert.assertEquals("VS033", flightData.get(0).getFlightNo());
        Assert.assertEquals("x", flightData.get(0).getWednesday());
    }

}