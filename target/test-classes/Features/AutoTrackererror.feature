@Regression

Feature: To verify whether error scenarios are handled correctly

  Scenario: To verify correct respponse message for missing mandatory field in request
    Given request is built with valid locale and wa_key
    And request is appended with "" manufacturer
    When build-date endpoint is called
    Then Reponse should contain message ""
    When request is appended with "" main-type
    And build-date endpoint is called
    Then Reponse should contain message ""

  Scenario Outline: To verify no results present in the response for invalid data in request
    Given request is built with valid locale and wa_key
    And request is appended with "<manufacturer>" and "<main-types>"
    When build-date endpoint is called
    Then Response should contain empty list
    Examples:
      | manufacturer | main-types |
      | 425          | hujgkr     |
      | 675          | Magentis   |

  Scenario: To verify request to endpoints does not work without valid authorisation
    Given request is built with valid locale and manufacturer
    And request is appended with "invalid_key123" wa_key
    When main-types endpoint is called
    Then Response code should be "403"





  Scenario: Fetching build date with valid manufacturer and with invalid main type.
  Given


