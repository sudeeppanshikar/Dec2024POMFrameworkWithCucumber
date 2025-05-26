package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CartInfoPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {

	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountPage accountpage;
	protected SearchResultsPage searchrespage;
	protected ProductInfoPage prodinfopage;
	protected RegisterPage registerpage;
	protected CartInfoPage cartinfopage;
	WebDriver driver;
	protected Properties prop;

	@Step("Intializing the Driver through Driver Factory")
	@Parameters({ "browser", "browserversion" })

	@BeforeTest
	public void setup(@Optional String browserName, @Optional String browserversionName) {

		df = new DriverFactory();
		prop = df.initprop();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserversionName);
		}
		driver = df.initBrowser(prop);
		df.launchURL(prop);
		loginpage = new LoginPage(driver);

	}

	@AfterMethod // will be runnig after each @Test method
	public void attachScreeshot(ITestResult result) {

		if (!result.isSuccess()) {// only for faiure test case
			ChainTestListener.embed(DriverFactory.getScreenShotFile(), "image/png");

		}
	}

	@AfterTest
	public void teardown() {

		df.driverQuit();
	}

}
