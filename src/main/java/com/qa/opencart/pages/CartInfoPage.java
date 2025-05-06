package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class CartInfoPage {

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;

	public CartInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	private By checkoutButton = By.linkText("Checkout");

	public String getCartInfoPageURL() {

		return elementUtil.waitforURLContains(CART_INFO_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	public boolean checkoutButtonExist() {

		return elementUtil.doElementdisplayed(checkoutButton);
		
	}
	
	public void getTotalPrice() {
		
	}

}
