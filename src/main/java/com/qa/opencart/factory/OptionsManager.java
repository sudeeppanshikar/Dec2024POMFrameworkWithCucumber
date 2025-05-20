package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	Properties prop;

	public OptionsManager(Properties prop) {
		this.prop = prop;

	}

	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");

		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {

			co.addArguments("--incognito");

		}
		if (Boolean.parseBoolean(prop.getProperty("selenium_grid"))) {

			return co;

		}
		
		if (Boolean.parseBoolean(prop.getProperty("selenoid"))) {

			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			co.setCapability("selenoid:options", selenoidOptions);
		}
		
		

		return co;
	}

	public FirefoxOptions getFireFoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");

			if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
				fo.addArguments("--incognito");
			}
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {

				return fo;

			}
			
			
			if (Boolean.parseBoolean(prop.getProperty("selenoid"))) {

				fo.setCapability("browserName", "chrome");
				fo.setBrowserVersion(prop.getProperty("browserversion").trim());

				Map<String, Object> selenoidOptions = new HashMap<>();
				selenoidOptions.put("screenResolution", "1280x1024x24");
				selenoidOptions.put("enableVNC", true);
				selenoidOptions.put("name", prop.getProperty("testname"));
				fo.setCapability("selenoid:options", selenoidOptions);
			}

		}

		return fo;
	}

}
