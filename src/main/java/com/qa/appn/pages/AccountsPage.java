package com.qa.appn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.ElementUtil;
import com.qa.appn.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By logoutLink = By.linkText("Logout");
	private By accSecHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		// return driver.getTitle();
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}

	public String getAccountPageURL() {
		// return driver.getCurrentUrl();
		return eleUtil.waitForUrlContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}

	public boolean ifSearchExists() {
		// return driver.findElement(search).isDisplayed();
		// return eleUtil.doIsDisplayed(search);
		return eleUtil.waitForElementVisible(search, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public ResultsPage performSearch(String productName) {
		System.out.println("product search : "+ productName);
		if(ifSearchExists()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			//4. to return next landing class object
			return new ResultsPage(driver);
		}
		return null; //search exists is not present.
	}

	public boolean isLogoutExists() {
		// return driver.findElement(logoutLink).isDisplayed();
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public List<String> getAccountsPageSectionHeaders() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accSecHeaders, TimeUtil.DEFAULT_TIME_OUT);
		List<String> secHeadersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			secHeadersValList.add(text);

		}
		return secHeadersValList;
	}
}
