package com.qa.openkart.utils;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openkart.exceptions.ElementException;
import com.qa.openkart.factory.DriverFactory;

import io.qameta.allure.Step;



public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	private final String DEFAULT_ELEMENT_TIMEOUT_MESSAGE = "ELEMENT TIME OUT";
	private final String DEFAULT_ALERT_TIMEOUT_MESSAGE = "ALERT TIME OUT";
	
	
	
	private void nullCheck(String value) {
		if (value == null || value.length() == 0) {
			throw new ElementException(value+" visible text can not be null");
		}
	}

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);

	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType.toLowerCase().trim()) {

		case "id":
			locator = By.id(locatorValue);
			break;

		case "xpath":
			locator = By.xpath(locatorValue);

		case "name":
			locator = By.name(locatorValue);

		case "classname":
			locator = By.className(locatorValue);

		case "css":
			locator = By.cssSelector(locatorValue);

		case "linkText":
			locator = By.linkText(locatorValue);

		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);

		case "tagname":
			locator = By.tagName(locatorValue);

		default:
			break;
		}
		return locator;
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public void checkHighlight(WebElement ele) {
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			
			jsUtil.flash(ele);	
			}
	}
	
	public void checkHighlight(By locator) {
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			
			jsUtil.flash(driver.findElement(locator));	
			}
	}
	
	@Step("getting web element using locator {0}")
	private WebElement getElement(By locator) {
		WebElement element = null;
		
		try {			
			element = driver.findElement(locator);
			checkHighlight(locator);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public boolean isElementSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public boolean isElementEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public ArrayList<String> getElementsTextList(By locator) {
		List<WebElement> elementsList = getElements(locator);
		ArrayList<String> elementText = new ArrayList<String>();
		for (WebElement z : elementsList) {
			String s = z.getText();

			if (s.length() != 0) {
				elementText.add(s);
			}
		}
		return elementText;
	}

	public ArrayList<String> getElementAtrributeList(By locator, String attributeName) {
		List<WebElement> elementsList = getElements(locator);
		ArrayList<String> elementAttribute = new ArrayList<String>();
		for (WebElement z : elementsList) {
			String s = z.getAttribute(attributeName);
			if (s.length() != 0) {
				elementAttribute.add(s);
			}
		}
		return elementAttribute;
	}
	@Step(" checking element {0} is displayed... ")
	public boolean isElementDisplayed(By locator) {
		boolean b;
		try {
			b = getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
		return b;
	}

	public boolean isElementExist(By locator) {
		if (getElements(locator).size() == 1) {
			return true;
		}
		return false;

	}

	public boolean multipleElementExist(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;

	}

	public String getElementAttribute(By locator, String attName) {
		return driver.findElement(locator).getAttribute(attName);
	}

	@Step("entering value : {1} || in element :{0}")
	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		nullCheck(value);
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	@Step("clicking on element using locator : {0}")
	public void doclick(By locator) {
		getElement(locator).click();
	}

	public String getElementText(By locator) {
		return getElement(locator).getText();
	}

	public String getElementText(String locatorType, String value) {
		return getElement(locatorType, value).getText();
	}

	/////////////////////////////// Select based drop down utils \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		nullCheck(value);
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doSelectByVisibleText(By locator, String value) {
		nullCheck(value);
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}
	
	public void doSelectDropDownValue(By locator, String value) {
		nullCheck(value);
		Select select = new Select(getElement(locator));
		List<WebElement> option = select.getOptions();

		for (WebElement z : option) {
			String text = z.getText();
			System.out.println(text);
			if (text.equals(value)) {
				z.click();
				break;
			}
		}
	}
	
	public void doSearch(By locatorSearchBar,By locatorClick,String searchParameter , String clickParameter) throws InterruptedException {
		doSendKeys(locatorSearchBar, searchParameter);
		Thread.sleep(3000);
		
		List<WebElement> elements = getElements(locatorClick);
		for(WebElement e : elements) {
			String s =e.getText();
			System.out.println(s);
			if(s.contains(clickParameter)){
				e.click();
				break;
			}
		}
	}
	/**
	 * 
	 * @param drobDownLocator
	 * @param choices
	 * @param value (... is a varargs -- spread parameter(JS)-- array 
	 * @throws InterruptedException
	 */
	public void doSelectChoice (By drobDownLocator, By choices, String... value) throws InterruptedException {
		
		doclick(drobDownLocator);
		Thread.sleep(0);
		
		List<WebElement> elements = getElements(choices);
		if	(!value[0].equals("all")) {	
		for(WebElement z : elements) {
			String s=z.getText();
			if (!s.equals("")) {
				System.out.println(s);
			}
			for(String x : value) {
				if(s.equals(x)) {
					z.click();
					
				}
			}
		}}else {
			for(WebElement z:elements) {
				try{z.click();
				}catch(Exception e) {
					break;
				}
			}
		}
	}
		///////////////////Table related\\\\\\\\\\\\\\\\\\\\\
		public int totalRow(By locator) {
			return getElements(locator).size();
		}
		
		public void firstColumnValue(By totalColumn) {
			int totalRows= totalRow(totalColumn);
			String by ="//*[@class='ws-table-all']//tr[";
			String by2 = "]/td[1]";
			
			for(int i =2 ; i<=totalRows ; i++) {
				String z = by+i+by2;
				String text = getElement(By.xpath(z)).getText();
				System.out.println(text);
			}
			
		}
		
		public void secondColumnValue(By totalColumn) {
			int totalRows= totalRow(totalColumn);
			String by ="//*[@class='ws-table-all']//tr[";
			String by2 = "]/td[2]";
			
			for(int i =2 ; i<=totalRows ; i++) {
				String z = by+i+by2;
				String text = getElement(By.xpath(z)).getText();
				System.out.println(text);
			}
			
		}
		
		public void thirdColumnValue(By totalColumn) {
			int totalRows= totalRow(totalColumn);
			String by ="//*[@class='ws-table-all']//tr[";
			String by2 = "]/td[3]";
			
			for(int i =2 ; i<=totalRows ; i++) {
				String z = by+i+by2;
				String text = getElement(By.xpath(z)).getText();
				System.out.println(text);
			}
			
		}
		
		///////////////////////Actions class\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		public void handelMenuSubMenuLevel2(By parentLocator, By subMenuLocator) throws InterruptedException {
			Actions a = new Actions(driver);
			a.moveToElement(getElement(parentLocator)).build().perform();;
			Thread.sleep(1500);
			getElement(subMenuLocator).click();
			
		}
		
		public void handelMenuSubMenuLevel4(By main ,By parentLocator, By subMenuLocator, By nextLocator) throws InterruptedException {
			getElement(main).click();
			Thread.sleep(500);
			Actions a = new Actions(driver);
			a.moveToElement(getElement(parentLocator)).build().perform();;
			Thread.sleep(500);
			a.moveToElement(getElement(subMenuLocator)).build().perform();;
			Thread.sleep(500);
			getElement(nextLocator).click();
			
		}
		
		public void doActionClick(By locator) {
			Actions a = new Actions(driver);
			a.click(getElement(locator)).perform();;
			
		}
		
		public void doActionSendKeys(By locator, String value) {
			Actions a = new Actions(driver);
			a.sendKeys(getElement(locator),value).perform();
		}
		public void p() {
			Actions a = new Actions(driver);
			a.pause(500).perform();;
		}
		public void doScrollToElement(By locator) {
			Actions a = new Actions(driver);
			a.scrollToElement(driver.findElement(locator));
			
		}
		
		
		/////////////////Wait\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		/**
		   * An expectation for checking that an element is present on the DOM of a page. This does not
		   * necessarily mean that the element is visible.
		   */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		/**
		   * An expectation for checking that an element is present on the DOM of a page and visible.
		   * Visibility means that the element is not only displayed but also has a height and width that is
		   *  greater than 0.
		   */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			getElement(locator);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		public WebElement fluentWaitForElementPresence(By locator, int timeOut, int pollingTime) {
			Wait <WebDriver>wait = new 	FluentWait<WebDriver>(driver)
													.withMessage(DEFAULT_ELEMENT_TIMEOUT_MESSAGE)
													.withTimeout(Duration.ofSeconds(timeOut))
													.pollingEvery(Duration.ofSeconds(pollingTime))													
													.ignoring(NoSuchElementException.class)
													.ignoring(NoClassDefFoundError.class);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		
		public List<WebElement> waitForAllElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
		}
		public List<WebElement> waitForAllElementsVisibility(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
		}
		public void doSendKeys(By locator, String value, int timeOut) {
			nullCheck(value);
			waitForElementVisible(locator, timeOut).sendKeys(value);
			
		}
		
		public void doClick(By locator, int timeOut) {
			waitForElementVisible(locator, timeOut).click();
		}
		public void waitThenClick(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			
		}
		
			
			
		

		
		/////////////Waits for Title and Url\\\\\\\\\\\\\\
		
		public String waitForTitleContains(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
					return driver.getTitle();
				}
			} catch (Exception e) {
				System.out.println("Not found");
			}
			return null;
		}
		
		@Step("waiting for the expected title...")
		public String waitForTitleIs(String title, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.titleIs(title))) {
					return driver.getTitle();
				}

			} catch (Exception e) {
				System.out.println("Not found");
			}

			return driver.getTitle();
		}
		
		public String waitForUrlContains(String urlContains, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.urlContains(urlContains))) {
					return driver.getCurrentUrl();
				}

			} catch (Exception e) {
				System.out.println("Not found");
			}

			return driver.getCurrentUrl();
		}
		public String waitForUrlToBe(String url, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.urlToBe(url))) {
					return driver.getCurrentUrl();
				}

			} catch (Exception e) {
				System.out.println("Not found");
			}

			return driver.getCurrentUrl();
		}
		
			/////////////Waits for Alert\\\\\\\\\\\\\\
		
		public Alert waitForAlert(int timeOut) {			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());						
		}
		public Alert fluentWaitForAlert(int timeOut, int pollingTime) {		
		Wait <WebDriver>wait = new 	FluentWait<WebDriver>(driver)
												.withMessage(DEFAULT_ALERT_TIMEOUT_MESSAGE)
												.withTimeout(Duration.ofSeconds(timeOut))
												.pollingEvery(Duration.ofSeconds(pollingTime))													
												.ignoring(NoSuchElementException.class)
												.ignoring(NoClassDefFoundError.class);
		return wait.until(ExpectedConditions.alertIsPresent());						
		}
		public String waitForAlertText(int timeout) {
			return waitForAlert(timeout).getText();
		}
		public void waitForAlertAccept(int timeout) {
			 waitForAlert(timeout).accept();
		}
		public void waitForAlertDismiss(int timeout) {
			 waitForAlert(timeout).dismiss();
		}
		public void waitForAlertSendKeys(String text,int timeout) {
			 waitForAlert(timeout).sendKeys(text);
		}
		
			//////////////////Waits for Window\\\\\\\\\\\\\\
		
		public boolean waitForWindow(int totalNumberOfWindowsToBe, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.numberOfWindowsToBe(totalNumberOfWindowsToBe));			
		}
		
		//////////////////////Waits for iframe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		public void waitForFrameAndSwitchToIt(int index, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}
		public void waitForFrameAndSwitchToIt(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
		public void waitForFrameAndSwitchToIt(WebElement element, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		}
		
		/////////////Page\\\\\\\\\\\\\\\\\
		public boolean isPageLoaded(int timeOut) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState ==='complete'")).toString();
			return Boolean.parseBoolean(flag);
		}
		
		/////////////////////////CUSTOM WAIT\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		public  WebElement retryingElement(By locator, int timeOut) {
			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element found in : " + attempts + " attempts");
					break;
				} catch (NoSuchElementException e) {
					try {
						System.out.println("element still not found in : " + attempts + " attempts");
						Thread.sleep(1000);
					} catch (InterruptedException f) {
						
					}
				}
				attempts++;
			}
			if (element == null) {
				throw new ElementException("No Such Element");
			} else
				return element;
		}
		
		public  WebElement retryingElement(By locator, int timeOut , int pollingTime) {
			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element found in : " + attempts + " attempts");
					break;
				} catch (NoSuchElementException e) {
					try {
						System.out.println("element still not found in : " + attempts + " attempts");
						Thread.sleep(pollingTime);
					} catch (InterruptedException f) {
						
					}
				}
				attempts++;
			}
			if (element == null) {
				throw new ElementException("No Such Element");
			} else
				return element;
		}
		///////////////////////////////Pheko Concept\\\\\\\\\\\\\\\\\\
		
		public void printTextFormWebelementS(By locator) {
			List<WebElement> elements = getElements(locator);
			elements.stream().forEach(e -> System.out.println(e.getText()));
		}
		public List<String> getWeblementsList(By locator) {
			List<WebElement> elements = getElements(locator);
			return elements.stream().map(e -> e.getText()).collect(Collectors.toList());
		}
		
		
		
		
	
	
	
}
