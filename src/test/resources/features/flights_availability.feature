Feature: Flights Availability

  Scenario: To check flights availability based on given date
    Given I have data available in CSV file
    When I requested to verify flight availability based on date
    Then I should get all available flights on that date