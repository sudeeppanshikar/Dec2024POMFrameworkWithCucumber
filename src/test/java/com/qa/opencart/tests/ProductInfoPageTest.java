package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	// String productSearch;

	@BeforeClass
	public void productInfoPageSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "macbook", "MacBook" }, { "macbook", "MacBook Pro" },
				{ "samsung", "Samsung SyncMaster 941BW" }

		};

	}

	@Test(priority = Integer.MIN_VALUE, dataProvider = "getProductTestData")
	public void productInfoURLTest(String searchValue, String productName) {

		searchrespage = accountpage.doSearch(searchValue);

		prodinfopage = searchrespage.selectProduct(productName);

		String prodInfoPageURL = prodinfopage.getProductInfoURL();

		Assert.assertTrue(prodInfoPageURL.contains(PRODUCT_INFO_PAGE_FRACTIONAL_URL));

	}

	@Test(dataProvider = "getProductTestData")
	public void productnfoTitleTest(String searchValue, String productName) {

		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName);
		String productInfoPageTitle = prodinfopage.getProductInfoTitle(productName);
		System.out.println("Title for Product Info Page is :  " + productInfoPageTitle);
		Assert.assertEquals(productInfoPageTitle, productName);

	}

	@Test(dataProvider = "getProductTestData")
	public void productInfoHeaderTest(String searchValue, String productName) {
		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName);

		String prodInfoPageheaderAct = prodinfopage.getProductHeader();
		System.out.println("Product header in Product Info Page : " + prodInfoPageheaderAct);
		Assert.assertEquals(prodInfoPageheaderAct, productName);

	}

	@DataProvider
	public Object[][] getProductImageCountTestData() {
		return new Object[][] { { "macbook", "MacBook", 5 }, { "macbook", "MacBook Pro", 4 },
				{ "samsung", "Samsung SyncMaster 941BW", 1 }

		};

	}

	@Test(dataProvider = "getProductImageCountTestData")
	public void productInfoImageTest(String searchValue, String productName, int expectedImageCount) {
		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName);
		int imageCount = prodinfopage.getProductImageCount();
		Assert.assertEquals(imageCount, expectedImageCount);

	}

	@DataProvider
	public Object[][] getProductInfoTestData() {
		return new Object[][] { { "macbook", "MacBook", "5", "$602.00", "$500.00", "Apple", "Product16", "600" } };

	}

	@Test(dataProvider = "getProductInfoTestData")
	public void getProductFullDeatilsTest(String searchValue, String productHeader, String expectedImageCount,
			String expectedproductPrice, String expectedproductPriceWithTax, String expectedbrand,
			String expectedproductCode, String expectedrewardPoints) {
		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productHeader);
		Map<String, String> actualproductdetails = prodinfopage.getProductDetails();

		SoftAssert softassert = new SoftAssert();

		softassert.assertEquals(actualproductdetails.get("ProductHeader"), productHeader);
		softassert.assertEquals(actualproductdetails.get("ProductImageCount"), expectedImageCount);
		softassert.assertEquals(actualproductdetails.get("Productprice"), expectedproductPrice);
		softassert.assertEquals(actualproductdetails.get("ProductPriceWithTax"), expectedproductPriceWithTax);

		softassert.assertEquals(actualproductdetails.get("Brand"), expectedbrand);
		softassert.assertEquals(actualproductdetails.get("ProductCode"), expectedproductCode);
		softassert.assertEquals(actualproductdetails.get("RewardPoints"), expectedrewardPoints);

		softassert.assertAll();

	}

	@DataProvider
	public Object[][] getCartTestData() {
		return new Object[][] { { "macbook", "MacBook Pro", "$2,000.00" } };
	}

	@Test(dataProvider = "getCartTestData", priority = Integer.MAX_VALUE - 1)
	public void cartButtonDetailsItemTest(String searchValue, String productName, String price) {
		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName);
		prodinfopage.addItemToCart(true);
		Map<String, String> actualItemsInCart = prodinfopage.getCartProductCountandPriceForItems();
		System.out.println(actualItemsInCart);
		System.out.println(actualItemsInCart.get("Total Value"));
		SoftAssert softassert = new SoftAssert();

		softassert.assertEquals(actualItemsInCart.get("Total Value"), price);
		softassert.assertAll();

	}

	@DataProvider
	public Object[][] getCartTestDataForMulitpletems() {
		return new Object[][] { { "macbook", "MacBook Pro", "MacBook Air", "$3,202.00" } };
	}

	@Test(priority = Integer.MAX_VALUE - 1, dataProvider = "getCartTestDataForMulitpletems")
	public void cartButtonDetailsMultipleItemTest(String searchValue, String productName1, String productName2,
			String Price) {

		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName1);
		prodinfopage.addItemToCart(true);
		searchrespage = accountpage.doSearch(searchValue);
		prodinfopage = searchrespage.selectProduct(productName2);
		prodinfopage.addItemToCart(true);

		Map<String, String> actualItemsInCart = prodinfopage.getCartProductCountandPriceForItems();
		System.out.println(actualItemsInCart);
		System.out.println(actualItemsInCart.get("Total Value"));
		SoftAssert softassert = new SoftAssert();

		softassert.assertEquals(actualItemsInCart.get("Total Value"), Price);
		softassert.assertAll();

	}

	@Test(priority = Integer.MAX_VALUE - 2)
	public void addToCartSuccessMessageTest() {
		searchrespage = accountpage.doSearch("macbook");
		prodinfopage = searchrespage.selectProduct("MacBook Pro");
		prodinfopage.addItemToCart(false);

		Assert.assertTrue(prodinfopage.getSuccessMessage("MacBook Pro"));
		prodinfopage.removeItemsFromCartFun();

	}

	@Test(priority = Integer.MAX_VALUE)
	//new addition
	public void cartInfoPageURLTest() {

		searchrespage = accountpage.doSearch("macbook");
		prodinfopage = searchrespage.selectProduct("MacBook Pro");
		prodinfopage.removeItemsFromCartFun();
		prodinfopage.addItemToCart(false);
		cartinfopage = prodinfopage.navigateShoppingCartPage();
		String cartInfoPageTitle = cartinfopage.getCartInfoPageURL();
		System.out.println(cartInfoPageTitle);
		Assert.assertTrue(cartInfoPageTitle.contains(CART_INFO_FRACTIONAL_URL));
		prodinfopage.removeItemsFromCartFun();

	}

}
