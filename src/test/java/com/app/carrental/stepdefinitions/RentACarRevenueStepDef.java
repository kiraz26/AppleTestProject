package com.app.carrental.stepdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class RentACarRevenueStepDef {
	Response response;
	List<Map> metricsMap;
	List<Map> metadataMap;
	List<String> makeList;
	List<String> vinList;
	
	@Then("Verify user can see revenues for each car")
	public void verify_user_can_see_revenues_for_each_car() {
	metricsMap = response.jsonPath().getList("metrics", Map.class);
	ArrayList<Float> totalExpense = new ArrayList<>();
	for (int i = 0; i < metricsMap.size(); i++) {

		totalExpense.add(Float.parseFloat(metricsMap.get(i).get("yoymaintenancecost").toString())
				+ Float.parseFloat(metricsMap.get(i).get("depreciation").toString()));
	}
	for (int i = 0; i < metricsMap.size(); i++) {

		System.out.println(totalExpense.get(i)+" "+makeList.get(i)+" "+vinList.get(i));

	    
	}
	}
	@Then("Verify User can see see car profits in descending order")
	public void verify_User_can_see_see_car_profits_in_descending_order() {
	    
	}

}
