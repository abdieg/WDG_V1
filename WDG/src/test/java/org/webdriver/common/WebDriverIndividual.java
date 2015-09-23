package org.webdriver.common;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverIndividual extends WebDriverTest {

	PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));

	/**
	 * Gets the parameters and gives them to WebDriverTest
	 */
	@BeforeClass
	@Parameters({"selenium-host", "selenium-port", "app-host", "browser-Firefox", "browser-Chrome"})
	public void setUp(String seleniumHostAddress, int seleniumPort, String appHost, String browserFirefox, String browserChrome) throws MalformedURLException {
		String browser = System.getProperty("browser");
		setUpProperties(seleniumHostAddress, seleniumPort, browser, appHost, browserFirefox, browserChrome);
		driver.get(appHost);
	}
	
	/**
	 * Delete all cookies after executing a method
	 */
	@AfterMethod(alwaysRun = true)
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
	
	/**
	 * Finish WebDriver session
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
}

