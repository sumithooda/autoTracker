package stepDef;

import com.jayway.jsonpath.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import integration.CommonActions;
import integration.StateContainer;
import org.junit.Assert;

import java.util.LinkedHashMap;

public class validateReq extends genericStep{


    @Given("^request is built with valid locale and wa_key$")
    public void requestIsBuiltWithValidLocaleAndWa_key() {
    }

    @When("^request is appended with \"([^\"]*)\" value \"([^\"]*)\" and build date endpoint is called$")
    public void requestIsAppendedWithValueAndBuildDateEndpointIsCalled(String param, String value) {
        stateContainer.response = commonActions.iComposeRestPointforBuildDate(param, value);
    }

    @Then("^Reponse should contain message \"([^\"]*)\"$")
    public void reponseShouldContainMessage(String errorMsg) {
        boolean errorPresent = commonActions.bodyContainsString(stateContainer.response.asString(), errorMsg);
        stateContainer.response.then().statusCode(400);
        Assert.assertTrue(errorPresent);
    }

    @When("^request is appended with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" endpoint is called$")
    public void requestIsAppendedWithAndAndEndpointIsCalled(String manufacturer, String mainTypes, String endPoint) {
        stateContainer.response = commonActions.iComposeRestPointwithValidKey(endPoint, manufacturer, endPoint);
    }

    @Then("^Response should contain empty list$")
    public void responseShouldContainEmptyList() {
        commonActions.responseBodyisnull(stateContainer.response.getBody().asString(), "wkda");
        stateContainer.response.then().statusCode(200);
    }


    @Given("^request is built with valid locale$")
    public void requestIsBuiltWithValidLocale() {

    }

    @When("^request is appended with wa_key value \"([^\"]*)\" and getManufacturer endpoint is called$")
    public void requestIsAppendedWithWa_keyValueAndGetManufacturerEndpointIsCalled(String key) {
        stateContainer.response =  commonActions.iComposeRestPointforBuildDatewithInvalidKey(key);
    }

    @Then("^Response code should be \"([^\"]*)\"$")
    public void responseCodeShouldBe(String statusCode) {
        stateContainer.response.then().statusCode(Integer.parseInt(statusCode));
    }

    @Then("^response body format should be correct \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
    public void responseBodyFormatShouldBeCorrect(String page, String pageSize, String totalPageCount, String wkda)  {
       }

    @And("^\"([^\"]*)\" response body should have \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
    public void responseBodyShouldHave( String endpoint, String page, String pageSize, String totalPageCount, String wkda) {
        System.out.println(stateContainer.response.getBody().asString());
        Assert.assertTrue(endpoint+" does not have page in body",stateContainer.response.getBody().asString().contains(page));
        Assert.assertTrue(endpoint+" does not have pageSize in body",stateContainer.response.getBody().asString().contains(pageSize));
        Assert.assertTrue(endpoint+" does not have total page count in body",stateContainer.response.getBody().asString().contains(totalPageCount));
        Assert.assertTrue(endpoint+" does not have wkda in body",stateContainer.response.getBody().asString().contains(wkda));

    }
}
