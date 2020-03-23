@regression

Feature: To verify whether error scenarios are handled correctly

  Scenario: To verify correct respponse message for missing mandatory field in request
    Given request is built with valid locale and wa_key
    When request is appended with "manufacturer" value "487" and build date endpoint is called
    Then Reponse should contain message "Required String parameter 'main-type' is not present"
    When request is appended with "main-type" value "NSX" and build date endpoint is called
    Then Reponse should contain message "Required String parameter 'manufacturer' is not present"

  Scenario Outline: To verify no results present in the response for invalid data in request
    Given request is built with valid locale and wa_key
    When request is appended with "<manufacturer>" and "<main-types>" and "built-dates" endpoint is called
    Then Response should contain empty list
    Examples:
      | manufacturer | main-types |
      | 425          | hujgkr     |
      | 675          | Magentis   |

  Scenario: To verify request to endpoints does not work without valid authorisation
    Given request is built with valid locale
    When request is appended with wa_key value "invalid_key123" and getManufacturer endpoint is called
    Then Response code should be "403"


  Scenario Outline:  To verify response body contains all expected attributes
    Given  customer is on select vehicle screen
    When request sent to system for valid "manufacturer"
    And "manufacturer" response body should have "page" "pageSize" "totalPageCount"  "wkda"
    When customer selects valid manufacturer "<manufacturer>" from drop down and request for "main-types"
    Then customer should be able to see  valid "<main-type>" list
    And "main-type" response body should have "page" "pageSize" "totalPageCount"  "wkda"
    When customer selects valid "<main-type>" from drop down and request for "built-dates" for "<manufacturer>"
    And "built-dates" response body should have "page" "pageSize" "totalPageCount"  "wkda"

    Examples:
      | manufacturer | main-type   |
      | 340          | Evolution   |







