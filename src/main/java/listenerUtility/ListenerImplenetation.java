package listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ListenerImplenetation implements ITestListener,ISuiteListener{
	
	ExtentSparkReporter spark;
	ExtentReports reports;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		Date date=new Date();
		String newdate = date.toString().replace(" ","_").replace(":","_");
		spark=new ExtentSparkReporter("./AdvanceReport/report_"+newdate+".html");
		spark.config().setDocumentTitle("NinzaCRM");
		spark.config().setReportName("CRM results");
		spark.config().setTheme(Theme.DARK);
		
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "Windows11");
		Reporter.log("report config",true);
		
	
	}

	@Override
	public void onFinish(ISuite suite) {
		reports.flush();
		Reporter.log("report backup",true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		test =reports.createTest(testName);
		test.log(Status.INFO,"==="+testName+"Execution Started===");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		test.log(Status.PASS,"==="+testName+"Execution Success===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		
		
		
		Date date=new Date();
		String newdate = date.toString().replace(" ","_").replace(":","_");
		TakesScreenshot ts=(TakesScreenshot) BaseClass.sdriver;
		  String temp = ts.getScreenshotAs(OutputType.BASE64);
		  test.addScreenCaptureFromBase64String(temp);
		
		test.log(Status.FAIL,"==="+testName+"Execution Failed===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		test.log(Status.SKIP,"==="+testName+"Execution Skipped===");
	}

}
