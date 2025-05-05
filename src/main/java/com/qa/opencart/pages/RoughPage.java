package com.qa.opencart.pages;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoughPage {

	static	WebDriver driver;
	static JavascriptExecutor js;
	static WebElement ele;
	
	public static void main(String[] args) throws InterruptedException {

		// ChromeOptions co = new ChromeOptions();

		
		System.out.println(generateRandomEmailid());
		
		
		// co.addArguments("--incognito");
		/*
		 * driver = new ChromeDriver(); js = (JavascriptExecutor) driver;
		 */
		 
		 
	//	List<WebElement> removeFromCart;
	/*
	 * driver.get(
	 * "https://naveenautomationlabs.com/opencart/index.php?route=product/product&product_id=45&search=macbook"
	 * );
	 * 
	 * driver.manage().window().maximize();
	 * 
	 * Thread.sleep(1000); driver.findElement(By.id("button-cart")).click();
	 * Thread.sleep(1000);
	 */
		 
			
		// ele = driver.findElement(By.name("search"));
		 
	//	ele = driver.findElement(By.xpath("//span[@id='cart-total']")); 

	//	ele.click();
		
	/*
	 * WebElement ele =
	 * driver.findElement(By.xpath("(//div[contains(@class,'alert')]//a)[1]"));
	 * flash(ele);
	 * 
	 * System.out.println(driver.findElement(By.xpath(
	 * "//div[contains(@class,'alert')]")).getText());
	 */
		
		//Actions action = new Actions(driver);	
		
	//	action.moveToElement(check).build().perform();
		
		
		
		
		

	//	List<WebElement> addcart = driver.findElements(By.xpath("//button[contains(@onclick,'cart.add')]"));
		
	/*
	 * WebElement addcart =
	 * driver.findElement(By.xpath("//button[contains(@onclick,'cart.add')]"));
	 * addcart.click(); Thread.sleep(1000); addcart.click(); Thread.sleep(1000);
	 * addcart.click(); Thread.sleep(1000);
	 */
		/*
		 * for (WebElement add : addcart) { add.click(); Thread.sleep(2000); }
		 */

//driver.findElement(By.xpath("//span[@id='cart-total']")).click();

		/*
		 * driver.findElement(By.xpath("//span[@id='cart-total']")).click();
		 * 
		 * removeFromCart =
		 * driver.findElements(By.xpath("//tr/td/button[@title='Remove']"));
		 * driver.findElement(By.xpath("//span[@id='cart-total']")).click();
		 * 
		 * for (int i = 0; i <= removeFromCart.size() - 1; i++) {
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * WebElement element1 = wait
		 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//span[@id='cart-total']"))); element1.click();
		 * 
		 * WebElement element2 = wait
		 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//button[@title='Remove']"))); element2.click();
		 * 
		 * }
		 */
		/*
		 * for (WebElement remove : removeFromCart) {
		 * driver.findElement(By.xpath("//span[@id='cart-total']")).click();
		 * remove.click(); Thread.sleep(3000); removeFromCart =
		 * driver.findElements(By.xpath("//tr/td/button[@title='Remove']"));
		 * 
		 * }
		 */

		// driver.manage().window().maximize();

		/*
		 * driver.findElement(By.xpath(
		 * "//label[@class='radio-inline']//input[@value='1']")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath(
		 * "//label[@class='radio-inline']//input[@value='0']")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath(
		 * "//label[@class='radio-inline']//input[@value='1']")).click();
		 */

		// By successMessg = By.cssSelector("div#content h1");

		/*
		 * By successMessg = By.xpath("//div[@id='content']/h1");
		 * 
		 * String text = driver.findElement(successMessg).getText();
		 */

		// driver.findElement(By.id("input-email")).sendKeys("asasaa");
		// driver.findElement(By.xpath("//input[@type='submit']")).click();

		// By cartbutton = By.xpath("//span[@id='cart-total']");

		// By message = By.id("input-email");

		// driver.findElement(cartbutton).click();

		// String text = ele.getText();

		// System.out.println(text);

		// 0 item(s) - $0.00

//		String text = "0 item(s) - $0.00";
//		
//		String textstring[] = text.split("-");
//		String item_number =textstring[0].replace("item(s)", "").trim();
//		String item_value = textstring[1].trim();
//		
//		System.out.println(Arrays.toString(textstring));
//		System.out.println(item_number);
//		System.out.println(item_value);
//		

	}
	
	/*
	 * public static void flash(WebElement element) { String bgcolor =
	 * element.getCssValue("backgroundColor");// blue for (int i = 0; i < 6; i++) {
	 * changeColor("rgb(0,400,0)", element);// green changeColor(bgcolor,
	 * element);// blue } }
	 * 
	 * private static void changeColor(String color, WebElement element) {
	 * js.executeScript("arguments[0].style.backgroundColor = '" + color + "'",
	 * element);// GBGBGBGBGBGB
	 * 
	 * try { Thread.sleep(10); } catch (InterruptedException e) { } }
	 */
	
	
	public static String generateRandomEmailid() {
		  LocalTime now = LocalTime.now();
				  
		  String randomEmailId = now+"@gmail.com";
				  randomEmailId = 	randomEmailId.replace(":", "");
				  return randomEmailId;
	}


}
