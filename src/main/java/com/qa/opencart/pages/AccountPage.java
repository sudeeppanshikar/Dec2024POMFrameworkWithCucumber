package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIME_OUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTIONAL_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	WebDriver driver;
	ElementUtil elementUtil;

	private final By header = By.xpath("//div[@id='content']//h2");
	private final By search = By.name("search");
	private final By searchIcon= By.xpath("//div[@id='search']//button");
	

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getaccountpageTittle() {

		return elementUtil.waitForTitle(HOME_PAGE_TITLE, DEFAULT_TIME_OUT);

	}

	public String getaccountpageURL() {

		return elementUtil.waitforURLContains(HOME_PAGE_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	
	public List<String> getAccountPageHeader() {
		List <String> actual_page_header =   elementUtil.getElementTextList(header);
		System.out.println(actual_page_header);
		return actual_page_header;
		
	}
	
	public  SearchResultsPage doSearch(String searchKey) {
		
		elementUtil.doSendkeys(search,searchKey);
		elementUtil.doClick(searchIcon);
		
		return new SearchResultsPage (driver);
	}
	
	
	
}
