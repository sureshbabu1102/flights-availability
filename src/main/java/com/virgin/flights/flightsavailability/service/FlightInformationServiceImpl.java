package com.virgin.flights.flightsavailability.service;

import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import com.virgin.flights.flightsavailability.exception.UnableToProcessRequestException;
import com.virgin.flights.flightsavailability.model.Flight;
import com.virgin.flights.flightsavailability.model.FlightData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FlightInformationServiceImpl implements FlightInformationService{

    private CSVDataLoader csvDataLoader;

    public FlightInformationServiceImpl(CSVDataLoader csvDataLoader) {
        this.csvDataLoader = csvDataLoader;
    }

    @Override
    public List<Flight> getAvailableFlights(String date) {
        try {
            List<Flight> availableFlights = new ArrayList<>();
            String dayOfWeek = getDayOfWeek(date);
            List<FlightData> flightsData = csvDataLoader.loadFlightInformation();
            Predicate<FlightData> predicate = getPredicate(dayOfWeek);
            List<FlightData> availableFlightsData = flightsData.stream().filter(predicate).collect(Collectors.toList());
            availableFlightsData.iterator().forEachRemaining(item -> {
                Flight flight = new Flight();
                BeanUtils.copyProperties(item, flight);
                availableFlights.add(flight);
            });
            return availableFlights;
        } catch (UnableToProcessRequestException e){
            throw new UnableToProcessRequestException("Unable to process request", e);
        } catch (Exception e){
            throw new ApplicationDataLoadException("Unable to Load Data", e);
        }
    }

    private Predicate<FlightData> getPredicate(String dayOfWeek){
        Predicate<FlightData> dayOfWeekPredicate = null;
        if("SUNDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getSunday());
        } else if("MONDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getMonday());
        } else if("TUESDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getTuesday());
        } else if("WEDNESDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getWednesday());
        } else if("THURSDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getThursday());
        } else if("FRIDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getFriday());
        } else if("SATURDAY".equals(dayOfWeek)){
            dayOfWeekPredicate = d -> "x".equals(d.getSaturday());
        }
        return dayOfWeekPredicate;
    }

    private String getDayOfWeek(String date) {
        try {
            return LocalDate.parse(date).getDayOfWeek().name();
        } catch (Exception e){
            throw new UnableToProcessRequestException("Unable to Process Request", e);
        }
    }

}
