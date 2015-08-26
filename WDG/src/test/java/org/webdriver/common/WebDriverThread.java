package org.webdriver.common;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverThread {

	private static final ThreadLocal<WebDriver> threadLocalSelenium = new ThreadLocal<WebDriver>();

	/**
	 * Works with RemoteWebDriver when working with nodes or on localhost
	 * @param seleniumHostAddress
	 * @param seleniumPort
	 * @param desiredCapabilities
	 * @throws Exception
	 */
	public static void startWebDriverSession (String seleniumHostAddress, int seleniumPort, DesiredCapabilities desiredCapabilities) throws Exception {
		threadLocalSelenium.set(new RemoteWebDriver(new URL("http://" + seleniumHostAddress + ":" + seleniumPort + "/wd/hub"), desiredCapabilities));
	}

	public static WebDriver getSession() {
		return threadLocalSelenium.get();
	}

	public static void resetSession() {
		threadLocalSelenium.set(null);
	}
}