package com.qa.appn.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	//private SafariOptions so;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			// co.setCapability("enableVNC", true);
			co.setPlatformName("linux");
			co.setBrowserVersion(prop.getProperty("browserversion"));
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			co.setCapability("selenoid:options", selenoidOptions);
		}
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			 fo.setCapability("enableVNC", true);
		//	fo.setPlatformName("linux");
			fo.setBrowserVersion(prop.getProperty("browserversion"));
			Map<String, Object> selenoidOptions1 = new HashMap<>();
			selenoidOptions1.put("enableVNC", true);
			fo.setCapability("selenoid:options", selenoidOptions1);
		}
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			fo.addArguments("--incognito");
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			// eo.setCapability("enableVNC", true);
			eo.setPlatformName("linux");
			//Selenoid does not support edge.
		}
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			eo.addArguments("--incognito");
		}
		return eo;
	}
	

	
}
