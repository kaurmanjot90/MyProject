package com.qa.appn.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.appn.factory.DriverFactory;
import com.qa.appn.pages.LoginPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage; //so that only child classes could access it.
	
	@BeforeTest
	public void setup() {
		//responsible for launching the url and setting the browser in basetest
		df = new DriverFactory();
		driver = df.initDriver("chrome");
		loginPage = new LoginPage(driver);//the same driver is given here.		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
