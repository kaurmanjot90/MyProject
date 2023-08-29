package com.qa.appn.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.appn.factory.DriverFactory;
import com.qa.appn.pages.AccountsPage;
import com.qa.appn.pages.LoginPage;
import com.qa.appn.pages.ProductInfoPage;
import com.qa.appn.pages.RegPage;
import com.qa.appn.pages.ResultsPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage; //so that only child classes could access it.
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegPage regPage;
	protected SoftAssert softAssert;
	protected Properties prop;
	
	@BeforeTest
	public void setup() {
		//responsible for launching the url and setting the browser in basetest
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop); //call be reference
		loginPage = new LoginPage(driver);//the same driver is given here.	
		softAssert = new SoftAssert(); //4. we have to create object of soft assert. for hard assertion, no need.
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
