package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.testng.Assert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;

@Epic("EPIC-04 - Register page behaviour and Validations ")
public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {

		registerpage = loginpage.navigateToRegisterPage();

	}

	public String generateRandomEmailid() {
		LocalTime now = LocalTime.now();

		String randomEmailId = now + "@gmail.com";
		randomEmailId = randomEmailId.replace(":", "");
		return randomEmailId;
	}

	@DataProvider

	public Object[][] userRegistartionData() {
		return new Object[][] { { "chris", "folder", generateRandomEmailid(), "9256272883", "automation@123", "no" }

		};

	}

	@Test(dataProvider = "userRegistartionData")

	public void userRegistrationTest(String firstname, String lastname, String email, String telephone, String pwd,
			String subscribe) {

		Boolean expectedresult = registerpage.userRegistration(firstname, lastname, email, telephone, pwd, subscribe);
		Assert.assertTrue(expectedresult);

	}

	@DataProvider

	public Object[][] userEmailFormatValidation() {
		return new Object[][] { { "chris", "folder", "chris1234@qa", "9256272883", "automation@123", "no" }

		};
	}

	@Test(dataProvider = "userEmailFormatValidation")
	public void emailFieldValidationTestForDotCOM(String firstname, String lastname, String email, String telephone,
			String pwd, String subscribe) {

		Boolean expectedresult = registerpage.validateEmailIdFormatForDotCom(firstname, lastname, email, telephone, pwd,
				subscribe);
		Assert.assertTrue(expectedresult);
	}

	@DataProvider

	public Object[][] userEmailFormatValidationForATCharacter() {
		return new Object[][] { { "chris", "folder", "chris1234", "9256272883", "automation@123", "no" }

		};
	}

	@Test(dataProvider = "userEmailFormatValidationForATCharacter")
	public void emailFieldValidationTestForATCharcter(String firstname, String lastname, String email, String telephone,
			String pwd, String subscribe) {

		Boolean expectedresult = registerpage.validateEmailIdFormatForAtCharcter(firstname, lastname, email, telephone,
				pwd, subscribe);
		Assert.assertTrue(expectedresult);
	}

}
