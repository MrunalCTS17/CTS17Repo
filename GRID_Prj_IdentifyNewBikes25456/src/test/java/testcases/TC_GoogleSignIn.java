package testcases;

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
		
		elementClick("SignInBtn_Xpath");
		
		try {
			driver.findElement(By.xpath(or.getProperty("GotIt_Xpath"))).click();
		} catch (NoSuchElementException e) {
			elementClick("GotIt_Xpath");
		}
		
		sendEnter("EmailTxtBox_Id");
		
		String msg = getElementText("ErrorMsg_Xpath");
		System.out.println("\nError message displayed: "+msg);
	
	}
	
	public static void screenshotErrMsg() {
		
		WebElement emsg = getElement("ErrorMsg_Xpath");
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		
		BufferedImage fullScreen = null;
		try {
			fullScreen = ImageIO.read(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Point location1 = emsg.getLocation();
		
		int width1 = emsg.getSize().getWidth();
		int height1 = emsg.getSize().getHeight();
		 
		BufferedImage gsImg = fullScreen.getSubimage(location1.getX(), location1.getY(),width1, height1);
		
		try {
			ImageIO.write(gsImg, "png", screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+
					"\\src\\test\\resources\\snapshots\\ErrorMessage_"+ExtentReportManager.getTimeStamp()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}