package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	WebDriver driver;
	Actions actions;
	JavaScriptUtil jsutil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		jsutil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {

		WebElement element = driver.findElement(locator);
		elementhighlight(element);

		return element;
	}

	public void doClick(By locator) {

		getElement(locator).click();

	}

	public void elementhighlight(WebElement element) {
		if (DriverFactory.highlight.equals("true".trim())) {
			jsutil.flash(element);
		}
	}

	public String doGetTitle() {

		return driver.getTitle();

	}

	public String doGetURL() {

		return driver.getCurrentUrl();

	}

	public void doSendkeys(By locator, String values) {
		getElement(locator).clear();
		getElement(locator).sendKeys(values);

	}

	public String getlabel(By locator) {
		return getElement(locator).getText();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();

	}

	public String doGetDOMProperty(By locator, String property) {
		return getElement(locator).getDomProperty(property);

	}

	public String doGetDOMAttr(By locator, String attr) {
		return getElement(locator).getDomAttribute(attr);

	}

	public boolean doElementdisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException ex) {
			System.out.println("Exception Caught: Incorrect Locator or Element Not Found");
			return false;
		}

	}

	// ******Find Elements UTIL *******//
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public int getElementcount(By locator) {
		System.out.println("Element Count ==>" + getElements(locator).size());
		return getElements(locator).size();
	}
@Step("Locator is passed {0}")
	public List<String> getElementTextList(By locator) {
		List<WebElement> webele = getElements(locator);
		List<String> webEleText = new ArrayList<String>();

		for (WebElement ele : webele) {
			if ((ele.getText()).length() != 0)
				webEleText.add(ele.getText());
		}

		return webEleText;

	}

//****************************************************DropDown***************************************************//
	public boolean doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {

			System.out.println("Incorrect index found ," + index + " does not exist");
			return false;
		}
	}

	public boolean doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByValue(value);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Incorrect value passed  " + value + "  No such value exist");
			return false;
		}
	}

	public boolean doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByVisibleText(text);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Incorrcet text passed  " + text + " No such text exist");
			return false;
		}
	}

	public boolean selectDropDownValue(By locator, String value) {
		boolean flag = false;
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		for (WebElement ele : options) {
			if (ele.getText().equals(value)) {
				ele.click();
				System.out.println("Found   " + ele.getText());
				flag = true;
			}
		}

		if (flag) {
			System.out.println(value + "is Selected");
			return true;
		} else {
			System.out.println(value + "Not Found");
			return false;
		}

	}

	// **********************************************************DropDown with no//
	// Select****************************************//

	public boolean selectDropDownWithNoSelect(By locator, String value) {

		List<WebElement> countryList = getElements(locator);
		boolean flag = false;
		for (WebElement ele : countryList) {
			if (ele.getText().equals(value)) {
				ele.click();
				flag = true;
			}
		}

		if (flag) {
			System.out.println(value + "is Selected");
			return true;
		} else {
			System.out.println(value + "Not Found");
			return false;
		}

	}

	// *********get list of option in drop down*********//
	public List<String> getDropDownValue(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> dropDownElementlist = select.getOptions();
		List<String> optionValList = new ArrayList<String>();
		for (WebElement e : dropDownElementlist) {
			optionValList.add(e.getText());
		}

		return optionValList;
	}

//******** Comparing the values to select ***************************//

	public boolean getDropDownValue(By locator, List<String> compareValueList) {

		Select select = new Select(getElement(locator));
		List<WebElement> dropDownWebElements = select.getOptions();
		List<String> dropDownValueList = new ArrayList<String>();
		for (WebElement e : dropDownWebElements) {
			dropDownValueList.add(e.getText().trim());
		}
		if (dropDownValueList.containsAll(compareValueList)) {
			return true;
		} else {
			return false;
		}

	}

	// ********************************************dropdownselection for generic
	// drop down , no Select tag required *********//

	public boolean selectChoiceWithNoSelect(By choicebox, By choicelist, String... choice) {

		doClick(choicebox);
		List<WebElement> choicedropdown = getElements(choicelist);
		boolean flag = false;
		List<String> choicedropdownvalue = new ArrayList<String>();
		for (WebElement ele : choicedropdown) {
			choicedropdownvalue.add(ele.getText().trim());
		}

		List<String> choicetolist = Arrays.asList(choice);
		if (!choicedropdownvalue.containsAll(choicetolist)) {
			if (choice[0].toLowerCase().equals("all"))
				for (WebElement ele : choicedropdown) {
					ele.click();
					flag = true;
				}
			if (flag) {
				return flag;
			} else {
				return flag;
			}

		} else {

			for (WebElement ele : choicedropdown) {

				flag = true;
				for (String ch : choice) {
					if (ele.getText().trim().equals(ch)) {
						ele.click();
						break;
					}
				}

			}

		}

		return flag;
	}

	// ***************************Action
	// Class*******************************************************************//

	public void handleParentSubmenu(By parentloc, By childloc) {

		actions.moveToElement(getElement(parentloc)).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(childloc)).click();

	}

	public void doMovetoElement(By locator) {

		actions.moveToElement(getElement(locator)).build().perform();

	}

	public void doActionClick(By locator) {

		actions.click(getElement(locator)).build().perform();

	}

	public void doActionSendKeys(By locator, String value) {
		actions.sendKeys(getElement(locator), value).build().perform();

	}

	// *********************************Explicit wait
	// utility***************************//

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsVisibleBy(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {

		}
		return Collections.EMPTY_LIST;
	}

	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		elementhighlight(element);
		return element;

	}

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		elementhighlight(element);
		return element;
	}

	public void clickWithWait(By locator, int timeout) {

		waitForElementVisible(locator, timeout).click();

	}

	public void waitWithSendKeys(By locator, int timeout, String value) {

		waitForElementVisible(locator, timeout).sendKeys(value);

	}

	// *****Wait for JS POPUP Alert ***//

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForAccepAlert(int timeout) {
		waitForAlert(timeout).accept();

	}

	public void waitForDismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();

	}

	public String waitForGettextAlert(int timeout) {
		return waitForAlert(timeout).getText();

	}

	public void waitForSendKeysAlert(int timeout, String value) {
		waitForAlert(timeout).sendKeys(value);

	}

	// **********waitfor title ***********************//

	public String waitForTitleContains(String partialTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(partialTitle));
			return driver.getTitle();

		} catch (TimeoutException e) {
			return "Not found";

		}

	}

	public String waitForTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();

		} catch (TimeoutException e) {
			return "Not found";

		}

	}

	// **********waitfor URL ***********************//

	public String waitfoURL(String url, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.urlToBe(url));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

	public String waitforURLContains(String fractionalurl, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlContains(fractionalurl));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;

		}
	}

	// **************************waitforframe***********************//

	public void waitForFrame(By framelocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

	}

	public void waitForFrame(int frameindex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));

	}

	public void waitForFrame(String frameIDorName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDorName));

	}

	public void waitForFrame(WebElement frameElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public Boolean isElementVisibleWithText(By locator, int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));

	}

	// **************FluentWait***********************//

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("====Elementnotfound====");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("====Elementnotfound====");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

}
