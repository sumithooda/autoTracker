package integration;
import com.jayway.restassured.RestAssured;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.junit.Assert;

public class CommonActions {

    public static Response performGet(String url){
        System.out.println("\n Performing GET to : "+ url);
        return RestAssured.given().get();
    }

    public static void jsonPathStringInArrayEquals(String responseBody, String jsonPath, String expected){
        JSONArray queriedJson = JsonPath.read(responseBody,jsonPath);
        Assert.assertEquals(expected,queriedJson.get(0));
    }
    public static void jsonPathInArrayEquals(String responseBody, String jsonPath, Boolean expected){
        JSONArray queriedJson = JsonPath.read(responseBody,jsonPath);
        Assert.assertEquals(expected, queriedJson.get(0));
    }

    public static void jsonPathStringInJsonStringEquals(String responseBody, String jsonPath, Boolean expected){
        JSONArray queriedJson = JsonPath.read(responseBody,jsonPath);
        Assert.assertEquals(expected, queriedJson);
    }

    public static void jsonPathIntegerInJsonStringEquals(String responseBody, String jsonPath, String expected){
        JSONArray queriedJson = JsonPath.read(responseBody,jsonPath);
        Assert.assertEquals(expected,String.valueOf(queriedJson));
    }
    public static void jsonPathBooleanInJsonStringEquals(String responseBody, String jsonPath, String expected){
        JSONArray queriedJson = JsonPath.read(responseBody,jsonPath);
        Assert.assertEquals(expected,String.valueOf(queriedJson));
    }


}
