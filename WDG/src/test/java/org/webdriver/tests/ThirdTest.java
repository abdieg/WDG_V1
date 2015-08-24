package org.webdriver.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.webdriver.common.WebDriverTestParameters;

public class ThirdTest extends WebDriverTestParameters {
	
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void verifyPageLEL() {
		common.waitForElementToBeVisible(By.xpath("//img[@alt='Mercury Tours']"));
		Assert.assertTrue(common.isElementPresent(By.xpath("//img[@alt='Mercury Tours']")));
		Assert.assertTrue(common.isLinkPresent("SIGN-ON"));
	}
	
	@Test (dependsOnMethods={"verifyPageLEL"})
	public void verifyPageLEL2() {
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
  
}
