package org.webdriver.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.webdriver.common.WebDriverTestParameters;

public class FourthTest extends WebDriverTestParameters {
	
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void verifyPageXF() {
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (dependsOnMethods={"verifyPageXF"})
	public void verifyPageXF2() {
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test (dependsOnMethods={"verifyPageXF2"})
	public void verifyPageXF3() {
		common.waitForElementToBeVisible(By.xpath("//img[@alt='Mercury Tours']"));
		Assert.assertTrue(common.isElementPresent(By.xpath("//img[@alt='Mercury Tours']")));
		Assert.assertTrue(common.isLinkPresent("SIGN-ON"));
	}
  
}
