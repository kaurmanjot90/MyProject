package com.qa.appn.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


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
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution on docker/grid/cloud
				init_remoteDriver("chrome");
			} else {
				// local execution
				tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution on docker/grid/cloud
				init_remoteDriver("Firefox");
			} else {
				// local execution
				tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			// driver = new EdgeDriver();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution on docker/grid/cloud
				init_remoteDriver("Edge");
			} else {
				// local execution
				tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		} else {     
			System.out.println("Please pass the correct browser name.." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		// do not want to use implicit wait
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		// no page load timeout - because that is also kind of global wait, but can use it - if you want.
		return getDriver();

	}

	private void init_remoteDriver(String browserName) {

		System.out.println("Running test cases on remote grid server: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			try {
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		if (browserName.equalsIgnoreCase("firefox")) {
			try {
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		if (browserName.equalsIgnoreCase("edge")) {
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized static WebDriver getDriver() {
		return tldriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		// mvn clean install -Denv="envName" (-D to pass parameter variable)
		// mvn clean install
		String envName = System.getProperty("env");
		System.out.println("Running Test Cases on : " + envName);
		if (envName == null) {
			System.out.println("No env is given, hence running scripts on QA env");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.toLowerCase()) {
				case ("qa"):
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case ("dev"):
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case ("stage"):
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case ("uat"):
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case ("prod"):
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("please pass the right environment name");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
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
