package org.webdriver.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverTest {

	public static WebDriver driver;;
	public Common common;
	
	public WebDriverTest() {
		
	}
	
	/**
	 * Returns driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Works with DesiredCapabilities making use of RemoteWebDriver
	 * @param baseUrl
	 * @param browser
	 * @param address
	 * @param port
	 * @throws Exception
	 */
	public void setUpProperties(String baseUrl, String browser, String address, int port) throws Exception {				
		DesiredCapabilities desiredCapabilities = null;
		
		if (browser.contains("firefox")) {
			desiredCapabilities = DesiredCapabilities.firefox();
		}
		else if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", browser);
			desiredCapabilities = DesiredCapabilities.chrome();
		}
		
		WebDriverThread.startWebDriverSession(baseUrl, browser, address, port, desiredCapabilities);
		driver = WebDriverThread.session();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		common = new Common(driver);
	}
	
	/**
	 * Uses a simple URL and browser
	 * @param baseUrl
	 * @param browser
	 * @throws Exception
	 */
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
		common = new Common(driver);
	}
	
	/**
	 * Finish WebDriver session
	 * @throws Exception
	 */
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
}
