@Regression
@HappyFlow

Feature : To Verify customer is able to select car type to receive an offer for selling a car

  #select values at run time (1st call response --> 2nd call)
  Scenario :  As a customer i should be able to select valid build-date in order to receive offer
    Given  customer is on select vehicle screen
    When request sent to system with valid <locale>
    Then customer should be able to see  valid manufacturer list
    When customer  selects valid <manufacturer> from drop down
    Then customer should be able to see valid main types list
    When customer selects valid <main-type> from drop down
    Then customer should be able to see build date in drop down

 #Verification with static values
  Scenario Outline :  As a customer i should be able to see valid build-date
    Given  customer is on select vehicle screen
    When request sent to system with valid "<locale>"
    Then customer should be able to see  valid manufacturer list
    When customer  selects valid "<manufacturer>" from drop down
    Then customer should be able to see valid main types list
    When customer selects valid "<main-type>" from drop down
    Then customer should be able to see "<build-date>" in drop down
    Examples:
      | locale | manufacturer | main-type   | build-date |
      | EN     | 340          | Evolution   | 2010       |
      | DE     | 502          | Evora       | 2012       |
      | ar-KW  | 071          | 800         | 1997       |
      | ar-LB  | 730          | Discovery 5 | 2019       |
      | ar-LY  | 425          | Magentis    | 2003       |

    Scenario: