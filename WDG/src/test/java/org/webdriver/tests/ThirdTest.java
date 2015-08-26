package org.webdriver.tests;

import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class ThirdTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		common.goToPage("https://www.medium.com");
		Thread.sleep(5000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("http://e-quallity.net/");
    	Thread.sleep(5000);
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("http://www.heidoc.net/joomla/");
    	Thread.sleep(5000);
    }
  
}
