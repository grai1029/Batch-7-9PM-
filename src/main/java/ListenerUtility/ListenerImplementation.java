package ListenerUtility;

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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class ListenerImplementation implements ISuiteListener, ITestListener {
	public ExtentReports report;
	public ExtentSparkReporter spark;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);
		Date d = new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");
		spark=new ExtentSparkReporter("./AdvanceReport/report_"+newDate+".html");
		spark.config().setDocumentTitle("NinzaCRM Test Suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("====="+result.getMethod().getMethodName()+"Execution STARTED====",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("===="+result.getMethod().getMethodName()+"SUCCESS====",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reporter.log("===="+testName+"FAILURE=====",true);
		Date d = new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");
		
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
	     File temp = ts.getScreenshotAs(OutputType.FILE);
	     File per = new File("./Screenshot/takeSS_"+newDate+".png");
	     FileHandler.copy(temp, per);
	     
	     try {
	    	 FileHandler.copy(temp, per)
	    	 
	     } catch (IOException e){
	    	 
	    	 e.printStackTrace();
	     }
	     
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("===="+result.getMethod().getMethodName()+"SKIPPED====",true);
	}



}
