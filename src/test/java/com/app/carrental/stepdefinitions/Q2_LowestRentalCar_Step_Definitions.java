package com.app.carrental.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;

public class Q2_LowestRentalCar_Step_Definitions {

	List<Map> metadataMap;
	List<String> makeList;
	List<String> vinList;
	static List<Map> perdayrentMap;

	@When("Status code is {int} from Response")
	public void status_code_is_from_Response(Integer int1) {
		assertEquals(Hooks.response.statusCode(), int1.intValue());

	}

	@Then("Verify user can see lowest car per day")
	public void verify_user_can_see_lowest_car_per_day() {

		perdayrentMap = Hooks.response.jsonPath().getList("perdayrent", Map.class);

		printLowestPriceCar();
	}

	public void printLowestPriceCar() {
		System.out.println("=============PRINTING LOWEST CAR PRICE BEFORE DISCOUNT===============");
		ArrayList<Float> prices = new ArrayList<>();
		JsonPath json = Hooks.response.jsonPath(); // Serialization of JSON object
		makeList = json.getList("make");
		vinList = json.getList("vin");
		
		for (int i = 0; i < perdayrentMap.size(); i++) {
			
			prices.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString()));
		}
		Collections.min(prices);
		for (int i = 0; i < makeList.size(); i++) {
			if (Collections.min(prices) == prices.get(i)) {
				System.out.println(Collections.min(prices)+" "+makeList.get(i) + " " + vinList.get(i));
			}
		}
		
	}

	@Then("Verify User can see lowest car per day after discount")
	public void verify_User_can_see_lowest_car_per_day_after_discount() {
		printLowestPriceCarAfterDiscount();
	}

	public void printLowestPriceCarAfterDiscount() {
		System.out.println("=============PRINTING LOWEST CAR PRICE AFTER DISCOUNT==============="); 
		ArrayList<Float> pricesAfterDiscount = new ArrayList<>();
		JsonPath json = Hooks.response.jsonPath(); // Serialization of JSON object
		makeList = json.getList("make");
		vinList = json.getList("vin");
		perdayrentMap = Hooks.response.jsonPath().getList("perdayrent", Map.class);
		for (int i = 0; i < perdayrentMap.size(); i++) {

			pricesAfterDiscount.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
					- Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
							* Float.parseFloat(perdayrentMap.get(i).get("Discount").toString()) / 100);
		}
		Collections.min(pricesAfterDiscount);
		for (int i = 0; i < makeList.size(); i++) {
			if (Collections.min(pricesAfterDiscount) == pricesAfterDiscount.get(i)) {
				System.out.println(Collections.min(pricesAfterDiscount)+" "+makeList.get(i) + " " + vinList.get(i));
			}
			}
	}
}
