package com.qa.appn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.ElementUtil;
import com.qa.appn.utils.TimeUtil;

import io.qameta.allure.Step;

//this class should return the behavior of the page.
public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//Concept - private variables are used in public methods - Encapsulation in Line 55.
	//1. private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.Page Actions
	@Step("getting login page title..")
    public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
		//return driver.getTitle();
	}
	
	@Step("getting login page url..")
	public String getLoginPageUrl() {
		//return driver.getCurrentUrl();	
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	@Step("if forgot pwd link exists..")
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("logging into application with username : {0} and password : {1}")
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("creds are :"+un+":"+pwd);
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		//driver.findElement(emailId).sendKeys(un);
	    //driver.findElement(password).sendKeys(pwd);
	    //driver.findElement(loginBtn).click(); //Concept - private variables are used in public methods - Encapsulation.
		//to verify it has logged in or not.
		//return driver.findElement(By.linkText("Logout")).isDisplayed();
	}
	
	@Step("navigating to register page..")
	public RegPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}
	
}



//page class should not have any assertion code.
//page should not have any testng method.
//PAGE CHAINING MODEL/ZIG ZAG PATTERN in doLogin method.
//login page is not responsible for driver, it just says give me the driver and i will use it.