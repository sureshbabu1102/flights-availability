package com.virgin.flights.flightsavailability.acceptance.glue;

import com.virgin.flights.flightsavailability.FlightsAvailabilityApplication;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@ActiveProfiles("acceptance")
@SpringBootTest(classes = FlightsAvailabilityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FlightsInformationStepDefinitions {

    JsonPath jsonResponse = null;

    @LocalServerPort
    int SERVER_PORT_NUMBER;

    @Before
    public void setUp(){
        RestAssured.port = SERVER_PORT_NUMBER;
    }

    @Given("I have data available in CSV file")
    public void i_have_data_available_in_CSV_file() {
        //we have created mock-flights.csv file in test folder and provided values
    }

    @When("I requested to verify flight availability based on date")
    public void i_requested_to_verify_flight_availability_based_on_date() {
        jsonResponse = given()
                .when()
                .get("/virgin/v1/flights/2020-01-01").thenReturn().jsonPath();
    }

    @Then("I should get all available flights on that date")
    public void i_should_get_all_available_flights_on_that_date() {
        Assert.assertThat("I should get valid Flight No", "VS033", Is.is(jsonResponse.getList("flightNo").get(0)));
        Assert.assertThat("I should get valid Departure Time", "09:00", Is.is(jsonResponse.getList("departureTime").get(0)));
        Assert.assertThat("I should get valid Destination", "Antigua", Is.is(jsonResponse.getList("destination").get(0)));
        Assert.assertThat("I should get valid Destination Airport IATA", "ANU", Is.is(jsonResponse.getList("destinationAirportIATA").get(0)));
    }

}
