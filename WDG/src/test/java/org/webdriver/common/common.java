package org.webdriver.common;

import java.io.FileDescriptor;  
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.stjude.srm2qa.util.TestProperties;
import org.testng.Assert;

public class common {
	private WebDriver webDriver;
	private PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	public common(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
//	/**
//	 * will login using test credentials
//	 * @param webDriver
//	 * @throws Exception
//	 */
//	public void login(WebDriver webDriver) throws Exception {
//		webDriver.manage().window().maximize();
//		WebElement userNameElement = patientlyFindElement(By.xpath("//input[@id='username']"));
//		userNameElement.sendKeys(userName);
//		
//		WebElement passwordElement = patientlyFindElement(By.xpath("//input[@id='password']"));
//		passwordElement.sendKeys(password);
//		
//		patientlyFindElement(By.name("submit")).click();
//		patientlyFindElement(By.cssSelector("BODY"));
//	}
	
	/**
	 * Will try to find the WebElement within 30 seconds.
	 * If the WebElement is not found within 30 seconds, a
	 * TimeoutException will be thrown. Once found thread will 
	 * sleep for milliseconds specified in variable 'timeToWaitAfterElementFound'
	 * to give some time for browser to bind event listeners to the element (if there
	 * happened to be one)
	 * 
	 * @param by The locating mechanism
	 * @return The first matching element on the current page
	 * @throws TimeoutException If no matching elements are found
	 */
	
	public WebElement patientlyFindElement(final By by) {
		return patientlyFindElement(by, 30);
	}
	
	/**
	 * Will try to find the WebElement within seconds specified in 'waitFor'.
	 * If the WebElement is not found within 'waitFor' seconds, a
	 * TimeoutException will be thrown. Once found thread will 
	 * sleep for milliseconds specified in variable 'timeToWaitAfterElementFound'
	 * to give some time for browser to bind event listeners to the element (if there
	 * happened to be one)
	 * 
	 * @param by The locating mechanism
	 * @return The first matching element on the current page
	 * @throws TimeoutException If no matching elements are found
	 */
	
	public WebElement patientlyFindElement(final By by, int waitFor) {
		WebDriverWait wait = new WebDriverWait(webDriver, waitFor);
		return wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver input) {
				stdout.println("Waiting for: " + by.toString());
				WebElement element = webDriver.findElement(by);
				stdout.println("Found: " + by.toString());
				return element;
			}
		});
	}
	
	/**
	 * Checking an element is visible and enabled so that you
	 * can click it.
	 */
	public WebElement waitForElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}
	
	public void waitForElementToBeHidden(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void waitForElementToBeVisible(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Checking for an option on a select element.
	 */
	@SuppressWarnings("deprecation")
	public void waitForSelectOptionsToBePopulated(By by, String selectValue) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(by, selectValue));
	}
	
	/**
	 * Method will return true/false if value is available in element's value attribute
	 * @param by is locator
	 * @param value is string to match
	 * @return true/false
	 */
	public boolean isTextPresentInElementValue(By by, String value) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		return wait.until(ExpectedConditions.textToBePresentInElementValue(by, value));
	}
	
	/**
	 * waits for Spinner to finish
	 */
	public void waitForProcessToComplete() {
		waitForProcessToComplete(10);
	}
	
	public void waitForProcessToComplete(int timeoutInSeconds) {
		final int FREQUENCY = 2;
		double seconds = 0;
		while (seconds < timeoutInSeconds) {
			sleep(1000 / FREQUENCY);
			if (webDriver.findElement(By.id("loadingSpinner")).isDisplayed()) {
				seconds = seconds + (1.0 / FREQUENCY);
			} else {
				return;
			}
		}
		Assert.fail("Ajax request did not complete within " + timeoutInSeconds + " seconds.");
	}
	
	public void sleep(int millis, String message) {
		stdout.println(message);
		sleep(millis);
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * To click button or hyper link on the page
	 * @param locating mechanism
	 */
	public void click(By by) {
		webDriver.findElement(by).click();
		patientlyFindElement(By.cssSelector("BODY"));
	}
	
	/**
	 * finding out if text is present on current web page
	 * @param text
	 * @return
	 */
	public boolean isTextPresent(String text) {
		WebElement bodyTag = patientlyFindElement(By.tagName("body"));
		if(bodyTag.getText().contains(text)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method will retrieve value from table cell 
	 * @param header - Column Heading
	 * @param row - row from which you want to read value
	 * @return String
	 */
	public String getCellValue(String header, int row) {
		return getCellValue("//table", header, row);
	}
	
	/**
	 * finds total number of rows
	 * @param locating mechanism
	 * @return number of rows
	 */
	public int getTableRowCount(By by) {
		List<WebElement> rows = webDriver.findElements(by);
		return rows.size();
	}
	
	public void selectValueInComboBox(final String elementId, final String value) {
		WebElement select = patientlyFindElement(By.id(elementId));
	    List<WebElement> options = select.findElements(By.tagName("option"));
	    for (WebElement option : options) {
	        if(value.equals(option.getText()))
	            option.click();
	    }
	}
	
	public void selectValueInDropDown(final String elementName, final String value) {
		Select selectElement = new Select(patientlyFindElement(By.xpath("//select[@name='"+elementName+"']")));
		selectElement.selectByVisibleText(value);
	}
		
	public void print(String string) {
		stdout.println(string);
	}
	
	/**
	 * finding out if link is present on current web page
	 * @param textLink
	 * @return
	 */
	 public boolean isLinkPresent(String textLink) {
		boolean linkPresent = false;	
		if(webDriver.findElement(By.linkText(textLink)).isDisplayed()) {
			linkPresent= true;
		}
		return linkPresent;
	}	
		
	/**
	 * This method is to close the Browser Generated Alerts
	 * As there is no clean way assert the alert text closing the alert through script.
	 * In order to know there is an alert or not returning the true or false for the alert existence and Asserting the returned value
	 * @return boolean
	 */
	public boolean closeBrowserGeneratedAlert() {
		boolean alertPresent = true;
		try {
			((JavascriptExecutor )webDriver).executeScript( "window.onbeforeunload = function(e){};" );
		} catch (Exception e) {
			alertPresent = false;
		}
		return alertPresent;
	}
	
	/**
	 * This method will return the class for the element
	 * @param elementXPath
	 * @return
	 */
	public String getElementClassByXPath(String elementXPath) {
		return patientlyFindElement(By.xpath(elementXPath)).getAttribute("class");
	}
	
	/**
	 * Check for Element Existence in the page
	 * @param by
	 * @return
	 */
	public boolean isElementPresent(By by) {
		boolean elementPresent = false;
		if(webDriver.findElements(by).size() != 0) {
			elementPresent = true;
		} 
		return elementPresent;
	}
	
	/**
	 * This method return the list of options available for the listbox based on the elementId
	 * @param elementId
	 * @return options list
	 */
	public List<String> getSelectOptionsById(String elementId) {
		WebElement select = patientlyFindElement(By.id(elementId));
	    List<WebElement> options = select.findElements(By.tagName("option"));
	    List<String> selectOptions = new ArrayList<String>();
	    for (WebElement option : options) {
	    	selectOptions.add(option.getText());
	    }
	   return selectOptions;
	}
	
	/**
	 * Method to clear and set value by ElementId
	 * @param elementId
	 * @param value
	 */
	public void clearAndSendKeysByElementId(String elementId, String value) {
		patientlyFindElement(By.id(elementId)).clear();
		patientlyFindElement(By.id(elementId)).sendKeys(value);
	}
	
	/**
	 * Method to clear and set value by ElementName
	 * @param elementId
	 * @param value
	 */
	public void clearAndSendKeysByElementName(String elementId, String value) {
		patientlyFindElement(By.name(elementId)).clear();
		patientlyFindElement(By.name(elementId)).sendKeys(value);
	}
	
	/**
	 * method to close the alert and get the text
	 * @return
	 */
	public String closeAlertAndGetItsText() {
	    Alert alert = webDriver.switchTo().alert();
	    String alertText = alert.getText().trim();
	    alert.accept();
	    return alertText;
	}	
	
	/**
	 * finds total number of rows
	 * @param locating mechanism
	 * @return number of rows
	 */
	public List<WebElement> getTableRows(By by) {
		return webDriver.findElements(by);
	}
	
	/**
	 * method to return column index of a header title
	 * @param xpath
	 * @param header
	 * @return
	 */
	public int getHeaderColumnIndex(String xpath, String header) {
		int column = 0;
		List<WebElement>headElements = webDriver.findElements(By.xpath(xpath + "//thead/tr/th"));
		for(int i=0; i< headElements.size(); i++) {
			//stdout.println("Table Headers: "+headElements.get(i).getText());
			if(headElements.get(i).getText().trim().equalsIgnoreCase(header.trim())) {
				column = i + 1;
				break;
			}
		}
		return column;
	}
	
	/**
	 * Overloading method getCellValue for multiple tables. In case there are multiple tables in a screen
	 * @param xpathToTable
	 * @param header
	 * @param row
	 * @return
	 */
	public String getCellValue(String xpathToTable, String header, int row) {
		int column = getHeaderColumnIndex(xpathToTable,header);
		if(column > 0 ) {
			return webDriver.findElement(By.xpath(xpathToTable + "[contains(., '" + header +"')]//tbody//tr[" + row +"]//td[" + column + "]")).getText();
		}
		else { 
			return "";
		}
		
	}

	public boolean isCellValueInactive(String header, int row, String value) {
		int column = 0;
		List<WebElement>headElements = webDriver.findElements(By.xpath("//thead/tr/th"));
		
		for(int i=0; i< headElements.size(); i++) {
			if(headElements.get(i).getText().equalsIgnoreCase(header)) {
				column = i + 1;
			}
		}

		if(column > 0 ) {
			return patientlyFindElement(By.xpath("//table[contains(., '" + header +"')]//tbody//tr[" + row +"]//td[" + column + "]/span[@class='inactiveChoice'][contains(.,'"+ value +"')]")).isDisplayed();
		}
		else { 
			return false;
		}
	}
	
	public void goToFrame(String xpath) {
		webDriver.switchTo().frame(webDriver.findElement(By.xpath(xpath)));
	}
	
	
	public void closePopupReturnToDefaultFrame() {
		webDriver.switchTo().defaultContent();
		patientlyFindElement(By.className("closebox")).click();
	}

	/**
	 * This method fires a Javascript event for certain element.
	 * Since Selenium's "fireEvent" method does not exist in WebDriver, we use a JavascriptExecutor to substitute it
	 * Example for blur event: "arguments[0].focus(); arguments[0].blur(); return true"
	 * @param by
	 */
	public void fireJavascriptEventForElement(By by, String event) {
		WebElement element = webDriver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript(event, element);
	}
	
	/**
	 * Executes filtering on both attributes. It works for 'input' and 'select' type filter.
	 * @param firstAttribute
	 * @param firstFilterInput
	 * @param operator
	 * @param secondAttribute
	 * @param secondFilterInput
	 */
	public void filterByAttributes(String firstAttribute, String firstFilterInput, String operator, String secondAttribute, String secondFilterInput) {
		new Select(patientlyFindElement(By.name("filterAttributeId"))).selectByVisibleText(firstAttribute);
		waitForProcessToComplete();
		
		waitForElementToBeVisible(By.xpath("//span[@id='filterAttributeOptions']"));
		WebElement filterAttributeOption = patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/*"));
		if (filterAttributeOption.getTagName().equalsIgnoreCase("input")) {
			patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/input")).clear();
			patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/input")).sendKeys(firstFilterInput);
		}
		else if (filterAttributeOption.getTagName().equalsIgnoreCase("select")) {
			Select filterAttributeOptionsSelect = new Select(patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/select")));
			filterAttributeOptionsSelect.selectByVisibleText(firstFilterInput);
		}
		
		patientlyFindElement(By.id(operator)).click();
		
		new Select(patientlyFindElement(By.name("secondFilterAttributeId"))).selectByVisibleText(secondAttribute);
		waitForProcessToComplete();
		
		waitForElementToBeVisible(By.xpath("//span[@id='secondFilterAttributeOptions']"));
		WebElement secondFilterAttributeOption = patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/*"));
		if (secondFilterAttributeOption.getTagName().equalsIgnoreCase("input")) {
			patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/input")).clear();
			patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/input")).sendKeys(secondFilterInput);
		}
		else if (secondFilterAttributeOption.getTagName().equalsIgnoreCase("select")) {
			Select filterAttributeOptionsSelect = new Select(patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/select")));
			filterAttributeOptionsSelect.selectByVisibleText(secondFilterInput);
		}
		
		patientlyFindElement(By.cssSelector("input.submitAttributeFilter")).click();
		waitForProcessToComplete();
	}

}
