package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTIONAL_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;

@Epic("EPIC-02 - Account Page Behaviour and Validation Message")

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void acctPageSetup() {

		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void acctPageTittleTest() {

		String acttitle = accountpage.getaccountpageTittle();
		Assert.assertEquals(acttitle, HOME_PAGE_TITLE);

	}

	@Test
	public void acctPageTittleURL() {

		String acttitle = accountpage.getaccountpageURL();
		System.out.println(acttitle);
		Assert.assertTrue(acttitle.contains(HOME_PAGE_FRACTIONAL_URL));

	}

	// Adding new function

	@Test
	public void acctPageHeadersTest() {
		List<String> accPageHeader = accountpage.getAccountPageHeader();
		Assert.assertTrue(accPageHeader.equals(expected_account_page_header));

	}

}
