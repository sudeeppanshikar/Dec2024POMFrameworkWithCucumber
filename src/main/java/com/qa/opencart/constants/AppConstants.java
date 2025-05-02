package com.qa.opencart.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String HOME_PAGE_TITLE = "My Account";
	public final static String RESULTS_SEARCH_PAGE_FRACTIONAL_TITLE = "Search";

	public final static int DEFAULT_TIME_OUT = 5;
	public final static int MED_TIME_OUT = 10;
	public final static int MAX_TIME_OUT = 15;

	public final static String LOGIN_PAGE_FRACTIONAL_URL = "?route=account/login";
	public static final String HOME_PAGE_FRACTIONAL_URL = "?route=account/account";
	public static final String RESULTS_SEARCH_PAGE_FRACTIONAL_URL = "?route=product/search";

	public static final String PRODUCT_INFO_PAGE_FRACTIONAL_URL = "?route=product/product";

	public static final List<String> expected_account_page_header = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	
	public static final String  SUCCESSFUL_USERCREATION_MESSAGE =  "Your Account Has Been Created!"; 
	public static final String  EMAIL_FORMAT_VALIDATION_MESSAGE = "E-Mail Address does not appear to be valid!";
	
	public static final String  EMAIL_FORMAT_VALIDATION_MESSAGE_MISSING	= "Please include an '@'";
}
