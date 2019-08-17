package com.app.carrental.stepdefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Q3_RentACarRevenueStepDef {
	;
	List<Map> metricsMap;
	List<Map> metadataMap;
	List<String> makeList;
	List<String> vinList;
	List<Map> perdayrentMap;
	List<Map> rentalcountMap;

	@Then("Verify user can see revenues for each car")
	public void verify_user_can_see_revenues_for_each_car() {
		perdayrentMap = Hooks.response.jsonPath().getList("perdayrent", Map.class);
		rentalcountMap = Hooks.response.jsonPath().getList("metrics.rentalcount", Map.class);
		ArrayList<Float> pricesAfterDiscount = calculateDiscountedPrice();
		ArrayList<Float> highestRevenue = calculateHighestRevenue(pricesAfterDiscount);
		JsonPath json = Hooks.response.jsonPath(); // Serialization of JSON object
		makeList = json.getList("make");
		vinList = json.getList("vin");
		printHighestRevenueCar(highestRevenue);

	}

	public void printHighestRevenueCar(ArrayList<Float> highestRevenue) {
		
		for (int i = 0; i < makeList.size(); i++) {
			if (Collections.max(highestRevenue) == highestRevenue.get(i)) {
				System.out.println(makeList.get(i) + " " + vinList.get(i));
			}
		}
	}

	public ArrayList<Float> calculateHighestRevenue(ArrayList<Float> pricesAfterDiscount) {
		ArrayList<Float> highestRevenue = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			highestRevenue.add(
					pricesAfterDiscount.get(i) * Float.parseFloat(rentalcountMap.get(i).get("yeartodate").toString()));
		}
		Collections.max(highestRevenue);
		System.out.println("=============PRINTING HIGHEST REVENUE CAR==============="); 

		System.out.println(Collections.max(highestRevenue));
		return highestRevenue;
	}

	public ArrayList<Float> calculateDiscountedPrice() {
		ArrayList<Float> pricesAfterDiscount = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			pricesAfterDiscount.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
					- Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
							* Float.parseFloat(perdayrentMap.get(i).get("Discount").toString()) / 100);
		}
		return pricesAfterDiscount;
	}

	@Then("Verify User can see see car profits in descending order")
	public void verify_User_can_see_see_car_profits_in_descending_order() {
		perdayrentMap = Hooks.response.jsonPath().getList("perdayrent", Map.class);
		ArrayList<Float> highestProfit = new ArrayList<>();
		ArrayList<Float> highestRevenue = new ArrayList<>();
		ArrayList<Float> totalExpence = new ArrayList<>();
		ArrayList<Float> pricesAfterDiscount = new ArrayList<>();

		calculateBiggestRevenueCar(highestProfit, highestRevenue, totalExpence, pricesAfterDiscount);

	}

	public void calculateBiggestRevenueCar(ArrayList<Float> highestProfit, ArrayList<Float> highestRevenue,
			ArrayList<Float> totalExpence, ArrayList<Float> pricesAfterDiscount) {
		for (int i = 0; i < perdayrentMap.size(); i++) {

			pricesAfterDiscount.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
					- Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
							* Float.parseFloat(perdayrentMap.get(i).get("Discount").toString()) / 100);
		}
		metricsMap = Hooks.response.jsonPath().getList("metrics", Map.class);

		for (int i = 0; i < metricsMap.size(); i++) {

			totalExpence.add(Float.parseFloat(metricsMap.get(i).get("yoymaintenancecost").toString())
					+ Float.parseFloat(metricsMap.get(i).get("depreciation").toString()));
		}
		rentalcountMap = Hooks.response.jsonPath().getList("metrics.rentalcount", Map.class);

		for (int i = 0; i < perdayrentMap.size(); i++) {

			highestRevenue.add(
					pricesAfterDiscount.get(i) * Float.parseFloat(rentalcountMap.get(i).get("yeartodate").toString()));
		}
		for (int i = 0; i < perdayrentMap.size(); i++) {

			highestProfit.add(highestRevenue.get(i) - totalExpence.get(i));
		}
		Collections.max(highestProfit);
		System.out.println("=============PRINTING HIGHEST PROFIT CAR==============="); 
		System.out.println(Collections.max(highestProfit));
		JsonPath json = Hooks.response.jsonPath(); // Serialization of JSON object
		makeList = json.getList("make");
		vinList = json.getList("vin");

		printHighestRevenueCar(highestProfit);
	}
}
