package com.qa.appn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.appn.utils.ElementUtil;
import com.qa.appn.utils.TimeUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchProducts = By.cssSelector("div.product-layout");

	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getSearchPageTitle(String productName) {
		return eleUtil.waitForTitleContains(productName, TimeUtil.DEFAULT_TIME_OUT);	
	}
	
	public int getSearchProductsCount() {
		return eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String mainProductName) { //4. not using hard coded one.
		System.out.println("main product name :"+mainProductName);
		eleUtil.doClick(By.linkText(mainProductName));
		return new ProductInfoPage(driver);
	}
	
}
