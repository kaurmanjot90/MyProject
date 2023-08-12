package com.qa.appn.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.appn.base.BaseTest;
import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.AppErrors;

public class AccountsPageTest extends BaseTest{
	
	//individual pre-condition for accounts page only.
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin("abc@gmail.com", "abc");
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountPageTitle();
		System.out.println("Accounts Page Title : " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountPageURL();
		System.out.println("Accounts Page URL : " + actURL);
		Assert.assertEquals(actURL.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.ifSearchExists());
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actualHeaders = accPage.getAccountsPageSectionHeaders(); 
		Assert.assertEquals(actualHeaders,AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}
	
	@DataProvider //4.
	public Object[][] getProductName(){
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "getProductName")
	public void productSearchTest(String productName) {
		//String productName = "macbook"; //4.TDD Approach
		resultsPage = accPage.performSearch(productName);
		String actTitle = resultsPage.getSearchPageTitle(productName);
		System.out.println("actualTitle : "+ actTitle);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_TITLE+" "+productName);
		Assert.assertTrue(resultsPage.getSearchProductsCount()>0); //4. count should be greater than zero.
	}
	
}
