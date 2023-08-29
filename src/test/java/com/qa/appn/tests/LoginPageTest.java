package com.qa.appn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.appn.base.BaseTest;
import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 001: Design Login Page")
@Story("US - 001: Login Page Functionality	")
public class LoginPageTest extends BaseTest {

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("login Page Title = " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
		//steps + validation = automation testing.
	}

	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		System.out.println("login Page URL = " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Description("Forgot Pwd Link Exists Test on Login Page Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void forgotPwdLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("User is able to login")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutExists(),AppErrors.LOGIN_UNSUCCESSFUL);
	}
}









//writing Test at the end of every test method
//easy to identify that it is test method.
//Cannot inherit loginPage directly here - bcz parent class is present in other package.
//either declare it public or protected
//without assertions - there is no test. Because we are not validating the things then.
//this class does not contain any driver. code and just responsible for assertions
//custom assertion message is optional.
//martin fowler is responsible for pom design pattern.
//Simon Stewart - creator of Selenium.