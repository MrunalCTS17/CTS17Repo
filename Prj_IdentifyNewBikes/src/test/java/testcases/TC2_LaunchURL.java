package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC2_LaunchURL extends Test_base{
	
	@Test(groups="getURL",dependsOnGroups="selDriver")
	public static void getURL() {
		
		//Fetching the URL from properties file
		driver.get(config.getProperty("testURL"));
		
		waitForPageLoad();

		//Closing the pop-up
		driver.findElement(By.id(or.getProperty("dismissbtn_id"))).click();
	}
	
}