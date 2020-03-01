package com.virgin.flights.flightsavailability.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.virgin.flights.flightsavailability.exception.ApplicationDataLoadException;
import com.virgin.flights.flightsavailability.model.FlightData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class CSVDataLoader {

    @Value("${virgin.flights.information.file}")
    private String fileName;

    public List<FlightData> loadFlightInformation() {
        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<FlightData> readValues =
                    mapper.reader(FlightData.class).with(csvSchema).readValues(file);
            List<FlightData> flightData = readValues.readAll();
            return flightData;
        } catch (Exception e) {
            throw new ApplicationDataLoadException("Unable to Load Data from CSV File", e);
        }
    }
}
