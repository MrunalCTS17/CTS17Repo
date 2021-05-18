package listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;

import base.Test_base;

public class customlistener extends Test_base implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		Date d = new Date();
		String cdate = d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\snapshots\\TestFailure_"+cdate);
		
		try {
			Files.copy(screenshot, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}