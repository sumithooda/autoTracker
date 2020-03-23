package stepDef;

import com.jayway.restassured.path.json.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import integration.CommonActions;
import integration.StateContainer;
import jdk.nashorn.internal.parser.JSONParser;

public class verifyGetoffer extends genericStep{


    @Given("^customer is on select vehicle screen$")
    public void customerIsOnSelectVehicleScreen() {
        //this method is empty step definiton to make feature file more meaningfull.
    }


    @When("^request sent to system for valid \"([^\"]*)\"$")
    public void requestSentToSystemWithValid(String endpoint) {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endpoint, null, null);
        stateContainer.response.then().statusCode(200);
    }

    @Then("^customer should be able to see  valid manufacturer list$")
    public void customerShouldBeAbleToSeeValidManufacturerList() {
        commonActions.responseBodynotnull(stateContainer.response.getBody().asString(), "wkda");
        //To do generate index from random generator
        stateContainer.manufacturer = commonActions.valuefromresponsebyindex(stateContainer.response.getBody().asString(), "wkda", 1);
    }

    @When("^customer selects valid manufacturer from drop down and request for \"([^\"]*)\"$")
    public void customerSelectsValidManufacturerFromDropDownAndRequestFor(String endpoint) throws Throwable {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endpoint, StateContainer.manufacturer, null);
        stateContainer.response.then().statusCode(200);
    }

    @Then("^customer should be able to see valid main types list$")
    public void customerShouldBeAbleToSeeValidMainTypesList() {
        commonActions.responseBodynotnull(stateContainer.response.getBody().asString(), "wkda");
        //To do generate index from random generator
        stateContainer.mainTypes = commonActions.valuefromresponsebyindex(stateContainer.response.getBody().asString(), "wkda", 1);
    }


    @When("^customer selects valid main-type from drop down and request for \"([^\"]*)\"$")
    public void customerSelectsValidMainTypeFromDropDownAndRequestFor(String endpoint) throws Throwable {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endpoint, StateContainer.manufacturer, StateContainer.mainTypes);
        stateContainer.response.then().statusCode(200);
    }

    @Then("^customer should be able to see build date in drop down$")
    public void customerShouldBeAbleToSeeBuildDateInDropDown() {
        commonActions.responseBodynotnull(stateContainer.response.getBody().asString(), "wkda");
    }

    @Then("^customer should be able to see  valid \"([^\"]*)\" list$")
    public void customerShouldBeAbleToSeeValidList(String Key) throws Throwable {
        commonActions.valuefromresponsebyKey(stateContainer.response.getBody().asString(), "wkda", Key);
    }

    @When("^customer selects valid manufacturer \"([^\"]*)\" from drop down and request for \"([^\"]*)\"$")
    public void customerSelectsValidFromDropDownAndRequestFor(String manuFacvalue, String endpoint) throws Throwable {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endpoint, manuFacvalue, null);
        stateContainer.response.then().statusCode(200);
    }


    @When("^customer selects valid \"([^\"]*)\" from drop down and request for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void customerSelectsValidFromDropDownAndRequestForFor(String mainTypeValue, String endPoint, String manuFacValue) throws Throwable {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endPoint, manuFacValue, mainTypeValue);
        stateContainer.response.then().statusCode(200);
    }

    @Then("^customer should be able to see \"([^\"]*)\" in drop down$")
    public void customerShouldBeAbleToSeeInDropDown(String builtDate) throws Throwable {
        commonActions.valuefromresponsebyKey(stateContainer.response.getBody().asString(), "wkda", builtDate);
    }
}
