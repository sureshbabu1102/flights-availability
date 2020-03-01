package com.virgin.flights.flightsavailability.controller;

import com.virgin.flights.flightsavailability.IntegrationTestBase;
import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import com.virgin.flights.flightsavailability.service.FlightInformationService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightDataInformationControllerIT extends IntegrationTestBase {

    @Autowired
    private FlightInformationController flightInformationController;

    @SpyBean
    private FlightInformationService flightInformationService;

    @Test
    public void shouldReturnOKStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/virgin/v1/flights/2020-01-01"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentWhenDataNotAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/virgin/v1/flights/2020-01-02"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnBadRequestWhenRequestedWithIncorrectDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/virgin/v1/flights/2020-01"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnServiceErrorCodeWhenUnableToProcess() throws Exception {
        Mockito.doThrow(ApplicationDataLoadException.class).when(flightInformationService).getAvailableFlights("2020-01-03");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/virgin/v1/flights/2020-01-03"))
                .andExpect(status().is5xxServerError());
    }

}