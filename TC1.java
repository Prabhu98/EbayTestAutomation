package resources;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;


@SuppressWarnings("unused")
public class TC1 {
	
    
	public static void main(String[] args) {
		
		
		RequestSpecification request = new RequestSpecBuilder().build();
		Response response = given().log().all().header("Content-Type","application/json").when().get("https://api.coindesk.com/v1/bpi/currentprice.json")
				.then().log().all().statusCode(200).extract().response();
        System.out.println(response);
	    
        String responseBody = response.getBody().asString();

        
        JsonPath jsonPath = response.jsonPath();
        
        assertThat(jsonPath.getMap("bpi"), hasKey("USD"));
        assertThat(jsonPath.getMap("bpi"), hasKey("GBP"));
        assertThat(jsonPath.getMap("bpi"), hasKey("EUR"));


        
        String gbpDescription = jsonPath.getString("bpi.GBP.description");
        Assert.assertThat(gbpDescription, equalTo("British Pound Sterling"));

        System.out.println("Verification Passed!");
        
	   
	    
        
        
        
		
		/*
		 * for(int i=0;i<=count;i++){ String currency = js.getString("bpi["+i+"].USD");
		 * System.out.println(currency); }
		 * 
		 * 
		 * String res = js.getString("bpi"); System.out.println(res);
		 * 
		 * 
		 * // Verify presence of the three BPIs (USD, GBP, EUR) if(count !=3 ||
		 * !res.equals("bpi.USD") || !res.equals("bpi.GBP") || !res.equals("bpi.EUR")) {
		 * System.out.println("Not all BPIs (USD, GBP, EUR) found in response.");
		 * return; }
		 * 
		 * String gbpdescription = js.getString("bpi.GBP.descrption");
		 * System.out.println(gbpdescription);
		 * if(!gbpdescription.equals("British Pound Sterling")) {
		 * System.out.println("GBP description is not 'British Pound Sterling'.");
		 * return; }
		 * 
		 * System.out.
		 * println("All BPIs (USD, GBP, EUR) found in response and GBP description is 'British Pound Sterling'."
		 * );
		 */

		}

	private static void assertThat(Map<Object, Object> map, Matcher<Map<? extends String, ?>> hasKey) {
		// TODO Auto-generated method stub
		
	}
		
	}


