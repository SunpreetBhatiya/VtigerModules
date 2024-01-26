package CommonUtils;

import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplemenation  implements ITestListener{
	
	ExtentReports rp;
	public ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("Test Script is Started",true);		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodname=result.getMethod().getMethodName();
		//Reporter.log(methodname + "Test Script is Passed",true);
		test.log(Status.PASS, methodname + "Test Script is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String message = result.getThrowable().toString();
		String methodname=result.getMethod().getMethodName();
		//Reporter.log(methodname + "Test Script is Failed" + message,true);	
		test.log(Status.FAIL, methodname + "Test Script is Failed");
		test.log(Status.FAIL, result.getThrowable());
		WebDriverutils utils = new WebDriverutils();
		try {
			utils.takeScreenShot1(BaseClass.sdriver, "Contact name");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
    //resutl will capture code in runtime
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname=result.getMethod().getMethodName();
		//Reporter.log(methodname + "Test Script is Skipped",true);
		test.log(Status.SKIP, methodname + "Test Script is Skipped");
		
		
	}

	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReport/Report.html");
        htmlreport.config().setDocumentTitle("ExtentsReport");
		htmlreport.config().setReportName("Vtiger");
		htmlreport.config().setTheme(Theme.DARK);
		rp=new ExtentReports();
        rp.attachReporter(htmlreport);
	    rp.setSystemInfo("Operating System", "Windows");
	    rp.setSystemInfo("Browser", "Chrome");
        rp.setSystemInfo("Chrome Version", "120.060");
        rp.setSystemInfo("Programming Language", "Java");
        rp.setSystemInfo("Author", "Sunpreet");
		
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		rp.flush();
		
	}

	
	
	
	
	
	
}
