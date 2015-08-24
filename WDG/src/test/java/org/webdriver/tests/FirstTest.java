package org.webdriver.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.webdriver.common.WebDriverTestParameters;

public class FirstTest extends WebDriverTestParameters {
	
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void verifyPageTitle() {
		common.waitForElementToBeVisible(By.xpath("//img[@alt='Mercury Tours']"));
		Assert.assertTrue(common.isElementPresent(By.xpath("//img[@alt='Mercury Tours']")));
		Assert.assertTrue(common.isLinkPresent("SIGN-ON"));
	}
	
	@Test (dependsOnMethods={"verifyPageTitle"})
	public void verifyPageTitle2() {
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		Assert.assertNotEquals(actualTitle, expectedTitle);
	}
  
}
