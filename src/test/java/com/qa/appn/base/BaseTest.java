package com.qa.appn.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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
	protected LoginPage loginPage; //so that only different package's child class could access it.
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegPage regPage;
	protected SoftAssert softAssert;
	protected Properties prop;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setup(String browserName, String browserVersion) {
		//responsible for launching the url and setting the browser in basetest
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		//df.initdriver("chrome");
		driver = df.initDriver(prop); //call by reference - calling a method by passing the reference.
		loginPage = new LoginPage(driver);//the same driver is given here by initDriver.	
		softAssert = new SoftAssert(); //4. we have to create object of soft assert. for hard assertion, no need.
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

//update the project in eclipse in case if it does not work properly.
//we should not write dologin here, as it would skip the login page tests.

