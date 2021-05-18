package testcases;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC_UsedCars extends Test_base {

	static WebDriverWait wait;
	
	public static TC_HomePage popularModels() {
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600);");
		
		System.out.println("\nThe popular used car models in Chennai are :-\n");
		
		ArrayList<WebElement> al = new ArrayList<WebElement>();
		al = (ArrayList<WebElement>) driver.findElements(By.className(or.getProperty("arraylist_ClassName")));
		
		for(WebElement w:al)
			System.out.println(w.getText());
		
		elementClick("Home_Xpath");
		
		waitForPageLoad();
		
		return(TC_HomePage) PageFactory.initElements(driver,TC_HomePage.class);
		
	}
	
}