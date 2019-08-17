package com.app.carrental.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LowestRentalCar_Step_Definitions {
	RequestSpecification request;
	Response response;
	List<Map> metadataMap;
	List<String> makeList;
	List<Map> perdayrentMap;
	String url;

	@Given("User get request from url")
	public void user_get_request_from_url() {
		url = "http://localhost:3000";

	}

	@When("User call Rent a car api")
	public void user_call_Rent_a_car_api() {
		response = given().accept(ContentType.JSON).when().get(url + "/cars");
	}

	@When("Status code is {int} from Response")
	public void status_code_is_from_Response(Integer int1) {
		assertEquals(response.statusCode(), int1.intValue());

	}

	@Then("Verify user can see lowest car per day")
	public void verify_user_can_see_lowest_car_per_day() {
		perdayrentMap = response.jsonPath().getList("perdayrent", Map.class);
		ArrayList<Float> prices = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			prices.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString()));
		}
		Collections.min(prices);
		System.out.println(Collections.min(prices));
	}

	@Then("Verify User can see lowest car per day after discount")
	public void verify_User_can_see_lowest_car_per_day_after_discount() {
		ArrayList<Float> pricesAfterDiscount = new ArrayList<>();
		perdayrentMap = response.jsonPath().getList("perdayrent", Map.class);
		for (int i = 0; i < perdayrentMap.size(); i++) {

			pricesAfterDiscount.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
					- Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
							* Float.parseFloat(perdayrentMap.get(i).get("Discount").toString()) / 100);
		}
		Collections.min(pricesAfterDiscount);
		System.out.println(Collections.min(pricesAfterDiscount));
	}
}
