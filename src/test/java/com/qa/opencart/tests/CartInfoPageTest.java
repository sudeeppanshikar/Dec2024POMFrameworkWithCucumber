package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;

@Epic("EPIC-03 - CarT page behaviour and Validations ")
public class CartInfoPageTest extends BaseTest {

	@BeforeTest

	public void cartInfoPageSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 2)
	public void checkoutButtonExistTest() {

		String productName = "MacBook Pro";
		searchrespage = accountpage.doSearch("macbook");
		prodinfopage = searchrespage.selectProduct(productName);
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();

		Assert.assertTrue(cartinfopage.checkoutButtonExist());

	}

	@DataProvider
	public Object[][] selectedProductGetAddedtestData() {
		return new Object[][] { { "macbook", "MacBook Pro" } };
	}

	@Test(dataProvider = "selectedProductGetAddedtestData")
	public void selectedProductGetAddedTest(String searchProduct, String productName) {

		searchrespage = accountpage.doSearch(searchProduct);
		prodinfopage = searchrespage.selectProduct(productName);
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();
		String actualProduct = cartinfopage.getProductsAddedToCart(productName);

		Assert.assertEquals(actualProduct, productName);

	}

	@DataProvider
	public Object[][] productAvailibilitytestData() {
		return new Object[][] { { "macbook", "MacBook" },
				/* { "macbook", "MacBook Pro"} */ };
	}

	@Test(dataProvider = "productAvailibilitytestData")
	public void productAvailibilityTest(String searchProduct, String productName) {

		searchrespage = accountpage.doSearch(searchProduct);
		prodinfopage = searchrespage.selectProduct(productName);
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();
		Boolean actualStatus = cartinfopage.getProductAvailibility(productName);
		Assert.assertTrue(actualStatus);

	}

	@DataProvider
	public Object[][] productQuantityUpdatedTestData() {
		return new Object[][] { { "macbook", "MacBook", "$1,204.00" }, { "macbook", "MacBook Pro", "$4,000.00" } };
	}

	@Test(priority = Integer.MAX_VALUE-1 ,dataProvider = "productQuantityUpdatedTestData")
	public void productQuantityUpdatedTest(String searchProduct, String productName, String excpectedPrice) {
		searchrespage = accountpage.doSearch(searchProduct);
		prodinfopage = searchrespage.selectProduct(productName);
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();

		String updatedPrice = cartinfopage.getProductQuantityUpdate(productName, "2");
		Assert.assertEquals(updatedPrice, excpectedPrice);

	}

	@DataProvider
	public Object[][] multipleproductQuantityUpdatedTestData() {
		return new Object[][] { { "macbook", "MacBook", "$1,204.00", "MacBook Pro", "$4,000.00" }, };
	}

	@Test(priority = Integer.MAX_VALUE,  dataProvider= "multipleproductQuantityUpdatedTestData")
	public void multipleProductQuantityUpdatedTest(String searchProduct, String productName1, String excpectedPrice1,
			String productName2, String excpectedPrice2) {
		searchrespage = accountpage.doSearch(searchProduct);
		prodinfopage = searchrespage.selectProduct(productName1);
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		searchrespage = accountpage.doSearch(searchProduct);
		prodinfopage = searchrespage.selectProduct(productName2);
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();

		String updatedPrice1 = cartinfopage.getProductQuantityUpdate(productName1, "2");
		String updatedPrice2 = cartinfopage.getProductQuantityUpdate(productName2, "2");

		SoftAssert softassert = new SoftAssert();

		softassert.assertEquals(updatedPrice1, excpectedPrice1);
		softassert.assertEquals(updatedPrice2, excpectedPrice2);

	}

}
