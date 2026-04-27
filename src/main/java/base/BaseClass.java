package base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;


public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver=null;
	public PropertyUtility plib = new PropertyUtility();
	public SeleniumUtility slib = new SeleniumUtility();
	
	@BeforeSuite 
		public void beforesuit(){
		Reporter.log("DB open",true);
	}
   @BeforeClass
   public void beforeclass() throws IOException {
	   
	   
		String BROWSER = plib.toReadDataFromPropertyFile("browser");
		
		//System.setProperty("webdriver.edge.driver", "C:\\Drivers\\msedgedriver\\msedgedriver.exe");
		if(BROWSER.equalsIgnoreCase("Edge")) 
		{
			
			driver = new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("chrome")) {
			//chrome setting for popup 
			ChromeOptions settings = new ChromeOptions(); 
			Map<String, Object> prefs = new HashMap<>(); 
			prefs.put("profile.password_manager_leak_detection", false); 
		    settings.setExperimentalOption("prefs", prefs);	
		    driver = new ChromeDriver(settings);
		}	
		else {
			System.out.println("Invalid broswer name ");
			return;
		} 
		sdriver=driver;
		Reporter.log("Broswer opened", true);
   }
	
   @BeforeMethod
   public void BeforeMethod() throws IOException, InterruptedException {
	  
	   
	 
	   
		String URL = plib.toReadDataFromPropertyFile("url");
		String USERNAME = plib.toReadDataFromPropertyFile("username");
		String PASSWORD = plib.toReadDataFromPropertyFile("password");
	    
		slib.maximzie(driver);
		slib.implicitwait(driver);
		driver.get(URL);
		//login
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("logged in app");
		Thread.sleep(1000);
		Reporter.log("Login success", true);
   }
   @org.testng.annotations.AfterMethod
   public void AfterMethod() {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	   WebElement p1 = wait.until(
			    ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='user-icon']")));
		slib.clickElement(driver, p1);
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		//driver.quit();
		Reporter.log("logout", true);
		
   }
	  @AfterClass
	  public void afterclass() {
		  driver.quit();
		  Reporter.log("clase broswer", true);
	  }
   
   @AfterSuite
   public void aftersuit() {
	   Reporter.log("DB close", true);
   }
}

 


