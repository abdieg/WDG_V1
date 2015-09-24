package org.application.common;

import org.openqa.selenium.WebDriver;
import org.webdriver.common.WebDriverCommon;

public class ApplicationCommon extends WebDriverCommon {
	
	public WebDriver webDriver;
	
	public ApplicationCommon(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}
	
	public void goToLoginPage() {
		webDriver.get("http://stackoverflow.com/");
	}
	
//	/**
//	 * Login using test credentials
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
	
//	/**
//	 * Switch to a frame
//	 * @param xpath
//	 */
//	public void goToFrame(String xpath) {
//		webDriver.switchTo().frame(webDriver.findElement(By.xpath(xpath)));
//	}
//	
//	/**
//	 * Clicks on the closebox of the frame
//	 */
//	public void closePopupReturnToDefaultFrame() {
//		webDriver.switchTo().defaultContent();
//		patientlyFindElement(By.className("closebox")).click();
//	}
	
//	/**
//	 * Executes filtering on both attributes. It works for 'input' and 'select' type filter.
//	 * @param firstAttribute
//	 * @param firstFilterInput
//	 * @param operator
//	 * @param secondAttribute
//	 * @param secondFilterInput
//	 */
//	public void filterByAttributes(String firstAttribute, String firstFilterInput, String operator, String secondAttribute, String secondFilterInput) {
//		new Select(patientlyFindElement(By.name("filterAttributeId"))).selectByVisibleText(firstAttribute);
//		waitForProcessToComplete();
//		
//		waitForElementToBeVisible(By.xpath("//span[@id='filterAttributeOptions']"));
//		WebElement filterAttributeOption = patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/*"));
//		if (filterAttributeOption.getTagName().equalsIgnoreCase("input")) {
//			patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/input")).clear();
//			patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/input")).sendKeys(firstFilterInput);
//		}
//		else if (filterAttributeOption.getTagName().equalsIgnoreCase("select")) {
//			Select filterAttributeOptionsSelect = new Select(patientlyFindElement(By.xpath("//span[@id='filterAttributeOptions']/select")));
//			filterAttributeOptionsSelect.selectByVisibleText(firstFilterInput);
//		}
//		
//		patientlyFindElement(By.id(operator)).click();
//		
//		new Select(patientlyFindElement(By.name("secondFilterAttributeId"))).selectByVisibleText(secondAttribute);
//		waitForProcessToComplete();
//		
//		waitForElementToBeVisible(By.xpath("//span[@id='secondFilterAttributeOptions']"));
//		WebElement secondFilterAttributeOption = patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/*"));
//		if (secondFilterAttributeOption.getTagName().equalsIgnoreCase("input")) {
//			patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/input")).clear();
//			patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/input")).sendKeys(secondFilterInput);
//		}
//		else if (secondFilterAttributeOption.getTagName().equalsIgnoreCase("select")) {
//			Select filterAttributeOptionsSelect = new Select(patientlyFindElement(By.xpath("//span[@id='secondFilterAttributeOptions']/select")));
//			filterAttributeOptionsSelect.selectByVisibleText(secondFilterInput);
//		}
//		
//		patientlyFindElement(By.cssSelector("input.submitAttributeFilter")).click();
//		waitForProcessToComplete();
//	}

}
