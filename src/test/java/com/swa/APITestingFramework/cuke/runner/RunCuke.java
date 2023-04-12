package com.swa.APITestingFramework.cuke.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"./src/test/resources/features"},plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} ,glue="com.swa.APITestingFramework.cuke.steps")
public class RunCuke extends AbstractTestNGCucumberTests{
	
	

}
