package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVDataLoaderTest {

    @InjectMocks
    private CSVDataLoader csvDataLoader = new CSVDataLoader();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ApplicationDataLoadException.class)
    public void shouldReturnDataLoadExceptionWhenFailedToLoadData(){
        csvDataLoader.loadFlightInformation();
    }



}