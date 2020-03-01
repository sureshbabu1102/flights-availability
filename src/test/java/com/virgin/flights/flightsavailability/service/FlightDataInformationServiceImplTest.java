package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.IntegrationTestBase;
import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import com.virgin.flights.flightsavailability.exception.UnableToProcessRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightDataInformationServiceImplTest extends IntegrationTestBase {

    @Mock
    private CSVDataLoader csvDataLoader;

    @InjectMocks
    private FlightInformationService flightInformationService = new FlightInformationServiceImpl(csvDataLoader);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ApplicationDataLoadException.class)
    public void shouldReturnDataLoadExceptionWhenFailedToLoadData() {
        when(csvDataLoader.loadFlightInformation()).thenThrow(RuntimeException.class);
        flightInformationService.getAvailableFlights("2020-01-01");
    }

    @Test(expected = UnableToProcessRequestException.class)
    public void shouldThrowUnableToProcessRequestExceptionForBadData() {
        flightInformationService.getAvailableFlights("01-02");
    }
}