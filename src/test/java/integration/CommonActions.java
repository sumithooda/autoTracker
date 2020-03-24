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

    /**
     * Fetching values from property file
     */
    private static String appUrl = new config().getProperrty("appurl");
    private static String wa_key = new config().getProperrty("wa_key");
    private static String context = new config().getProperrty("context");
    private static String locale = new config().getProperrty("locale");

    /**
     * Method to customize url for various end points
     * and with various parameters & values.
     * @param endPoint
     * @param valueManufac
     * @param valueMain
     * @return
     */
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

    /**
     * Customize url for getBuilt dates endpoint for testing error scenario
     * in this we are concating only one parameter either manufacturer or Main types
     * @param paramName
     * @param pramValue
     * @return
     */
    public Response iComposeRestPointforBuildDate(String paramName, String pramValue){
        String url = appUrl + "/" + context + "/" + "built-dates" + "?wa_key=" + wa_key + "&locale=" + locale + "&" + paramName+ "=" + pramValue;
        return performGet(url);
    }

    /**
     * Perform Get operation on url
     * @param url
     * @return restassured response
     */
    public static Response performGet(String url) {
        System.out.println("\n Performing GET to : " + url);
        return RestAssured.given().get(url);
    }

    /**
     * to verify reponse body is not null.
     * @param responseBody
     * @param jsonPath
     */
    public void responseBodynotnull(String responseBody, String jsonPath) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        Assert.assertTrue(linkedHashMap.size() > 0);
    }

    /**
     * fetch values, keys from response body based on index
     * @param responseBody
     * @param jsonPath
     * @param index
     * @return key present in body
     */
    public String valuefromresponsebyindex(String responseBody, String jsonPath, int index) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        String key = (new ArrayList<String>(linkedHashMap.keySet())).get(index);
        return key;
    }

    /**
     * to verify certain value is present in bosdy based on key.
     * @param responseBody
     * @param jsonPath
     * @param Key
     */
    public void valuefromresponsebyKey(String responseBody, String jsonPath, String Key) {
        HashMap<String, String> HashMap = JsonPath.read(responseBody, jsonPath);
       Assert.assertTrue(HashMap.containsKey(Key));
    }

    /**
     * Method to verify a given string is present in response body
     * @param body
     * @param stringtoSearch
     * @return
     */
    public boolean bodyContainsString (String body, String stringtoSearch){
        return body.contains(stringtoSearch);
    }

    /**
     * Method to verify response body is empty
     * @param responseBody
     * @param jsonPath
     */
    public void responseBodyisnull(String responseBody, String jsonPath) {
        LinkedHashMap<String, String> linkedHashMap = JsonPath.read(responseBody, jsonPath);
        Assert.assertTrue(linkedHashMap.size() == 0);
    }

    /**
     * method to customize url with invalid key for error sceanrios testing.
     * @param waKey
     * @return
     */
    public Response iComposeRestPointforBuildDatewithInvalidKey(String waKey){
        String url = appUrl + "/" + context + "/" + "built-dates" + "?wa_key=" + waKey + "&locale=" + locale;
        return performGet(url);
    }
}
