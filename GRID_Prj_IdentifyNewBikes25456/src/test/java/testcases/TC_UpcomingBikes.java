package testcases;

import java.util.ArrayList;
import java.util.Collections;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import base.Test_base;

@Listeners(listeners.customlistener.class)
public class TC_UpcomingBikes extends Test_base {
	
	static WebElement ViewMoreBikes;
	static ArrayList<String> bikeModelsElements,upcomingBikes;
    
    public static void makeHonda() {
    	
		//Selecting Honda from Dropdown
		selectFromDropdown("Honda_Id", "Honda");
		
	}
	
	public static void hondaBikesDetail() {
		
		ViewMoreBikes=getElement("Ele1_CSS");

		waitForPageLoad();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ViewMoreBikes);
        
        //ViewMoreBikes.click();
        js.executeScript("arguments[0].click()", ViewMoreBikes);
        
		String bikeModels = getElementText("BikeModel_Xpath");
		
		//Storing the info in an ArrayList		
		bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements,bikeModels.split("\n"));
		
	}
	
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
	
	public static TC_HomePage displayingDetails() {
	
		
		//Printing them
		System.out.println("\nUpcoming Honda Bikes Below 4 Lakhs are as follows:\n");
		System.out.println("   Bike Name                    Price       Exp. Launch");
		
		for(int i = 0 ; i < upcomingBikes.size(); i++){
			System.out.println(upcomingBikes.get(i));
		}
				
		elementClick("Home_Xpath");
		
		waitForPageLoad();
		
		return(TC_HomePage) PageFactory.initElements(driver,TC_HomePage.class);
	}

}