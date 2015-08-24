package org.webdriver.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverTest {

	public static WebDriver driver;;
	public common common;
	
	public WebDriverTest() {
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public void setUpProperties(String baseUrl) throws Exception {
		multipleTest(baseUrl);
	}
	
	public void tearDown() throws Exception {
		if (driver != null) {
	        try {
	            driver.quit();
	        }
	        catch (WebDriverException e) {
	            System.out.println("***CAUGHT EXCEPTION IN DRIVER TEARDOWN***");
	            System.out.println(e);
	        }
	    }
	}
	
	public void simpleTest(String baseUrl) {
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		common = new common(driver);
	}
	
	public void multipleTest(String baseUrl) throws Exception {
		WebDriverThread.startWebDriverSession(new FirefoxDriver(), baseUrl);
		driver = WebDriverThread.session();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		common = new common(driver);
	}
	
}
