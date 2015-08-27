package org.webdriver.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class FirstTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		common.goToPage("http://www.hardwaremx.com");
		common.print("STDOUT");
		Thread.sleep(5000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("https://www.grammarly.com/1");
    	Thread.sleep(5000);
    	Assert.fail();
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("https://rationaleemotions.wordpress.com");
    	Thread.sleep(5000);
    }
  
}
