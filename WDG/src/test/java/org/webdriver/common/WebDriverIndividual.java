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
	@Parameters({"selenium-host", "selenium-port", "app-host", "browser-Firefox", "browser-Chrome", 
				 "db-driver", "db-url", "db-user", "db-password"})
	public void setUp(String seleniumHostAddress, int seleniumPort, String appHost, String browserFirefox, String browserChrome, 
					  String db_driver, String db_url, String db_user, String db_password) throws MalformedURLException {
		String browser = System.getProperty("browser");
		setUpProperties(seleniumHostAddress, seleniumPort, browser, appHost, browserFirefox, browserChrome, 
						db_driver, db_url, db_user, db_password);
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

