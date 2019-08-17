package com.app.carrental.stepdefinitions;




import static io.restassured.RestAssured.given;



import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Hooks {
	
	public static Response response;
	@Before
	public void setUp(Scenario scenario) {
	
		String url = "http://localhost:3000";
		response = given().accept(ContentType.JSON).when().get(url + "/cars");
		
	}

	@After
	public void tearDown(Scenario scenario) {
				
	}

}
