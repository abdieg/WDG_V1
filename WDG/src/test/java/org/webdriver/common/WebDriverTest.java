package org.webdriver.common;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.application.common.ApplicationCommon;
import org.application.common.ApplicationDatabase;
import org.application.common.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class WebDriverTest {

	public WebDriver driver;
	public ApplicationCommon app;
	public Properties properties;
	public ApplicationDatabase dbutil;
	public Utilities utilities;

	/**
	 * Works with DesiredCapabilities making use of RemoteWebDriver
	 * @param seleniumHostAddress
	 * @param seleniumPort
	 * @param browser
	 * @param appHost
	 * @throws MalformedURLException
	 */
	public void setUpProperties(String seleniumHostAddress, int seleniumPort, String browser, String appHost, String browserFirefox, String browserChrome, 
								String db_driver, String db_url, String db_user, String db_password) throws MalformedURLException {
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
		
		properties = new Properties();
		properties.setSeleniumHostAddress(seleniumHostAddress);
		properties.setSeleniumPort(seleniumPort);
		properties.setAppPath(appHost);
		properties.setDbDriver(db_driver);
		properties.setDbUrl(db_url);
		properties.setDbUserName(db_user);
		properties.setDbPassword(db_password);
		
		dbutil = new ApplicationDatabase(properties);

		app = new ApplicationCommon(driver);
		app.goToPage(appHost);
	}

}
