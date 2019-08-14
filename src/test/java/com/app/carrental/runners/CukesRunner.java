package com.app.carrental.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-report", "json:target/cucumber.json",
		"rerun:target/rerun.txt" }, tags = "", features = {
				"src/test/resources/com/app/features/" }, glue = "com/app/carrental/stepdefinitions", dryRun = true)

public class CukesRunner extends AbstractTestNGCucumberTests {

}
