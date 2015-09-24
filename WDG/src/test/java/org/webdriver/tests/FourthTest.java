package org.webdriver.tests;

import org.testng.annotations.Test;
import org.webdriver.common.WebDriverIndividual;

public class FourthTest extends WebDriverIndividual {
	
	@Test
    public void testMethod1() throws InterruptedException {
		app.goToPage("http://www.postgresql.org/");
		Thread.sleep(2000);
    }
 
    @Test (dependsOnMethods={"testMethod1"})
    public void testMethod2() throws InterruptedException {
    	driver.get("http://www.oracle.com");
    	Thread.sleep(2000);
    }
    
    @Test (dependsOnMethods={"testMethod2"})
    public void testMethod3() throws InterruptedException {
    	driver.get("https://www.linux.com");
    	Thread.sleep(2000);
    }
  
}
