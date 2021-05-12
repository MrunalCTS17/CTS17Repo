package temp;

import java.io.FileInputStream;
import java.io.IOException;
import base.Test_base;

public class ReadConfig extends Test_base{

	public static void main(String[] args) throws IOException {

		fconfig = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		config.load(fconfig);
		
		//Reading the properties file
		System.out.println(config.getProperty("browser"));
		System.out.println(config.getProperty("testURL"));
		
	}

}