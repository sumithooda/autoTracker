package integration;

import com.jayway.restassured.RestAssured;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import utility.config;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.jayway.restassured.http.ContentType.JSON;

public class CommonActions {

    private static String appUrl = new config().getProperrty("appurl");
    private static String wa_key = new config().getProperrty("wa_key");
    private static String context = new config().getProperrty("context");
    private static String locale = new config().getProperrty("locale");

    public Response iComposeRestPointwithValidKey(String endPoint, String valueManufac, String valueMain) {
        String url = null;
        if (endPoint.equalsIgnoreCase("manufacturer")) {
            url = appUrl + "/" + context + "/" + endPoint + "?wa_key=" + wa_key + "&locale=" + locale;
        } else if (endPoint.equalsIgnoreCase("main-types")) {
            url = appUrl + "/" + context + "/" + endPoint + "?wa_key=" + wa_key + "&locale=" + locale + "&manufacturer=" + valueManufac;
        } else {
            url = appUrl + "/" + context + "/" + endPoint + "?wa_key=" + wa_key + "&locale=" + locale + "&manufacturer=" + valueManufac + "&main-type=" + valueMain;

        }
        return performGet(url);
    }

    public Response iComposeRestPointforBuildDate(String paramName, String pramValue){
        String url = appUrl + "/" + context + "/" + "built-dates" + "?wa_key=" + wa_key + "&locale=" + locale + "&" + paramName+ "=" + pramValue;
        return performGet(url);
    }

    public static Response performGet(String url) {
        System.out.println("\n Performing GET to : " + url);
        return RestAssured.given().get(url);
    }

    public static void jsonPathStringInArrayEquals(String responseBody, String jsonPath, String expected) {
        JSONArray queriedJson = JsonPath.read(responseBody, jsonPath);
        Assert.assertEquals(expected, queriedJson.get(0));
    }

    public static void jsonPathInArrayEquals(String responseBody, String jsonPath, Boolean expected) {
        JSONArray queriedJson = JsonPath.read(responseBody, jsonPath);
        Assert.assertEquals(expected, queriedJson.get(0));
    }

    public static void jsonPathStringInJsonStringEquals(String responseBody, String jsonPath, Boolean expected) {
        JSONArray queriedJson = JsonPath.read(responseBody, jsonPath);
        Assert.assertEquals(expected, queriedJson);
    }

    public static void jsonPathIntegerInJsonStringEquals(String responseBody, String jsonPath, String expected) {
        JSONArray queriedJson = JsonPath.read(responseBody, jsonPath);
        Assert.assertEquals(expected, String.valueOf(queriedJson));
    }

    public static void jsonPathBooleanInJsonStringEquals(String responseBody, String jsonPath, String expected) {
        JSONArray queriedJson = JsonPath.read(responseBody, jsonPath);
        Assert.assertEquals(expected, String.valueOf(queriedJson));
    }

    public void responseBodynotnull(String responseBody, String jsonPath) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        Assert.assertTrue(linkedHashMap.size() > 0);
    }


    public String valuefromresponsebyindex(String responseBody, String jsonPath, int index) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        String key = (new ArrayList<String>(linkedHashMap.keySet())).get(index);
//        String value = (new ArrayList<String>(linkedHashMap.values())).get(index);
        return key;
    }
    public void valuefromresponsebyKey(String responseBody, String jsonPath, String Key) {
        HashMap<String, String> HashMap = JsonPath.read(responseBody, jsonPath);
       Assert.assertTrue(HashMap.containsKey(Key));
    }

    public boolean bodyContainsString (String body, String stringtoSearch){
        return body.contains(stringtoSearch);
    }
    public void responseBodyisnull(String responseBody, String jsonPath) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        Assert.assertTrue(linkedHashMap.size() == 0);
    }

    public Response iComposeRestPointforBuildDatewithInvalidKey(String waKey){
        String url = appUrl + "/" + context + "/" + "built-dates" + "?wa_key=" + waKey + "&locale=" + locale;
        return performGet(url);
    }
}
