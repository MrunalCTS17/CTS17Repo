package PageClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import base.Test_base;
import utilities.ExtentReportManager;

@Listeners(listeners.customlistener.class)
public class TC_GoogleSignIn extends Test_base {
	
	public static void gettingErrorMsg() {
		
		//Clicking on the "Sign In" button after entering the Email ID
		elementClick("SignInBtn_Xpath");
		
		//Clicking on the "Got It" button that appears on a pop-up after clicking on sign in
		try {
			driver.findElement(By.xpath(or.getProperty("GotIt_Xpath"))).click();
		} catch (NoSuchElementException e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			elementClick("GotIt_Xpath");
		}
		
		//Pressing Enter to submit the Email ID
		sendEnter("EmailTxtBox_Id");
		
		//Getting the error message
		String msg = getElementText("ErrorMsg_Xpath");
		
		//Displaying the error message on the console
		System.out.println("\nError message displayed: "+msg);
	
	}
	
	public static void screenshotErrMsg() {
		
		WebElement emsg = getElement("ErrorMsg_Xpath");
		
		//Capturing fullscreen screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		
		BufferedImage fullScreen = null;
		try {
			fullScreen = ImageIO.read(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Getting location of the error message
		Point location1 = emsg.getLocation();
		
		int width1 = emsg.getSize().getWidth();
		int height1 = emsg.getSize().getHeight();
		
		//Cropping the screenshot to only show the error message
		BufferedImage gsImg = fullScreen.getSubimage(location1.getX(), location1.getY(),width1, height1);
		
		try {
			ImageIO.write(gsImg, "png", screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Saving the screenshot as a png file in the Snapshots folder
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+
					"\\src\\test\\resources\\snapshots\\ErrorMessage_"+ExtentReportManager.getTimeStamp()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}