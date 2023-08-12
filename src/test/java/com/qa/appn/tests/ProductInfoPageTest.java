package com.qa.appn.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.appn.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin("abc@gmail.com", "abc");
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { //5.
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};	
	}

	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey, String mainProductName) { 
		resultsPage = accPage.performSearch(searchKey);//4. zig-zag pattern
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, mainProductName);
	}
}
