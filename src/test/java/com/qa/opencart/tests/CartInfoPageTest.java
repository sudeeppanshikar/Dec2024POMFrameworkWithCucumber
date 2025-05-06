package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
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

}
