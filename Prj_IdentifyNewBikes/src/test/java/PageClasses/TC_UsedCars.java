package PageClasses;

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
	
		//Scrolling down the page until "Popular Models" list is visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600);");
		
		System.out.println("\nThe popular used car models in Chennai are :-\n");
		
		//Creating an ArrayList to store the details
		ArrayList<WebElement> al = new ArrayList<WebElement>();
		al = (ArrayList<WebElement>) driver.findElements(By.className(or.getProperty("arraylist_ClassName")));
		
		//Displaying all the popular models on the console
		for(WebElement w:al)
			System.out.println(w.getText());
		
		//Navigating back to the HomePage
		elementClick("Home_Xpath");
		
		waitForPageLoad();
		
		return(TC_HomePage) PageFactory.initElements(driver,TC_HomePage.class);
		
	}
	
}