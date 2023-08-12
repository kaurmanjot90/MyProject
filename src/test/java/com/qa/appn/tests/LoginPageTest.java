package com.qa.appn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.appn.base.BaseTest;
import com.qa.appn.utils.AppConstants;
import com.qa.appn.utils.AppErrors;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("login Page Title = " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
		//steps + validation = automation testing.
	}

	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		System.out.println("login Page URL = " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Test
	public void forgotPwdLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test
	public void loginTest() {
		accPage = loginPage.doLogin("abc@gmail.com", "abc");
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