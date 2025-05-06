package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

@Epic("Test the Login Pages for User interaction")
public class LoginPageTest extends BaseTest {
	
	@Description("Verify the Page Title")

	@Test
	public void loginPageTitleTest() {

		String actTittle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTittle, LOGIN_PAGE_TITLE);
	}

	@Description("Verify the Page URL")
	@Test
	public void loginPageURLTest() {
		String loginurl = loginpage.getLoginPageURL();
		Assert.assertTrue(loginurl.contains(LOGIN_PAGE_FRACTIONAL_URL));

	}

	@Description("Verify the Landing Page Title. Navigate to AccountPage")
	@Test(priority = Integer.MAX_VALUE)
	public void loginPageSuccefullTest() {
		String uname = prop.getProperty("username");
		String pwd = prop.getProperty("password");
		accountpage = loginpage.doLogin(uname, pwd);
		String actTittle = accountpage.getaccountpageTittle();
		Assert.assertEquals(actTittle, HOME_PAGE_TITLE);

	}

	@Description("Verify the Continue Button Exist")
	@Test(enabled = true)
	public void continuebuttonExistTest() {

		Assert.assertTrue(loginpage.isContinueButtonExist());

	}

	@Description("Verify the Forgot Password Button Exist")
	@Test
	public void forgottenPasswordExistTest() {

		Assert.assertTrue(loginpage.isForgottenPasswordLinkExist());
	}

	@Description("Verify the Menu list displayed in right side of the Page")
	@Test
	public void rightMenuListItemTest() {
		List<String> rightMenuList = loginpage.getRightMenuList();
		System.out.println(rightMenuList);
		Assert.assertTrue(rightMenuList.contains("Login"));

	}

}
