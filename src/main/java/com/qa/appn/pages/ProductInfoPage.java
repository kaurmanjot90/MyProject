package com.qa.appn.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.appn.utils.ElementUtil;
import com.qa.appn.utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("a.thumbnail");
	private By productMetadata = By.xpath("");
	private By pricingData = By.xpath("");
	
	private Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eleUtil.doGetElementText(productHeader);
	}
	
	public int getProductImagesCount() {
		int ImagesCount = eleUtil.waitForElementsVisible(productImages, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("Total Image Count on product info page = "+ImagesCount);
	    return ImagesCount;
	}
	
	
	public Map<String, String> getProductInformation() { //5.
		productMap = new HashMap<String, String>();
		getProductMetadata();
		getProductPricingdata();
		System.out.println(productMap);
		return productMap;
		
	}

	private void getProductMetadata() { 
		List<WebElement> metaDataList = eleUtil.getElements(productMetadata);
		System.out.println("product metadata count -->"+ metaDataList.size());
		
		for(WebElement e : metaDataList) {
			String meta = e.getText();
			String metaData[] = meta.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
		private void getProductPricingdata() {
			List<WebElement> metaPriceList = eleUtil.getElements(pricingData);
			System.out.println("product rice count -->"+ metaPriceList.size());
			String price = metaPriceList.get(0).getText().trim();
			String ExTaxPrice = metaPriceList.get(0).getText().trim();
			
			productMap.put("actprice", price);
			productMap.put("actExTaxPrice", ExTaxPrice);
	}
}
