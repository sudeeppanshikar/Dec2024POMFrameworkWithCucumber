public class PaymentPage {

	WebDriver driver;
	ElementUtil elementUtil;
	Map<String, String> productmap;
	JavaScriptUtil jsUtil;
	Map<String, String> cartItemDetails;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

}
