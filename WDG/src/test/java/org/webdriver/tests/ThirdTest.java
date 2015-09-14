package org.webdriver.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class ThirdTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		common.goToPage("https://www.medium.com");
		Thread.sleep(2000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("http://e-quallity.net/");
    	Thread.sleep(2000);
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("http://www.heidoc.net/joomla/");
    	Thread.sleep(2000);
    }
	
//	@Test
//	public void testMethod1() throws InterruptedException {
//		common.goToPage("http://192.168.0.19/player.htm?f=CgAggAIg");
//		common.waitForElementToBeVisible(By.id("PF_7"));
//		for (int i = 0; i < 40; i++) {
//			common.waitForElementToBeVisible(By.id("PF_9"));
//			common.patientlyFindElement(By.id("PF_14")).click();
//			common.waitForElementToBeVisible(By.id("PF_9"));
//			common.sleep(1000);
//		}
//		common.waitForElementToBeVisible(By.id("PF_9"));
//		Assert.assertTrue(common.isTextPresent("Large Heading"));
//	}
  
}
