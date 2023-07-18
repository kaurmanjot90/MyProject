package com.qa.appn.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

//some people name as DriverManager as well.

public class DriverFactory {

	public WebDriver driver; //it will supply the driver to everyone.
	
	public WebDriver initDriver(String browserName) {
		System.out.println("Browser name is :"+ browserName);
		
		//cross browser logic
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Please pass the correct browser name.."+browserName);
		}
		
		driver.manage().deleteAllCookies();
		//do not want to use implicit wait
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//no page load timeout - because that is also kind of global wait, but can use it - if you want.
		return driver;
		
	}
	
	
}
