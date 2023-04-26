package UdemyRSA.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import UdemyRSA.PageObjects.LandingPage;

/**
 * This will be the Base class for all Test classes
 */
public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		
		/** Properties class */ 		// -> to read global properties
		Properties prop = new Properties();
		//Convert files into InputStream to be able to load the file properly
//		FileInputStream fis = new FileInputStream("D:\\Selenium\\Udemy_Selenium-Framework-Design\\SeleniumFrameworkDesign\\src\\main\\java\\UdemyRSA\\Resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\UdemyRSA\\Resources\\GlobalData.properties");
		prop.load(fis);
		
//		String broswerName = prop.getppoProperty("browser");
		// Edit to add scripts for maven execution
		// from Maven command: mvn test -Dbrowser=chrome
		// -> if it is null, get the one from GlobalData.properties
		String broswerName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		
		if(broswerName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			/** tell the driver to simply accept the Insecure Certificates whenever meets it */
//			options.setAcceptInsecureCerts(true);
			
			/** block/unblock pop-up windows */
//			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			
			/** set download directory */
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			prefs.put("download.default_directory", "/directory/path");
//			options.setExperimentalOption("preps", prefs);
			
			/** set add-on extensions go with browser driver */
//			options.addExtensions("filepaths");		
			
			/** set proxy will be used for browser driver */
//			Proxy proxy = new Proxy();
//			proxy.setHttpProxy("192.168.1.0:4444");
//			options.setCapability("proxy", proxy); 	// -> browser will start with proxy
			
			if(broswerName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //(Optional) help to run full screen even in headless mode
		}else if(broswerName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(broswerName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
//		Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		return driver;
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read jsonFile to String
		String jsonContent = FileUtils.readFileToString(
//				new File(System.getProperty("user.dir")+"\\src\\test\\java\\UdemyRSA\\Tests\\Data\\PurchaseOrder.json"),
				new File(filePath),
				StandardCharsets.UTF_8);
		
		//Need to convert String to HashMap: "Jackson Databind"
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir")+"\\reports\\screenshots\\"+testCaseName+".png";
		File destFile = new File(destFilePath);
		FileUtils.copyFile(sourceFile, destFile);
		return destFilePath;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		return landingPage;
	}
	
//	public LandingPage launchApplication() throws IOException {
//		driver = initializeDriver();
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		
//		return landingPage;
//	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
