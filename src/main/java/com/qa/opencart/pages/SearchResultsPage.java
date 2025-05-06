package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIME_OUT;
import static com.qa.opencart.constants.AppConstants.MED_TIME_OUT;
import static com.qa.opencart.constants.AppConstants.RESULTS_SEARCH_PAGE_FRACTIONAL_URL;
import static com.qa.opencart.constants.AppConstants.*;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	WebDriver driver;
	ElementUtil elementUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	private final By productCount = By.cssSelector("div.product-thumb");


	public String getSearchPageTittle() {

		return elementUtil.waitForTitleContains(RESULTS_SEARCH_PAGE_FRACTIONAL_TITLE, DEFAULT_TIME_OUT);

	}

	public String getSearchPageURL() {

		return elementUtil.waitforURLContains(RESULTS_SEARCH_PAGE_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	public int getResultsProductCount() {

		List<WebElement> product = elementUtil.waitForElementsVisibleBy(productCount, DEFAULT_TIME_OUT);
		return product.size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product Name is " + productName);
		By selectProduct = By.linkText(productName);
		elementUtil.doClick(selectProduct);
		return new ProductInfoPage(driver);

		
		
	}



}
