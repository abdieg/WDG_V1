package org.webdriver.common;

import org.openqa.selenium.WebDriver;

public class WebDriverThread {
	
	private static final ThreadLocal<WebDriver> threadLocalSelenium = new ThreadLocal<WebDriver>();
	
	public static void startWebDriverSession (WebDriver driver, String baseUrl) throws Exception {
		threadLocalSelenium.set(driver);
		session().get(baseUrl);
	}

	public static WebDriver session() {
		return threadLocalSelenium.get();
	}

	public static void resetSession() {
		threadLocalSelenium.set(null);
	}

}
