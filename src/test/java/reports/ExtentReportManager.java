package reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	public static ExtentSparkReporter report;
	public static ExtentReports extent;

	public static ExtentReports extentreports() {

		if (extent == null) {
			String path = System.getProperty("user.dir") + "/test-output/ExtentReports/report.html";
			File file = new File(path);
			file.getParentFile().mkdir();	//this will create the new file 
			
			report = new ExtentSparkReporter(path);

			report.config().setReportName("Web Automation");
			report.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(report);
			extent.setSystemInfo("Test Engineer", "yaldandi ram reddy");
		}
		return extent;

	}

}
