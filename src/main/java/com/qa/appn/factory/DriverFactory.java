package com.qa.appn.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

//some people name as DriverManager as well.

public class DriverFactory {

	WebDriver driver; // it will supply the driver to everyone.
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser name is :" + browserName);
		highlight = prop.getProperty("highlight");

		// cross browser logic
		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			// driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
		} else {
			System.out.println("Please pass the correct browser name.." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		// do not want to use implicit wait
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		// no page load timeout - because that is also kind of global wait, but can use
		// it - if you want.
		return getDriver();

	}

	public synchronized static WebDriver getDriver() {
		return tldriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) { // order of exceptions DOES matter.
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
