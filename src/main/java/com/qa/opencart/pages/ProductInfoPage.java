package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.constants.AppConstants.MED_TIME_OUT;
import static com.qa.opencart.constants.AppConstants.PRODUCT_INFO_PAGE_FRACTIONAL_URL;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil elementUtil;
	Map<String, String> productmap;
	JavaScriptUtil jsUtil;
	Map<String, String> cartItemDetails;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//li");

	private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]//li");
	private By productPriceDetails = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]//li");

	private By addCartButton = By.id("button-cart");
	private By cartButton = By.xpath("//span[@id='cart-total']");
	private By removeItemFromCart = By.xpath("//button[@title='Remove']");
	private By removeItemsFromCart = By.xpath("//tr/td/button[@title='Remove']");

	private By successMessageProduct = By.xpath("(//div[contains(@class,'alert')]//a)[1]");
	private By successMessageCart = By.xpath("(//div[contains(@class,'alert')]//a)[2]");

	public String getProductInfoURL() {

		return elementUtil.waitforURLContains(PRODUCT_INFO_PAGE_FRACTIONAL_URL, DEFAULT_TIME_OUT);

	}

	public String getProductInfoTitle(String searchProduct) {

		return elementUtil.waitForTitleContains(searchProduct, DEFAULT_TIME_OUT);

	}

	public String getProductHeader() {
		return elementUtil.waitForElementPresence(productHeader, DEFAULT_TIME_OUT).getText();

	}

	public int getProductImageCount() {
		return elementUtil.waitForElementsVisibleBy(productImages, MED_TIME_OUT).size();

	}

	public Map<String, String> getProductDetails() {
		productmap = new LinkedHashMap<String, String>();
		productmap.put("ProductHeader", getProductHeader());
		productmap.put("ProductImageCount", Integer.toString(getProductImageCount()));
		getProductPriceDetails();
		getProductMetaDataDetails();
		System.out.println("Full Product Datails " + productmap);
		return productmap;

	}

	private void getProductPriceDetails() {

		List<WebElement> pricelist = elementUtil.waitForElementsVisibleBy(productPriceDetails, DEFAULT_TIME_OUT);

		String productprice = pricelist.get(0).getText();
		String productTax = pricelist.get(1).getText();
		String[] productTax_array = productTax.split(":");
		String productPriceWithTax = productTax_array[1].trim();

		productmap.put("Productprice", productprice);
		productmap.put("ProductPriceWithTax", productPriceWithTax);

	}

	private void getProductMetaDataDetails() {

		List<WebElement> metadata = elementUtil.waitForElementsVisibleBy(productMetaData, DEFAULT_TIME_OUT);

		for (WebElement ele : metadata) {

			String metadataString = ele.getText();
			String meta[] = metadataString.split(":");
			String metakey = meta[0].trim().replaceAll("\\s", "");
			String metavalue = meta[1].trim().replaceAll("\\s", "");

			productmap.put(metakey, metavalue);
		}

	}

	public void addItemToCart(boolean flag) {
		elementUtil.doClick(addCartButton);
		if (flag) {
			jsUtil.refreshBrowserByJS();
		}
	}

	public void removeItemsFromCartFun() {

		elementUtil.doClick(cartButton);
		List<WebElement> removeFromCart = null;

		try {
			removeFromCart = elementUtil.waitForElementsVisibleBy(removeItemsFromCart, DEFAULT_TIME_OUT);
		} catch (NoSuchElementException e) {

		}

		elementUtil.doClick(cartButton);

		for (int i = 0; i <= removeFromCart.size() - 1; i++) {

			elementUtil.clickWhenReady(cartButton, DEFAULT_TIME_OUT);

			elementUtil.clickWhenReady(removeItemFromCart, DEFAULT_TIME_OUT);

		}
	}

	private Map<String, String> getCartButtonDetails() {

		String cartButtonText = elementUtil.waitForElementVisible(cartButton, MAX_TIME_OUT).getText();
		String textstring[] = cartButtonText.split("-");
		String item_number = textstring[0].replace("item(s)", "").trim();
		String item_value = textstring[1].trim();

		cartItemDetails = new HashMap<String, String>();

		cartItemDetails.put("TotalItem", item_number);
		cartItemDetails.put("Total Value", item_value);

		return cartItemDetails;

	}

	public Map<String, String> getCartProductCountandPriceForItems() {

		cartItemDetails = getCartButtonDetails();

		removeItemsFromCartFun();
		return cartItemDetails;

	}

	public boolean getSuccessMessage(String productName) {

		String successText = elementUtil.waitForElementVisible(successMessageProduct, DEFAULT_TIME_OUT).getText();

		if (successText.contains(productName)) {
			return true;
		}
		return false;
	}

	public CartInfoPage checkShoppingCart() {

		elementUtil.waitForElementPresence(successMessageCart, DEFAULT_TIME_OUT).click();

		return new CartInfoPage(driver);

	}

}
