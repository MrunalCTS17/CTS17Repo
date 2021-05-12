package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC1_BrowserSelect extends Test_base {

	static int browser;
	
	@BeforeTest
	public static void userInput() throws IOException {
		
		Test_base.fconfig = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		Test_base.org = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\or.properties");
		
		config.load(fconfig);
		or.load(org);
		
		System.out.println("Choose a browser:");
		System.out.println("Press \"1\" for Mozilla Firefox\nPress \"2\" for Microsoft Edge\nPress \"3\" for Google Chrome");
		
		browser = Test_base.sc.nextInt();
		
	}
	
	@Test(groups="selDriver")
	public static void selDriver() {
		
		switch(browser) {
				
			case 1:
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src/test/resources//executables//geckodriver.exe");
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--disable-notifications");
				Test_base.driver = new FirefoxDriver(fo);
				break;
				
			case 2:
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"//src/test/resources//executables//msedgedriver.exe");
				EdgeOptions eo = new EdgeOptions();
				Test_base.driver = new EdgeDriver(eo);
				break;
				
			case 3:
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src/test/resources//executables//chromedriver.exe");
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--disable-notifications");
				Test_base.driver = new ChromeDriver(co);
				break;
					
			default:
				System.out.println("Choose a valid value!");
			}
		
		Test_base.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Test_base.driver.manage().window().maximize();
		
	}
	
	@AfterTest
	public static void closeScQuitBrowser() {
		Test_base.sc.close();
		Test_base.driver.quit();
	}
	
}