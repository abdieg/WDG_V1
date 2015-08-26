package org.webdriver.common;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverIndividual extends WebDriverTest {

	PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));

	/**
	 * Gets the parameters and gives them to WebDriverTest
	 */
	@BeforeClass
	@Parameters({"selenium-host", "selenium-port", "browser", "app-host"})
	public void setUp(String seleniumHostAddress, int seleniumPort, String browser, String appHost) throws MalformedURLException {
		setUpProperties(seleniumHostAddress, seleniumPort, browser, appHost);
		driver.get(appHost);
	}
	
	/**
	 * Finish WebDriver session
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

