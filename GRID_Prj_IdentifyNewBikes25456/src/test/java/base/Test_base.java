package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReportManager;

public class Test_base {

	public static WebDriver driver;
	public static Properties config;
	public static Properties or;
	public static ExtentTest logger;
	SoftAssert softAssert = new SoftAssert();
	public ExtentReports report = ExtentReportManager.getReportInstance();
	
	
	/****************** Load Properties File ***********************/
	
	public void loadProperties() {
		
		config = new Properties();
		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			config.load(file);
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		or = new Properties();
		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\or.properties");
			or.load(file);
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	/****************** Invoke Browser ***********************/
	
	public void invokeBrowser(String browserName) {
		
		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				try {
					String nodeUrl= "http://localhost:5555/wd/hub";
					DesiredCapabilities dc = DesiredCapabilities.chrome();
					dc.setBrowserName("chrome");
					dc.setPlatform(Platform.WINDOWS);
					
					driver = new RemoteWebDriver((new URL(nodeUrl)),dc);
					
				}catch (Exception e){
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
					ChromeOptions co = new ChromeOptions();
					co.addArguments("--disable-notifications");
					driver = new ChromeDriver(co);
					
				}

				
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				try {
					DesiredCapabilities dc = DesiredCapabilities.firefox();
					dc.setBrowserName("firefox");
					dc.setPlatform(Platform.WINDOWS);
				}catch(Exception e) {
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
					FirefoxOptions fo = new FirefoxOptions();
					fo.addArguments("--disable-notifications");
					driver = new FirefoxDriver(fo);
					
				}

				

			} else if (browserName.equalsIgnoreCase("Edge")) {
				try {
					String nodeUrl= "http://localhost:5555/wd/hub";
					DesiredCapabilities dc = DesiredCapabilities.edge();
					dc.setBrowserName("edge");
					dc.setPlatform(Platform.WINDOWS);
					
					driver = new RemoteWebDriver((new URL(nodeUrl)),dc);

				}catch(Exception e) {
					System.setProperty("webdriver.edge.driver",
							System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver.exe");
					driver = new EdgeDriver();
				}
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	/****************** Open URL ***********************/

	public void openURL(String websiteURLKey) {

		try {
			driver.get(config.getProperty(websiteURLKey));
			reportPass(websiteURLKey + " Identified Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Quit Browser ***********************/
	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}

	/****************** Enter Text ***********************/

	public static void enterText(String xpathKey, String data) {

		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + " - Entered successfully in locator Element : " + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	
	/****************** Press Enter ***********************/

	public static void sendEnter(String xpathKey) {

		try {
			getElement(xpathKey).sendKeys(Keys.ENTER);
			reportPass("Pressed Enter Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}


	/****************** Click Element ***********************/

	public static void elementClick(String xpathKey) {

		try {
			getElement(xpathKey).click();
			reportPass(xpathKey + " : Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Identify Element ***********************/

	public static WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(or.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return element;
	}

	/****************** Verify Element ***********************/
	public boolean isElementPresent(String locatorKey) {
		try {
			if (getElement(locatorKey).isDisplayed()) {
				reportPass(locatorKey + " : Element is Displayed");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean isElementSelected(String locatorKey) {
		try {
			if (getElement(locatorKey).isSelected()) {
				reportPass(locatorKey + " : Element is Selected");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean isElementEnabled(String locatorKey) {
		try {
			if (getElement(locatorKey).isEnabled()) {
				reportPass(locatorKey + " : Element is Enabled");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}
	
	/****************** Verify Title ***********************/
	public void verifyPageTitle(String pageTitle) {
		
		try {
			String actualTite = driver.getTitle();
			logger.log(Status.INFO, "Actual Title is : " + actualTite);
			logger.log(Status.INFO, "Expected Title is : " + pageTitle);
			Assert.assertEquals(actualTite, pageTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	
	}
	
	/****************** Assertion Functions ***********************/
	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertfalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		try{
			logger.log(Status.INFO, "Assertion : Actual is -" + actual + " And Expacted is - " + expected);
			softAssert.assertEquals(actual, expected);
		}catch(Exception e){
			reportFail(e.getMessage());
		}
		
	}

	
	/****************** Select List Drop Down ******************/
	public static void selectFromDropdown(String locatorPath, String Value){
		try{
			WebElement dropdown = getElement(locatorPath);
			Select select = new Select(dropdown);
			select.selectByVisibleText(Value);
			logger.log(Status.INFO, "Selected the Defined Value : " +Value);
		}catch (Exception e){
			reportFail(e.getMessage());
		}
	}
	
	
	/****************** Get Element Text ******************/
	public static String getElementText(String locatorPath){
		
		String elementText = null;
		
		try{
			WebElement Element = getElement(locatorPath);
			elementText = Element.getText();
			logger.log(Status.INFO, "Extracted the element text");
		}catch (Exception e){
			reportFail(e.getMessage());
		}
		return elementText;
	}
	
	/****************** Reporting Functions ***********************/

	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public static void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
	
	@AfterMethod
	public void afterTest() {
		softAssert.assertAll();
	}

	/****************** Capture Screen Shot ***********************/

	public static void takeScreenShotOnFailure() {

		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/ss.png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/ss.png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/***************** Wait Functions in Framework *****************/
	public static void waitForPageLoad() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public static void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void endReport() {
		report.flush();
		
	}

}