package org.webdriver.common;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class WebDriverTest {

	public WebDriver driver;
	public WebDriverCommon wdcommon;

	/**
	 * Works with DesiredCapabilities making use of RemoteWebDriver
	 * @param seleniumHostAddress
	 * @param seleniumPort
	 * @param browser
	 * @param appHost
	 * @throws MalformedURLException
	 */
	public void setUpProperties(String seleniumHostAddress, int seleniumPort, String browser, String appHost, String browserFirefox, String browserChrome) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = null;
		if (browser.contains("firefox")) {
			desiredCapabilities = DesiredCapabilities.firefox();
		}
		else if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", browserChrome);
			desiredCapabilities = DesiredCapabilities.chrome();
		}
//		else if (browser.equalsIgnoreCase("*iexplore")) {
//			desiredCapabilities = DesiredCapabilities.internetExplorer();
//		desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		}
		else {
            throw new RuntimeException("Browser type unsupported");
        }

		try {
			WebDriverThread.startWebDriverSession(seleniumHostAddress, seleniumPort, desiredCapabilities);
		} catch (Exception e) {
			System.out.println("There was an exception creating WebDriver: " + e);
			e.printStackTrace();
		}
		
		driver = WebDriverThread.getSession();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

		wdcommon = new WebDriverCommon(driver);
		wdcommon.goToPage(appHost);
	}

}
