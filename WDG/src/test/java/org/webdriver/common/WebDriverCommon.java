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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebDriverCommon {
	
	private WebDriver webDriver;
	private PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	public WebDriverCommon(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	/**
	 * Go to Main Page on project
	 * @param baseUrl
	 */
	public void goToMainPage(String baseUrl) {
		webDriver.get(baseUrl);
	}
	
	/**
	 * Go to specific URL
	 * @param url
	 */
	public void goToPage(String url) {
		webDriver.get(url);
	}
	
	/**
	 * Will try to find the WebElement within 30 seconds.
	 * If the WebElement is not found within 30 seconds, a TimeoutException will be thrown. 
	 * Once found thread will sleep for milliseconds specified in variable 'timeToWaitAfterElementFound'
	 * to give some time for browser to bind event listeners to the element (if there happened to be one)
	 * @param	by The locating mechanism
	 * @return	The first matching element on the current page
	 * @throws	TimeoutException If no matching elements are found
	 */
	public WebElement patientlyFindElement(final By by) {
		return patientlyFindElement(by, 30);
	}
	
	/**
	 * Will try to find the WebElement within 30 seconds.
	 * If the WebElement is not found within 30 seconds, a TimeoutException will be thrown. 
	 * Once found thread will sleep for milliseconds specified in variable 'timeToWaitAfterElementFound'
	 * to give some time for browser to bind event listeners to the element (if there happened to be one)
	 * @param	by The locating mechanism
	 * @return	The first matching element on the current page
	 * @throws	TimeoutException If no matching elements are found
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
	 * Wait for an element to be clickable
	 * @param by
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}
	
	/**
	 * Wait for an element to be hidden
	 * @param by
	 */
	public void waitForElementToBeHidden(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	/**
	 * Wait for an element to be visible
	 * @param by
	 */
	public void waitForElementToBeVisible(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Wait for dropdown menu to be populated
	 * @param by
	 * @param selectValue
	 */
	public void waitForSelectOptionsToBePopulated(By by, String selectValue) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, selectValue));
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
	 * Find if text is present on current web page
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
	 * Find if link is present con the current page
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
	 * Check for element existence in the page
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
	 * Waits for spinner to finish
	 */
	public void waitForProcessToComplete() {
		waitForProcessToComplete(10);
	}
	
	/**
	 * Main method for spinner
	 * @param timeoutInSeconds
	 */
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
	
	/**
	 * Sleeps for the given amount of time and shows a message
	 * @param millis
	 * @param message
	 */
	public void sleep(int millis, String message) {
		stdout.println(message);
		sleep(millis);
	}

	/**
	 * Sleeps for the given amount of time
	 * @param millis
	 */
	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Clicks an element on the page
	 * @param by
	 */
	public void click(By by) {
		patientlyFindElement(By.cssSelector("BODY"));
		webDriver.findElement(by).click();
	}
	
	/**
	 * Gets all the rows on a given table
	 * @param by
	 * @return
	 */
	public List<WebElement> getTableRows(By by) {
		return webDriver.findElements(by);
	}
	
	/**
	 * Method to return column index of a header title
	 * @param xpath
	 * @param header
	 * @return
	 */
	public int getHeaderColumnIndex(String xpath, String header) {
		int column = 0;
		List<WebElement>headElements = webDriver.findElements(By.xpath(xpath + "//thead/tr/th"));
		for(int i=0; i< headElements.size(); i++) {
			stdout.println("Table Headers: " + headElements.get(i).getText());
			if(headElements.get(i).getText().trim().equalsIgnoreCase(header.trim())) {
				column = i + 1;
				break;
			}
		}
		return column;
	}
	
	/**
	 * This method will retrieve value from table cell
	 * @param header
	 * @param row
	 * @return
	 */
	public String getCellValue(String header, int row) {
		return getCellValue("//table", header, row);
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
	
	/**
	 * Finds total number of rows in table
	 * @param by
	 * @return
	 */
	public int getTableRowCount(By by) {
		List<WebElement> rows = webDriver.findElements(by);
		return rows.size();
	}
	
	/**
	 * Selects a value in a given combo box
	 * @param elementId
	 * @param value
	 */
	public void selectValueInComboBox(String elementId, String value) {
		WebElement select = patientlyFindElement(By.id(elementId));
	    List<WebElement> options = select.findElements(By.tagName("option"));
	    for (WebElement option : options) {
	        if(value.equals(option.getText()))
	            option.click();
	    }
	}
	
	/**
	 * Selects a value in a dropdown
	 * @param elementName
	 * @param value
	 */
	public void selectValueInDropDown(String elementName, String value) {
		Select selectElement = new Select(patientlyFindElement(By.xpath("//select[@name='"+elementName+"']")));
		selectElement.selectByVisibleText(value);
	}
	
	/**
	 * Prints something on the console
	 * @param string
	 */
	public void print(String string) {
		stdout.println(string);
	}
		
	/**
	 * This method is to close the Browser Generated Alerts
	 * As there is no clean way assert the alert text closing the alert through script
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
	 * Method to close the alert and get the text
	 * @return
	 */
	public String closeAlertAndGetItsText() {
	    Alert alert = webDriver.switchTo().alert();
	    String alertText = alert.getText().trim();
	    alert.accept();
	    return alertText;
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
	 * This method return the list of options available for the listbox based on the elementId
	 * @param elementId
	 * @return
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
	public void clearAndSendKeysByElementId(String element, String value) {
		patientlyFindElement(By.id(element)).clear();
		patientlyFindElement(By.id(element)).sendKeys(value);
	}
	
	/**
	 * Method to clear and set value by ElementName
	 * @param elementId
	 * @param value
	 */
	public void clearAndSendKeysByElementName(String element, String value) {
		patientlyFindElement(By.name(element)).clear();
		patientlyFindElement(By.name(element)).sendKeys(value);
	}
	
	/**
	 * Method to clear and set value by Xpath
	 * @param element
	 * @param value
	 */
	public void clearAndSendKeysByElementXpath(String element, String value) {
		patientlyFindElement(By.xpath(element)).clear();
		patientlyFindElement(By.xpath(element)).sendKeys(value);
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
	 * Executes a drag and drop
	 * @param from
	 * @param to
	 * @throws InterruptedException
	 */
	public void dragAndDrop(By from, By to) throws InterruptedException {
		Actions act = new Actions(webDriver);
		WebElement fromThis = webDriver.findElement(from);
		WebElement toThis = webDriver.findElement(to);
		act.clickAndHold(fromThis).moveToElement(toThis).perform();
		Thread.sleep(50);
		act.release(toThis).perform();
	}
}
