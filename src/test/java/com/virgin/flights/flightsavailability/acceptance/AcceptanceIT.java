package com.virgin.flights.flightsavailability.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.virgin.flights.flightsavailability.acceptance.glue"}
)
public class AcceptanceIT {
}
