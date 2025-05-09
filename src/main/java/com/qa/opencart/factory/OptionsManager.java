package com.qa.opencart.factory;

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
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {

			return co;

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

		}

		return fo;
	}

}
