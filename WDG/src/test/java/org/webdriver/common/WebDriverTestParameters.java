package org.webdriver.common;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverTestParameters extends WebDriverTest {

	protected static PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	public WebDriverTestParameters() {
		super();
	}
	
	/**
	 * Gets the parameters and gives them to WebDriverTest
	 */
	@BeforeClass
	@Parameters ({ "baseUrl", "browser", "address", "port" })
	public void setUpProperties(String baseUrl, String browser, String address, int port) throws Exception {
		super.setUpProperties(baseUrl, browser, address, port);
	}
	
	/**
	 * Finish WebDriver session
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		stdout.println("Quitting webdriver from WebDriverTestParameters class");
		super.tearDown();
	}	
}
