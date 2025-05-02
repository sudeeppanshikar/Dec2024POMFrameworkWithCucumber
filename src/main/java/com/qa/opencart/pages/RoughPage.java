package com.qa.opencart.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoughPage {

	public static void main(String[] args) throws InterruptedException {

		// ChromeOptions co = new ChromeOptions();

		// co.addArguments("--incognito");

		WebDriver driver = new ChromeDriver();

		List<WebElement> removeFromCart;
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=product/search&search=macbook%20Air");

		driver.manage().window().maximize();

		Thread.sleep(1000);

	//	List<WebElement> addcart = driver.findElements(By.xpath("//button[contains(@onclick,'cart.add')]"));
		
		WebElement addcart = driver.findElement(By.xpath("//button[contains(@onclick,'cart.add')]"));
		addcart.click();
		Thread.sleep(1000);
		addcart.click();
		Thread.sleep(1000);
		addcart.click();
		Thread.sleep(1000);
		/*
		 * for (WebElement add : addcart) { add.click(); Thread.sleep(2000); }
		 */

//driver.findElement(By.xpath("//span[@id='cart-total']")).click();

		driver.findElement(By.xpath("//span[@id='cart-total']")).click();

		removeFromCart = driver.findElements(By.xpath("//tr/td/button[@title='Remove']"));
		driver.findElement(By.xpath("//span[@id='cart-total']")).click();

		for (int i = 0; i <= removeFromCart.size() - 1; i++) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element1 = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='cart-total']")));
			element1.click();

			WebElement element2 = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='Remove']")));
			element2.click();

		}

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

}
