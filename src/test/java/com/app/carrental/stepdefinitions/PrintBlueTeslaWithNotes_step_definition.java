package com.app.carrental.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PrintBlueTeslaWithNotes_step_definition {

	RequestSpecification request;
	Response response;
	List<Map> metadataMap;
	List<String> makeList;
	

	@Given("User gets request from url")
	public void user_gets_request_from_url() {
		String url = "http://localhost:3000";
		response = given().accept(ContentType.JSON).when().get(url + "/cars");
		assertEquals(response.statusCode(), 200);

	}

	@When("User can call car rental api to see {string} {string}")
	public void user_can_call_car_rental_api_to_see(String string, String string2) {
		JsonPath json = response.jsonPath(); // Serilization of JSON object
		makeList = json.getList("make");
		
		metadataMap = response.jsonPath().getList("metadata", Map.class);

	}

	@Then("verify user can see {string} {string}")
	public void verify_user_can_see(String string, String string2) {
		for (int i = 0; i < makeList.size(); i++) {
			if (makeList.get(i).equals(string) && metadataMap.get(i).get("Color").equals(string2)) {
				assertTrue(true);
				System.out.println("There is Blue Tesla");
			}
		}

	}

	@Then("verify user can see detail notes")
	public void verify_user_can_see_detail_notes() {
		for (int i = 0; i < makeList.size(); i++) {
			if (makeList.get(i).equals("Tesla") && metadataMap.get(i).get("Color").equals("Blue")) {
				System.out.println(makeList.get(i)+" " +metadataMap.get(i).get("Color")+" "+metadataMap.get(i).get("Notes"));
				
			}
		}
	}

}
