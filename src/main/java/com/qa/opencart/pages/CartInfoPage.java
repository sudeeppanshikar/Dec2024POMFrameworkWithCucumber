package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.CART_INFO_FRACTIONAL_URL;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

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

	private By availibility = By.xpath("//span[@class='text-danger']");

	public String getCartInfoPageURL() {

		return elementUtil.waitforURLContains(CART_INFO_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	public boolean checkoutButtonExist() {

		return elementUtil.doElementdisplayed(checkoutButton);

	}

	public String getProductsAddedToCart(String productName) {

		By product = By.linkText(productName);
		return elementUtil.getElement(product).getText();

	}

	public boolean getProductAvailibility(String productName) {

		By availibilitySign = By.xpath("(//a[text()='" + productName + "'])[2]//parent::td/span");

		try {
			elementUtil.getElement(availibilitySign).getText();
			return false;

		} catch (NoSuchElementException e) {

		}

		return true;

	}

	public String getProductQuantityUpdate(String productName, String value) {

		By quantityBox = By.xpath(
				"((//a[text()='" + productName + "'])[2]//parent::td//following-sibling::td)[2]//input[@type='text']");
		By totalPrice = By.xpath("((//a[text()='" + productName + "'])[2]//parent::td//following-sibling::td)[4]");
		By refreshButton = By.xpath(
				"(//a[text()='" + productName + "'])[2]//parent::td//following-sibling::td//button[@type='submit']");
		By quantityUpdateSuccessMessage = By.xpath("//div[contains(@class,'alert-success')]");

		WebElement quantityBox_Ele = elementUtil.waitForElementVisible(quantityBox, DEFAULT_TIME_OUT);

		quantityBox_Ele.clear();
		quantityBox_Ele.sendKeys(value);

		elementUtil.doClick(refreshButton);

		/*
		 * String successMessage =
		 * elementUtil.waitForElementPresence(quantityUpdateSuccessMessage,
		 * MED_TIME_OUT) .getText();
		 */
		/*
		 * if (successMessage.contains("Success")) {
		 * 
		 * return elementUtil.waitForElementVisible(totalPrice,
		 * DEFAULT_TIME_OUT).getText(); }
		 */

		 return elementUtil.waitForElementVisible(totalPrice, DEFAULT_TIME_OUT).getText();
	}

}
