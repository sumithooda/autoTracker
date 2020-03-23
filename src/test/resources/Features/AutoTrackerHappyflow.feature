@happyFlow
@regression

Feature: To Verify customer is able to select car type to receive an offer for selling a car

  @regression
  Scenario:  As a customer i should be able to select valid build-date in order to receive offer
    Given  customer is on select vehicle screen
    When request sent to system for valid "manufacturer"
    Then customer should be able to see  valid manufacturer list
    When customer selects valid manufacturer from drop down and request for "main-types"
    Then customer should be able to see valid main types list
    When customer selects valid main-type from drop down and request for "built-dates"
    Then customer should be able to see build date in drop down


  Scenario Outline:  As a customer i should be able to see valid build-date
    Given  customer is on select vehicle screen
    When request sent to system for valid "manufacturer"
    Then customer should be able to see  valid "<manufacturer>" list
    When customer selects valid manufacturer "<manufacturer>" from drop down and request for "main-types"
    Then customer should be able to see  valid "<main-type>" list
    When customer selects valid "<main-type>" from drop down and request for "built-dates" for "<manufacturer>"
    Then customer should be able to see "<build-date>" in drop down

    Examples:
      | manufacturer | main-type   | build-date |
      | 340          | Evolution   | 2010       |
      | 502          | Evora       | 2012       |
      | 071          | 800         | 1997       |
      | 730          | Discovery 5 | 2019       |
      | 425          | Magentis    | 2003       |