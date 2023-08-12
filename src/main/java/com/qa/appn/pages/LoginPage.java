package com.qa.appn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.ElementUtil;
import com.qa.appn.utils.TimeUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//Concept - private variables are used in public methods - Encapsulation.
	//1. private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password11");
	private By registerLink = By.linkText("Register");

	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.Page Actions
    public String getLoginPageTitle() {
		//return driver.getTitle();
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public String getLoginPageUrl() {
		//return driver.getCurrentUrl();	
		return eleUtil.waitForUrlContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("creds are :"+un+":"+pwd);
		//driver.findElement(emailId).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		//to verify it has logged in or not.
		//return driver.findElement(By.linkText("Logout")).isDisplayed();
	}
	
}



//page class should not have any assertion code.
//page should not have any testng method.
//PAGE CHAINING MODEL in doLogin method.