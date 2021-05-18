package utilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {

	public static ExtentReports report;

	public static String getTimeStamp() {
		
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");

	}
	
	public static ExtentReports getReportInstance() {	
		
		if (report == null) {

			String reportName = "HTMLreport_" + getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
					"\\src\\test\\resources\\test_result\\" + reportName);
		
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "10.8.1");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle("Identify New Bikes - HTML Report");
			htmlReporter.config().setReportName("UI Test Report");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}

		return report;
	}

}