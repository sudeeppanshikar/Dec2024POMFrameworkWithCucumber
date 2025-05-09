package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public static final Logger log = LogManager.getLogger(DriverFactory.class);

	@Step("Intialized properties are passing to this method and properties are : {0}")

	public WebDriver initBrowser(Properties prop) {

		String browser = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		log.info("Browser will be launched :" + browser);
		log.info("Properties file are : " + prop);

		switch (browser.trim().toLowerCase()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteBrowser(browser);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;
		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteBrowser(browser);
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
			break;

		default:
			log.warn("Enter the correct browser");
			throw new BrowserExceptions("Incorrect Brower");
		}

		return getDriver();

	}

	/**
	 * Launch the remote webdriver from Selenium grid
	 *
	 */

	private void initRemoteBrowser(String browser) {
		try {
			switch (browser.trim().toLowerCase()) {
			case "chrome":

				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("remoteUrl")), optionsManager.getChromeOptions()));

				break;

			case "firefox":

				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("remoteUrl")),
						optionsManager.getFireFoxOptions()));

				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * generate local thread copy from the thread local method
	 * 
	 * @return
	 *
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();

	}

	@Step("Receivec the URL from properties file for login {0}")
	public void launchURL(Properties prop) {
		System.out.println(prop.getProperty("URL"));
		String url = prop.getProperty("URL");
		nullCheck(url);
		lengthCheck(url);
		httpsCheck(url);
		getDriver().get(url);
		getDriver().manage().window().maximize();

	}

	public void driverClose() {
		if (getDriver() != null)
			getDriver().close();
	}

	public void driverQuit() {
		if (getDriver() != null)
			getDriver().quit();
	}

	public void lengthCheck(String url) {

		if (url.length() == 0) {
			throw new BrowserExceptions("===URL length cannot be zero====");

		}
	}

	public void httpsCheck(String url) {

		if (url.indexOf("http") != 0) {
			throw new BrowserExceptions("===https is missing====");
		}

	}

	public void nullCheck(String url) {
		if (url == null) {
			throw new BrowserExceptions("===URL cannot be NULL====");
		}
	}

	public void domainCheck(String url) {

		if (url.charAt(url.length() - 4) != '.' || url.charAt(url.length() - 3) != '.') {
			throw new BrowserExceptions("===URL has incorect domain====");

		}
	}

	public Properties initprop() {

		String envName = System.getProperty("env");
		prop = new Properties();
		FileInputStream ip = null;
		try {
			if (envName == null) {
				System.out.println("env is null , hence running the test using the default properties ");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			}

			else {
				switch (envName) {
				case "qa":
					System.out.println("env is QA , hence running the test using the QA properties ");
					ip = new FileInputStream("./src/test/resources/config/com.qa.properties");

					break;

				default:

					throw new FrameworkException("====Incorrect ENV varaiable=======");
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * Screenshot
	 * 
	 * @return
	 *
	 */

	public static File getScreenShotFile() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}

}
