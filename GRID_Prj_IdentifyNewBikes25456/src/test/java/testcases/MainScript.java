package testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Test_base;

public class MainScript extends Test_base {

	@Test
	@Parameters("browser")
	public void launchURL(String browser) {
		
		logger = report.createTest("Browser Select and Launch URL");
		
		loadProperties();
		invokeBrowser(browser);
		openURL("testURL");
		TC_HomePage.dismissPOPUP();
		
	}
	
	@Test(priority=1, groups= {"smoke","regression"})
	public void upBikes() {
		
		logger = report.createTest("Upcoming Honda Bikes");
		
		TC_HomePage.newUpcomingBikes();
		TC_UpcomingBikes.makeHonda();
		TC_UpcomingBikes.hondaBikesDetail();
		TC_UpcomingBikes.sortingDetails();
		TC_UpcomingBikes.displayingDetails();
		
	}
	
	@Test(priority=2, groups = "regression")
	public void usedCars() {
		
		logger = report.createTest("Used Cars In Chennai");
		
		TC_HomePage.usedCarsChn();
		TC_UsedCars.popularModels();
		
	}
	
	@Test(priority=3, groups = "regression")
	public void googleSignIn() {
		
		logger = report.createTest("Signing In With Invalid Credentials");
		
		TC_HomePage.signUp();
		TC_GoogleSignIn.gettingErrorMsg();
		TC_GoogleSignIn.screenshotErrMsg();
		
	}
	
	
	
	
}
