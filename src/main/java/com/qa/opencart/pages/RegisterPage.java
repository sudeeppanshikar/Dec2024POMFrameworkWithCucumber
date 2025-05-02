package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	WebDriver driver;
	ElementUtil elementUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	By firstname = By.id("input-firstname");
	By lastname = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By pwd = By.id("input-password");
	By confirmpwd = By.id("input-confirm");
	By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	By checkbox = By.xpath("//input[@name='agree']");
	By continue_button = By.xpath("//input[@type='submit']");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	private By emailValidationMessage = By.xpath("//div[@class='text-danger']");

	public boolean userRegistration(String firstname, String lastname, String email, String telephone, String pwd,
			String subscribe) {
		userRegistrationFormFill(firstname, lastname, email, telephone, pwd, subscribe);

		String confirmMessage = elementUtil.waitForElementVisible(successMessg, AppConstants.MED_TIME_OUT).getText();
		if (confirmMessage.equals(AppConstants.SUCCESSFUL_USERCREATION_MESSAGE)) {
			elementUtil.doClick(logoutLink);
			return true;

		}
		return false;

	}

	private void userRegistrationFormFill(String firstname, String lastname, String email, String telephone, String pwd,
			String subscribe) {

		WebElement firstname_element = elementUtil.waitForElementVisible(this.firstname, AppConstants.DEFAULT_TIME_OUT);
		firstname_element.clear();
		firstname_element.sendKeys(firstname);
		elementUtil.doSendkeys(this.lastname, lastname);
		elementUtil.doSendkeys(this.email, email);
		elementUtil.doSendkeys(this.telephone, telephone);
		elementUtil.doSendkeys(this.pwd, pwd);
		elementUtil.doSendkeys(this.confirmpwd, pwd);

		if (subscribe.equalsIgnoreCase("yes")) {
			System.out.println("Yes is clicked");
			elementUtil.doClick(subscribeYes);
		} else {
			System.out.println("NO is clicked");
			elementUtil.doClick(subscribeNo);
		}

		elementUtil.doClick(checkbox);
		elementUtil.doClick(continue_button);

	}

	public boolean validateEmailIdFormatForDotCom(String firstname, String lastname, String email, String telephone,
			String pwd, String subscribe) {
		userRegistrationFormFill(firstname, lastname, email, telephone, pwd, subscribe);
		String emailValidationMessage = elementUtil
				.waitForElementVisible(this.emailValidationMessage, AppConstants.MAX_TIME_OUT).getText();
		if (emailValidationMessage.equals(AppConstants.EMAIL_FORMAT_VALIDATION_MESSAGE)) {
			System.out.println("Please enter the email id with .com");
			System.out.println(emailValidationMessage);
			elementUtil.doClick(checkbox);
			return true;
		}
		return false;

	}

	public boolean validateEmailIdFormatForAtCharcter(String firstname, String lastname, String email, String telephone,
			String pwd, String subscribe) {
		userRegistrationFormFill(firstname, lastname, email, telephone, pwd, subscribe);
		String emailFormatValidation = elementUtil.getElement(this.email).getDomProperty("validationMessage");

		if (emailFormatValidation.contains(AppConstants.EMAIL_FORMAT_VALIDATION_MESSAGE_MISSING)) {
			System.out.println(emailFormatValidation);
			System.out.println("Please enter @ in the email id ");
			elementUtil.doClick(checkbox);
			return true;
		}
		return false;

	}

}
