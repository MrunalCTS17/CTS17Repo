package PageClasses;

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
	
		//Clicking on close pop-up
		elementClick("dismissbtn_Id");
	
	}
	
	public static TC_UpcomingBikes newUpcomingBikes() {
		
		//Creating object of the Actions class
		Actions action = new Actions(driver);
		
		UpBikes = getElement("Ele_LinkText");
		
		//Hovering the mouse over the "New Bikes" tab
		action.moveToElement(UpBikes).build().perform();
		
		//Waiting for the "Upcoming Bikes" option to be clickable
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getElement("UCBks_Xpath")));
		
		//Clicking on "Upcoming Bikes"
		elementClick("UCBks_Xpath");
		
		return(TC_UpcomingBikes) PageFactory.initElements(driver,TC_UpcomingBikes.class);
	
	}
	
	public static TC_UsedCars usedCarsChn() {

		WebElement usedCar = getElement("usedcar_Xpath");

		Actions action = new Actions(driver);
		
		//Hovering the mouse over the "Used Cars" tab
		action.moveToElement(usedCar).build().perform();

		//Clicking on "Chennai"
		elementClick("clickchn_Xpath");
		
		//Waiting for the page to load completely
		waitForPageLoad();
		
		return(TC_UsedCars) PageFactory.initElements(driver,TC_UsedCars.class);

	}
	
	public static TC_GoogleSignIn signUp() {
		
		//Getting the Window Handle Id of the Main Window
		String parentWin = driver.getWindowHandle();
		
		//Clicking on "Login/SignUp" button
		elementClick("SignIn_Id");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Getting the Window handles in a Set "handles"
		Set<String> handles = driver.getWindowHandles();
		
		//Creating iterator to iterate over the Set "handles"
		Iterator<String> handle = handles.iterator();
		
		//Getting Id of the child window and switching to it
		String childWin = handle.next();
		driver.switchTo().window(childWin);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Clicking on "Continue with Google"
		elementClick("SignInGoogle_Id");
		
		//Getting the Window handles after clicking on "Continue with Google"
		handles = driver.getWindowHandles();
		
		//Switching to the third window, i.e. Sign in with Google window
		for (String s : handles)
			if(s!=parentWin && s!=childWin)
				driver.switchTo().window(s);
		
		try {
			
			//Finding the Email ID text box and entering invalid email into it
			driver.findElement(By.id(or.getProperty("EmailTxtBox_Id"))).sendKeys("gharaavAbragal@gmail.com");
		
		}catch(NoSuchElementException e) {
			
			elementClick("SignInGoogle_Id");
			
			handles = driver.getWindowHandles();
			
			for (String s : handles)
				if(s!=parentWin && s!=childWin)
					driver.switchTo().window(s);
			
			//Sending invalid keys to the Email ID text box
			enterText("EmailTxtBox_Id", "gharaavAbragal@gmail.com");
			
		}
		
		return(TC_GoogleSignIn) PageFactory.initElements(driver,TC_GoogleSignIn.class);
		
	}
	
}