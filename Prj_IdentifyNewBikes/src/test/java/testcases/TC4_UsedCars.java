package testcases;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC4_UsedCars extends Test_base {

	static WebDriverWait wait;
	
	
	@Test(groups="popModels",dependsOnGroups="upBikes",priority=1)
	public static void usedCarsChn() throws IOException {
		
		WebElement usedCar = driver.findElement(By.xpath(or.getProperty("usedcar_xpath")));
		
		Actions action = new Actions(driver);
		action.moveToElement(usedCar).build().perform();
		
		driver.findElement(By.xpath(or.getProperty("clickchn_xpath"))).click();
		
		waitForPageLoad();

	}
	
	
	@Test(groups="popModels",dependsOnGroups="upBikes",priority=2)
	public static void popularModels() throws IOException {
			
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600);");
		
		System.out.println("\nThe popular used car models in Chennai are :-\n");
		
		ArrayList<WebElement> al = new ArrayList<WebElement>();
		al = (ArrayList<WebElement>) driver.findElements(By.className(or.getProperty("arraylist_classname")));
		
		for(WebElement w:al)
			System.out.println(w.getText());
		
		driver.findElement(By.xpath(or.getProperty("Home_xpath"))).click();
		waitForPageLoad();
		
	}
	
}