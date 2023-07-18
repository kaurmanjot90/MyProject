package com.qa.appn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	
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
	}
	
	//3.Page Actions
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();		
	}
	
	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public boolean doLogin(String un,String pwd) {
		System.out.println("creds are :"+un+":"+pwd);
		driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		//to verify it has logged in or not.
		return driver.findElement(By.linkText("Logout")).isDisplayed();
	}
	
}



//page class should not have any assertion code.
//page should not have any testng method.