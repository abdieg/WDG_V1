package org.webdriver.common;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverThread {
	
	private static final ThreadLocal<WebDriver> threadLocalSelenium = new ThreadLocal<WebDriver>();
	
	/**
	 * Uses simple driver when working on localhost
	 * @param driver
	 * @param baseUrl
	 * @throws Exception
	 */
	public static void startWebDriverSession (WebDriver driver, String baseUrl) throws Exception {
		threadLocalSelenium.set(driver);
		session().get(baseUrl);
	}
	
	/**
	 * Works with RemoteWebDriver when working with nodes or on localhost
	 * @param baseUrl
	 * @param browser
	 * @param address
	 * @param seleniumPort
	 * @param desiredCapabilities
	 * @throws Exception
	 */
	public static void startWebDriverSession (String baseUrl, String browser, String address, int seleniumPort, DesiredCapabilities desiredCapabilities) throws Exception {
		threadLocalSelenium.set(new RemoteWebDriver(new URL("http://" + address + ":" + seleniumPort + "/wd/hub"), desiredCapabilities));
		session().get(baseUrl);
	}

	public static WebDriver session() {
		return threadLocalSelenium.get();
	}

	public static void resetSession() {
		threadLocalSelenium.set(null);
	}

}
