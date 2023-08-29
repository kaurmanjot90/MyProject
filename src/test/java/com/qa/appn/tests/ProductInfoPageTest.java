package com.qa.appn.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.appn.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { //5.
			{"Macbook","MacBook Pro", 4},
			{"Macbook","MacBook Air", 4},
			{"iMac","iMac", 3},
			{"Apple","Apple Cinema 30\"", 6}
		};	
	}
	
	@Test(dataProvider="getProductImagesTestData")
	public void productImagesTest(String searchKey, String mainProductName, int imageCount) {
		resultsPage = accPage.performSearch(searchKey);//4. zig-zag pattern
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int actImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImageCount, imageCount);
	}
	
	@Test
	public void productMetadataTest() {
		resultsPage = accPage.performSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availablitiy"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("actualprice"), "$2000.00");
		softAssert.assertAll();//5. which assertion got failed - gives all info.
	}
}
