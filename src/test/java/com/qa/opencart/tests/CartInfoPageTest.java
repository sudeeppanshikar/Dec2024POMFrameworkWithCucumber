package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
			{ "macbook", "MacBook Pro"}	};
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
	

}
