package org.webdriver.tests;

import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class FirstTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		wdcommon.goToPage("https://mariadb.org/");
		wdcommon.print("STDOUT");
		Thread.sleep(2000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("https://www.grammarly.com/1");
    	Thread.sleep(2000);
//    	Assert.fail();
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("http://www.microsoft.com/");
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
