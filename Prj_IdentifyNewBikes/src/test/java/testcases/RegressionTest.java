package testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageClasses.TC_GoogleSignIn;
import PageClasses.TC_HomePage;
import PageClasses.TC_UpcomingBikes;
import PageClasses.TC_UsedCars;
import base.Test_base;

public class RegressionTest extends Test_base {

	@Test
	@Parameters("browser")
	public void launchURL(String browser) {
		
		//Creating a new test in the HTML/extent report
		logger = report.createTest("Browser Select and Launch URL");
		
		loadProperties();
		invokeBrowser(browser);
		openURL("testURL");
		TC_HomePage.dismissPOPUP();
		verifyPageTitle(config.getProperty("homePage_Title"));
		
	}
	
	@Test(priority=1)
	public void upBikes() {
		
		logger = report.createTest("Upcoming Honda Bikes");
		
		TC_HomePage.newUpcomingBikes();
		TC_UpcomingBikes.makeHonda();
		verifyPageTitle(config.getProperty("upcomingBikesPage_Title"));
		TC_UpcomingBikes.hondaBikesDetail();
		TC_UpcomingBikes.sortingDetails();
		TC_UpcomingBikes.displayingDetails();
		
	}
	
	@Test(priority=2)
	public void usedCars() {
		
		logger = report.createTest("Used Cars In Chennai");
		
		TC_HomePage.usedCarsChn();
		verifyPageTitle(config.getProperty("usedCarsPage_Title"));
		TC_UsedCars.popularModels();
		
	}
	
	@Test(priority=3)
	public void googleSignIn() {
		
		logger = report.createTest("Signing In With Invalid Credentials");
		
		TC_HomePage.signUp();
		TC_GoogleSignIn.gettingErrorMsg();
		TC_GoogleSignIn.screenshotErrMsg();
		
	}
	
}
