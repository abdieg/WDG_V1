package org.webdriver.common;

public class Properties {
	
	String app_host;
	String selenium_host;
	int selenium_port;
	String db_driver;
	String db_url;
	String db_user;
	String db_password;
	
	public String getAppPath() {
		return app_host;
	}
	
	public void setAppPath(String appPath) {
		this.app_host = appPath;
	}
	
	public String getSeleniumAddress() {
		return selenium_host;
	}
	
	public void setSeleniumHostAddress(String seleniumHostAddress) {
		this.selenium_host = seleniumHostAddress;
	}
	
	public int getSelenSrvrPort() {
		return selenium_port;
	}
	
	public void setSeleniumPort(int seleniumPort) {
		this.selenium_port = seleniumPort;
	}
	
	public String getDbDriver() {
		return db_driver;
	}
	
	public void setDbDriver(String db_driver) {
		this.db_driver = db_driver;
	}
	
	public String getDbUrl() {
		return db_url;
	}
	
	public void setDbUrl(String db_url) {
		this.db_url = db_url;
	}
	
	public String getDbUserName() {
		return db_user;
	}
	
	public void setDbUserName(String db_user) {
		this.db_user = db_user;
	}
	
	public String getDbPassword() {
		return db_password;
	}
	
	public void setDbPassword(String db_password) {
		this.db_password = db_password;
	}
	
}

