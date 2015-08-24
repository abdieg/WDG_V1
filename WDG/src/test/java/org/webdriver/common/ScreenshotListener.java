package org.webdriver.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

	WebDriver driver = null;
    
    public void onTestSuccess(ITestResult result) { 
    	System.out.println("SUCCESS: '" + result.getName() + "' has succeded.");
    	String methodName = result.getName().toString().trim();
    	try {
			takeScreenShot(cleanClassName(result), methodName, "success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("ERROR: '" + result.getName() + "' has failed.");
    	String methodName = result.getName().toString().trim();
    	try {
			takeScreenShot(cleanClassName(result), methodName, "fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void onTestSkipped(ITestResult result) { 
    	System.out.println("SKIPPED: '" + result.getName() + "' was skipped.");
    	String methodName = result.getName().toString().trim();
    	try {
			takeScreenShot(cleanClassName(result), methodName, "skip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void takeScreenShot(String test, String methodName, String status) throws IOException {
    	driver = WebDriverTest.getDriver();
    	
    	File path = new File (".");
    	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss_aa");
		Date date = new Date();
		String screenshotFilePath = "";
		
		if (status.equalsIgnoreCase("fail"))
			screenshotFilePath = path.getCanonicalPath() + "\\ScreenShots\\Failed\\" + dateFormat.format(date) + "_" + test + "_" + methodName + ".png";
        else if (status.equalsIgnoreCase("skip"))
        	screenshotFilePath = path.getCanonicalPath() + "\\ScreenShots\\Skipped\\" + dateFormat.format(date) + "_" + test + "_" + methodName + ".png";
        else if (status.equalsIgnoreCase("success"))
        	screenshotFilePath = path.getCanonicalPath() + "\\ScreenShots\\Success\\" + dateFormat.format(date) + "_" + test + "_" + methodName + ".png";			
		
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(scrFile, new File(screenshotFilePath));           
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        if (status.equalsIgnoreCase("fail"))
        	Reporter.log("<a target=\"_blank\" href=file:///" + screenshotFilePath + ">Failed! "+ test + " > " + methodName +"</a>");
        else if (status.equalsIgnoreCase("skip"))
        	Reporter.log("<a target=\"_blank\" href=file:///" + screenshotFilePath + ">Skipped! "+ test + " > " + methodName +"</a>");
        else if (status.equalsIgnoreCase("success"))
        	Reporter.log("<a target=\"_blank\" href=file:///" + screenshotFilePath + ">Success! "+ test + " > " + methodName +"</a>");
    }
    
    public String cleanClassName(ITestResult result) {
    	String className = result.getTestClass().getRealClass().toString();
    	String newClassName = className.replace("class ", "");
    	return newClassName;
    }
    
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	}
    
    public void onTestStart(ITestResult result) {	}
    
    public void onStart(ITestContext context) {	   }
    
	public void onFinish(ITestContext context) {	}
	
}
