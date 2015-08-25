package org.webdriver.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverTest {

	public static WebDriver driver;;
	public common common;
	
	public WebDriverTest() {
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public void setUpProperties(String baseUrl, String browser) throws Exception {		
		if (browser.contains("firefox")) {
			WebDriverThread.startWebDriverSession(new FirefoxDriver(), baseUrl);
		}
		else if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", browser);
			WebDriverThread.startWebDriverSession(new ChromeDriver(), baseUrl);
		}
		driver = WebDriverThread.session();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		common = new common(driver);
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
	
//	public void simpleTest(String baseUrl) {
//		driver = new FirefoxDriver();
////		driver = new ChromeDriver();
//		driver.get(baseUrl);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		common = new common(driver);
//	}
	
////System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\ChromeDriver\\chromedriver.exe");
////WebDriverThread.startWebDriverSession(new ChromeDriver(), baseUrl);
//WebDriverThread.startWebDriverSession(new FirefoxDriver(), baseUrl);
//driver = WebDriverThread.session();
//driver.manage().window().maximize();
//driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
//common = new common(driver);
	
}
