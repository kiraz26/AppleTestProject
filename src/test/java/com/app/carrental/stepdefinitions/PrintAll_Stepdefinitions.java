package com.app.carrental.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PrintAll_Stepdefinitions {
	@Given("User get request from url")
	public void user_get_request_from_url() {
		
		String url="http://localhost:3000";
		Response response = given().accept(ContentType.JSON).when()
				.get(url+"/cars");
		
		assertEquals(response.statusCode(), 200);
		
		System.out.println(response.asString());
		
		List listOfMaps = response.as(List.class);
		
		System.out.println(listOfMaps.get(0));
		
		
		List<Map> map =  response.jsonPath().getList("metadata", Map.class);
		
		System.out.println(map.get(0));

	}

	@Given("Content Type and Accept type is JSON")
	public void content_Type_and_Accept_type_is_JSON() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("User can see all blue Teslas")
	public void user_can_see_all_blue_Teslas() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("Status code is {int}")
	public void status_code_is(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("User can see notes about blue Teslas")
	public void user_can_see_notes_about_blue_Teslas() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("JSON Response data should match the actual data")
	public void json_Response_data_should_match_the_actual_data() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

}
