package testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC5_GoogleSignIn extends Test_base {
	
	@Test(dependsOnGroups="popModels",priority=1)
	public static void signUp() throws InterruptedException {
			
		String parentWin = driver.getWindowHandle();
		
		driver.findElement(By.id(or.getProperty("SignIn_id"))).click();			
		Thread.sleep(1000);
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handle = handles.iterator();
		String childWin = handle.next();
		driver.switchTo().window(childWin);
		Thread.sleep(3000);
		
		driver.findElement(By.id(or.getProperty("SignInGoogle_id"))).click();
		
		handles = driver.getWindowHandles();
		
		for (String s : handles)
			if(s!=parentWin && s!=childWin)
				driver.switchTo().window(s);
		
		try {
			
			driver.findElement(By.id(or.getProperty("EmailTxtBox_id"))).sendKeys("ghaaraavAbragal@gmail.com");
		
		}catch(NoSuchElementException e) {
			
			driver.findElement(By.id(or.getProperty("SignInGoogle_id"))).click();
			
			handles = driver.getWindowHandles();
			
			for (String s : handles)
				if(s!=parentWin && s!=childWin)
					driver.switchTo().window(s);
			
			driver.findElement(By.id(or.getProperty("EmailTxtBox_id"))).sendKeys("ghaaraavAbragal@gmail.com");
			
		}
		
		driver.findElement(By.xpath(or.getProperty("SignInBtn_xpath"))).click();
		
		driver.findElement(By.xpath(or.getProperty("GotIt_xpath"))).click();
		
		driver.findElement(By.id(or.getProperty("EmailTxtBox_id"))).sendKeys(Keys.ENTER);
		
		String msg = driver.findElement(By.xpath(or.getProperty("ErrorMsg_xpath"))).getText();
		System.out.println("\nError message displayed: "+msg);
	
	}
	
	
	@Test(dependsOnGroups="popModels",priority=2)
	public static void screenshotErrMsg() {
		
		Date d = new Date();
		String cdate = d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		WebElement emsg = driver.findElement(By.xpath("//form/span/section/div/div/div[1]/div/div[2]/div[2]/div"));
		
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
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\snapshots\\ErrorMessage_"+cdate));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}