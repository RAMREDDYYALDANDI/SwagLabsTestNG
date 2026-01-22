package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseTest.Base;
import reports.ExtentReportManager;

public class TestListener implements ITestListener{
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	ExtentReports extent = ExtentReportManager.extentreports();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		extentTest.set(extent.createTest(result.getMethod().getMethodName()));
		
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "The Test has passed successfull");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		WebDriver driver = null;
		extentTest.get().log(Status.FAIL, "The Test has Failed");
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result
					.getTestClass()
					.getRealClass()
					.getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filepath  = null;
		try {
			filepath  = Base.getscreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath , result.getMethod().getMethodName());
		
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	

}
