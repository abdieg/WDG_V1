package org.webdriver.tests;

import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class SecondTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		wdcommon.goToPage("http://www.github.com");
		Thread.sleep(2000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("https://www.openair.com");
    	Thread.sleep(2000);
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("https://www.slashdot.com");
    	Thread.sleep(2000);
    }
  
}
