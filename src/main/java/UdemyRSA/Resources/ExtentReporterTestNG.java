package UdemyRSA.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/** Define Extent Report
 * 
 * Need to remember and know these 2 classes
 * when dealing with Extent Report
 * helpful to generate the reports
 *  - ExtentReports
 *  - ExtentSpartkReporter
 * 
 */


public class ExtentReporterTestNG {
	
	public static ExtentReports getReportObject() {			// -> add "static" to be able to access without declaring the class
		
		/** ExtentSpartkReporter 
		 *  -> response to generate html file and do some configurations
		 **/
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("UdemyRSA Test Results");
		
		/** ExtentReports 
		 *  -> attach all defined configuration (from ExtentSparkReporter) into this main class
		 *  -> response to create & consolidate all the test execution
		 **/				
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Thao Nguyen");
//		extent.createTest(path);
		
		return extent;
		
	}
	
}
