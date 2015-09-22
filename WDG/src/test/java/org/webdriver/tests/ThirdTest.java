package org.webdriver.tests;

import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class ThirdTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		wdcommon.goToPage("https://www.medium.com");
		Thread.sleep(2000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("https://www.mysql.com/");
    	Thread.sleep(2000);
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("https://www.joomla.org/");
    	Thread.sleep(2000);
    }
  
}
