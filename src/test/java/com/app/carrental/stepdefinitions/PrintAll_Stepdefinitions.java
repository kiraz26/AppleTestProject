package com.app.carrental.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.groovy.json.internal.Value;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
 * Question 1: Print all the blue Teslas received in the web service response. Also print the notes
    Question 2: Return all cars which have the lowest per day rental cost for both cases:
    				a. Price only
    				b. Price after discounts
    Question 3: Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense per car for the full year for the rental car company.
    			The objective is to find those cars that produced the highest profit in the last year
 */

public class PrintAll_Stepdefinitions {
	@Given("User get request from url")
	public void user_get_request_from_url() {

		String url = "http://localhost:3000";
		//String fileUrl="C:\\Users\\Halil\\eclipse-workspace\\AppleTestProject\\db.json";
		Response response = given().accept(ContentType.JSON).when().get(url + "/cars");
		//Response response = given().accept(ContentType.JSON).when().get(fileUrl);

		assertEquals(response.statusCode(), 200);

		System.out.println(response.asString());

		List listOfMaps = response.as(List.class);

		System.out.println(listOfMaps.get(0));

		/*
		 * metadata in list
		 */
		System.out.println("---------METADATA--------");
		List<Map> metadataMap = response.jsonPath().getList("metadata", Map.class);

		System.out.println("---------COLOR--------");
		for (int i = 0; i < metadataMap.size(); i++) {
			System.out.println(metadataMap.get(i).get("Color"));
		}
		System.out.println("---------NOTES--------");
		for (int i = 0; i < metadataMap.size(); i++) {
			System.out.println(metadataMap.get(i).get("Notes"));
		}
		System.out.println("-----------------");
		System.out.println(metadataMap.get(0).get("Color"));
		System.out.println(metadataMap.get(1).get("Color"));
		System.out.println(metadataMap.get(0).get("Notes"));
		System.out.println(metadataMap.get(1).get("Notes"));
		System.out.println("-------MAKE----------");

		JsonPath json = response.jsonPath();

		List<String> makeList = json.getList("make");
		System.out.println(makeList);
		
		System.out.println("-------VIN----------");
		List<String> vinList = json.getList("vin");
		System.out.println(vinList);
		/*
		 * Question 1: Print all the blue Teslas received in the web service response.
		 * Also print the notes
		 */
		System.out.println("--------BLUE TESLAS WITH NOTES---------");
		for (int i = 0; i < makeList.size(); i++) {
			if (makeList.get(i).equals("Tesla") && metadataMap.get(i).get("Color").equals("Blue")) {
				System.out.println(makeList.get(i));
				System.out.println(metadataMap.get(i).get("Notes"));
			}
		}

		/*
		 * Question 2: Return all cars which have the lowest per day rental cost for
		 * both cases: a. Price only b. Price after discounts
		 */
		/*
		 * per day rent in list
		 */
		System.out.println("--------PER DAY RENT---------");
		List<Map> perdayrentMap = response.jsonPath().getList("perdayrent", Map.class);
		System.out.println("--------PRICES---------");

		for (int i = 0; i < perdayrentMap.size(); i++) {
			System.out.println(perdayrentMap.get(i).get("Price"));
		}
		System.out.println("--------DISCOUNT---------");
		for (int i = 0; i < perdayrentMap.size(); i++) {
			System.out.println(perdayrentMap.get(i).get("Discount"));
		}
		System.out.println("--------THE LOWEST PRICE---------");
		ArrayList<Float> prices = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			prices.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString()));
		}
		Collections.min(prices);
		System.out.println(Collections.min(prices));

		System.out.println("--------THE LOWEST PRICE AFTER DISCOUNT---------");
		ArrayList<Float> pricesAfterDiscount = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			pricesAfterDiscount.add(Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
					- Float.parseFloat(perdayrentMap.get(i).get("Price").toString())
							* Float.parseFloat(perdayrentMap.get(i).get("Discount").toString()) / 100);
		}
		Collections.min(pricesAfterDiscount);
		System.out.println(Collections.min(pricesAfterDiscount));

		System.out.println("--------PRICES AFTER DISCOUNT---------");
		for (int i = 0; i < perdayrentMap.size(); i++) {

			System.out.println(pricesAfterDiscount.get(i)+" "+makeList.get(i)+" "+vinList.get(i));

		}
		for (int i = 0; i < makeList.size(); i++) {
			if(Collections.max(pricesAfterDiscount)==pricesAfterDiscount.get(i)) {
				System.out.println(makeList.get(i)+" "+ vinList.get(i));
			}
		}
		
		/*
		 * Question 3: Find the highest revenue generating car. year over year
		 * maintenance cost + depreciation is the total expense per car for the full
		 * year for the rental car company. The objective is to find those cars that
		 * produced the highest profit in the last year
		 */
		System.out.println("--------Maintenance Cost---------");
		List<Map> metricsMap = response.jsonPath().getList("metrics", Map.class);
		for (int i = 0; i < metricsMap.size(); i++) {
			System.out.println(metricsMap.get(i).get("yoymaintenancecost")+" "+makeList.get(i)+" "+vinList.get(i));
		}

		System.out.println("--------Depreciation---------");

		for (int i = 0; i < metricsMap.size(); i++) {
			System.out.println(metricsMap.get(i).get("depreciation")+" "+makeList.get(i)+" "+vinList.get(i));
		}

		System.out.println("--------TOTAL EXPENSE---------");
		ArrayList<Float> totalExpence = new ArrayList<>();
		for (int i = 0; i < metricsMap.size(); i++) {

			totalExpence.add(Float.parseFloat(metricsMap.get(i).get("yoymaintenancecost").toString())
					+ Float.parseFloat(metricsMap.get(i).get("depreciation").toString()));
		}
		for (int i = 0; i < metricsMap.size(); i++) {

			System.out.println(totalExpence.get(i)+" "+makeList.get(i)+" "+vinList.get(i));
		}
		

		System.out.println("--------RENTAL COUNT---------");

		for (int i = 0; i < metricsMap.size(); i++) {
			System.out.println(metricsMap.get(i).get("rentalcount").toString());

		}

		System.out.println("--------RENTAL COUNT Last Week---------");
		List<Map> rentalcountMap = response.jsonPath().getList("metrics.rentalcount", Map.class);
		for (int i = 0; i < rentalcountMap.size(); i++) {
			System.out.println(rentalcountMap.get(i).get("lastweek")+" "+makeList.get(i)+" "+vinList.get(i));
		}
		System.out.println("--------RENTAL COUNT Year to Date---------");
		for (int i = 0; i < rentalcountMap.size(); i++) {
			System.out.println(rentalcountMap.get(i).get("yeartodate")+" "+makeList.get(i)+" "+vinList.get(i));
		}
		System.out.println("--------Highest Revenue---------");

		ArrayList<Float> highestRevenue = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			highestRevenue.add(
					pricesAfterDiscount.get(i) * Float.parseFloat(rentalcountMap.get(i).get("yeartodate").toString()));
		}
		Collections.max(highestRevenue);
		System.out.println(Collections.max(highestRevenue));
		for (int i = 0; i < makeList.size(); i++) {
			if(Collections.max(highestRevenue)==highestRevenue.get(i)) {
				System.out.println(makeList.get(i)+" "+ vinList.get(i));
			}
		}

		System.out.println("--------Highest Profit---------");
		ArrayList<Float> highestProfit = new ArrayList<>();
		for (int i = 0; i < perdayrentMap.size(); i++) {

			highestProfit.add(highestRevenue.get(i) - totalExpence.get(i));
		}
		Collections.max(highestProfit);
		System.out.println(Collections.max(highestProfit));
		for (int i = 0; i < makeList.size(); i++) {
			if(Collections.max(highestProfit)==highestProfit.get(i)) {
				System.out.println(makeList.get(i)+" "+ vinList.get(i));
			}
		}

	}

	@Given("Content Type and Accept type is JSON")
	public void content_Type_and_Accept_type_is_JSON() {

	}

	@When("User can see all blue Teslas")
	public void user_can_see_all_blue_Teslas() {
		// Write code here that turns the phrase above into concrete actions
		// throw new cucumber.api.PendingException();
	}

	@When("Status code is {int}")
	public void status_code_is(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		// throw new cucumber.api.PendingException();
	}

	@Then("User can see notes about blue Teslas")
	public void user_can_see_notes_about_blue_Teslas() {
		// Write code here that turns the phrase above into concrete actions
		// throw new cucumber.api.PendingException();
	}

	@Then("JSON Response data should match the actual data")
	public void json_Response_data_should_match_the_actual_data() {
		// Write code here that turns the phrase above into concrete actions
		// throw new cucumber.api.PendingException();
	}

}
