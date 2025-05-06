package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
@Epic("EPIC-06 - Searching Functionality ")
public class SearchTest extends BaseTest {

	@BeforeClass

	public void searchPageSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = Integer.MIN_VALUE)
	public void searchProductCountTest() {
		searchrespage = accountpage.doSearch("mac");

		int actResultsCount = searchrespage.getResultsProductCount();
		Assert.assertEquals(actResultsCount, 4);

	}

	@Test
	public void searchPageURLTest() {

		String searchPageActURL = searchrespage.getSearchPageURL();
		System.out.println(searchPageActURL);
		Assert.assertTrue(searchPageActURL.contains(RESULTS_SEARCH_PAGE_FRACTIONAL_URL));

	}

	@Test
	public void searchPageTitleTest() {

		String searchPageActTitle = searchrespage.getSearchPageTittle();
		System.out.println(searchPageActTitle);
		Assert.assertTrue(searchPageActTitle.contains(RESULTS_SEARCH_PAGE_FRACTIONAL_TITLE));

	}

}
