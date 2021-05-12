package base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Test_base {

	public static Properties config = new Properties();
	public static FileInputStream fconfig;
	public static WebDriver driver;
	public static Properties or = new Properties();
	public static FileInputStream org;
	public static Scanner sc = new Scanner(System.in);
	
	public static void waitForPageLoad() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete"))
				break;
			else
				waitLoad(1);
		}

		waitLoad(1);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState)
				break;
			else
				waitLoad(1);
		}
	}

	public static void waitLoad(int i) {
		
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}