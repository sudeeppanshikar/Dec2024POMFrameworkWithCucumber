package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.AppConstants;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
//Arranging Driver for ElementUtil
	WebDriver driver;
	ElementUtil elementUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	// Creating Locators for Login Page Class

	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By forgottenPassword = By.linkText("Forgotten Password");
	private By loginbutton = By.cssSelector("input.btn");
	private By continuebutton = By.linkText("Continue");
	private By rightmenulists = By.xpath("//div[@class='list-group']/a");
	
	private By register = By.linkText("Register");
	

	// Actions

	public String getLoginPageTitle() {

		return elementUtil.waitForTitle(LOGIN_PAGE_TITLE, DEFAULT_TIME_OUT);

	}

	public String getLoginPageURL() {

		return elementUtil.waitforURLContains(LOGIN_PAGE_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	public boolean isForgottenPasswordLinkExist() {

		return elementUtil.doElementdisplayed(forgottenPassword);
	}
@Step("email enterd is {0} and password is {1}")
	public AccountPage doLogin(String email, String Password) {

		elementUtil.doSendkeys(emailid, email);
		elementUtil.doSendkeys(password, Password);
		elementUtil.doClick(loginbutton);

		return new AccountPage(driver);

	}

	public Boolean isContinueButtonExist() {
		return elementUtil.doElementdisplayed(continuebutton);

	}

	public List<String> getRightMenuList() {
		return elementUtil.getElementTextList(rightmenulists);
	}
	
	
	public RegisterPage navigateToRegisterPage() {
		
		elementUtil.doClick(register);
		return new RegisterPage(driver);
		
	}

}
