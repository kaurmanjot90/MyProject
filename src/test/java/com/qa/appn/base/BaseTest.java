package com.qa.appn.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.appn.factory.DriverFactory;
import com.qa.appn.pages.AccountsPage;
import com.qa.appn.pages.LoginPage;
import com.qa.appn.pages.ProductInfoPage;
import com.qa.appn.pages.ResultsPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage; //so that only child classes could access it.
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		//responsible for launching the url and setting the browser in basetest
		df = new DriverFactory();
		driver = df.initDriver("chrome");
		loginPage = new LoginPage(driver);//the same driver is given here.	
		softAssert = new SoftAssert(); //4. we have to create object of soft assert. for hard assertion, no need.
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
