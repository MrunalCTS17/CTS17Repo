package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC3_UpcomingBikes extends Test_base {
	
	static WebElement UpBikes, ViewMoreBikes;
	static ArrayList<String> bikeModelsElements,upcomingBikes;
	
	
	@Test(groups="upBikes",dependsOnGroups="getURL",priority=1)
    public static void newUpcomingBikes() throws IOException {
    	
		Actions action = new Actions(driver);
		
		UpBikes = driver.findElement(By.linkText(or.getProperty("Ele_Lnktxt")));
		action.moveToElement(UpBikes).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(or.getProperty("UCBks_xpath")))));
		
		driver.findElement(By.xpath(or.getProperty("UCBks_xpath"))).click();
		
    }
	
    
	@Test(groups="upBikes",dependsOnGroups="getURL",priority=2)
    public static void makeHonda() {
    	
		//Selecting Honda from Dropdown
		Select dropdown = new Select(driver.findElement(By.id(or.getProperty("Honda_id"))));
		dropdown.selectByVisibleText("Honda");
		
	}
	
	
	@Test(groups="upBikes",dependsOnGroups="getURL",priority=3)
	public static void hondaBikesDetail() {
		
		ViewMoreBikes=driver.findElement(By.cssSelector(or.getProperty("Ele1_CSS")));

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ViewMoreBikes);
        
        //ViewMoreBikes.click();
        js.executeScript("arguments[0].click()", ViewMoreBikes);
        
		String bikeModels = driver.findElement(By.xpath(or.getProperty("BikeModel_xpath"))).getText();
		
		//Storing the info in an ArrayList		
		bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements,bikeModels.split("\n"));
		
	}
	
	
	@Test(groups="upBikes",dependsOnGroups="getURL",priority=4)
	public static void sortingDetails() {
		
		//Sorting the information according to names,dates and prices
		ArrayList<String> NameList = new ArrayList<String>();
		ArrayList<String> DateList = new ArrayList<String>();
		ArrayList<String> PriceList =new ArrayList<String>();
		
		String[] arr = null;
		
		for(int i = 0 ; i < bikeModelsElements.size(); i++){
			String s = bikeModelsElements.get(i);
			
			if(s.contains("Honda")){
				NameList.add(s);
			}else if(s.contains("Rs. ")){
				arr = s.split(" ");
				PriceList.add(arr[1]);
			}else if(s.contains("Exp. Launch")){
				String[] date = s.split(":");
				DateList.add(date[1]);
			}
		}
		
		//Creating an Arraylist which will add only the upcoming bikes under 4 Lakhs
		upcomingBikes = new ArrayList<String>();
		
			for(int i=0; i<NameList.size();i++){
				String temp = NameList.get(i);
			
				if (temp.length()<26){
					for (int j=temp.length();j<=25;j++)
						temp=temp.concat(" ");
			}
			
			double price = Double.parseDouble(PriceList.get(i));
			String info=temp+"     "+PriceList.get(i)+" Lakh    "+DateList.get(i);
			
			if(info.contains(temp)){
				if(Double.compare(price, 4d)<0){
					upcomingBikes.add(info);
				}
			}
		}
	}
	
	
	@Test(groups="upBikes",dependsOnGroups="getURL",priority=5)
	public static void displayingDetails() {
	
		//Reading the input from excel file
		String upcomingBikesManufacturer="Honda";
		
		//Printing them
		System.out.println("\nUpcoming "+upcomingBikesManufacturer+" Bikes Below 4 Lakhs are as follows:\n");
		System.out.println("   Bike Name                    Price       Exp. Launch");
		
		for(int i = 0 ; i < upcomingBikes.size(); i++){
			System.out.println(upcomingBikes.get(i));
		}
				
		driver.findElement(By.xpath(or.getProperty("Home_xpath"))).click();
		
		waitForPageLoad();
		
	}

}