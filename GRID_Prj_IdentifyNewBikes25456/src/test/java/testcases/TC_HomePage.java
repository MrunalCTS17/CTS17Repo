package testcases;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Test_base;

public class TC_HomePage extends Test_base {

	static WebElement UpBikes;
	
	public static void dismissPOPUP() {
	
		elementClick("dismissbtn_Id");
	
	}
		
	public static TC_UpcomingBikes newUpcomingBikes() {
		
		Actions action = new Actions(driver);
		
		UpBikes = getElement("Ele_LinkText");
		action.moveToElement(UpBikes).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getElement("UCBks_Xpath")));
		
		elementClick("UCBks_Xpath");
		
		return(TC_UpcomingBikes) PageFactory.initElements(driver,TC_UpcomingBikes.class);
	
	}
	
	public static TC_UsedCars usedCarsChn() {

		WebElement usedCar = getElement("usedcar_Xpath");

		Actions action = new Actions(driver);
		action.moveToElement(usedCar).build().perform();

		elementClick("clickchn_Xpath");
		
		waitForPageLoad();
		
		return(TC_UsedCars) PageFactory.initElements(driver,TC_UsedCars.class);

	}
	
	public static TC_GoogleSignIn signUp() {
		
		String parentWin = driver.getWindowHandle();
		
		elementClick("SignIn_Id");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handle = handles.iterator();
		String childWin = handle.next();
		driver.switchTo().window(childWin);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		elementClick("SignInGoogle_Id");
		
		handles = driver.getWindowHandles();
		
		for (String s : handles)
			if(s!=parentWin && s!=childWin)
				driver.switchTo().window(s);
		
		try {
			
			driver.findElement(By.id(or.getProperty("EmailTxtBox_Id"))).sendKeys("ghaaraavAbragal@gmail.com");
		
		}catch(NoSuchElementException e) {
			
			elementClick("SignInGoogle_Id");
			
			handles = driver.getWindowHandles();
			
			for (String s : handles)
				if(s!=parentWin && s!=childWin)
					driver.switchTo().window(s);
			
			enterText("EmailTxtBox_Id", "ghaaraavAbragal@gmail.com");
			
		}
		
		return(TC_GoogleSignIn) PageFactory.initElements(driver,TC_GoogleSignIn.class);
		
	}
	
}