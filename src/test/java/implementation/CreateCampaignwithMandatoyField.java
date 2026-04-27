package implementation;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithMandatoyField
{
	public static void main(String[] args) throws IOException
	{
		/*ChromeOptions settings=new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		WebDriver driver=new ChromeDriver(settings);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();*/
		Random r=new Random();
		char randalpha=(char) ('A' + r.nextInt(26));
		FileInputStream fis=new FileInputStream("./src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty("browser");
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=null;
		if(value.equalsIgnoreCase("chrome"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Browser in not in common property");
			return;
		}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.manage().window().maximize();
			String url = prop.getProperty("url");
			driver.get(url);
			String username = prop.getProperty("username");
			driver.findElement(By.name("username")).sendKeys(username);
			String pwd = prop.getProperty("password");
			driver.findElement(By.id("inputPassword")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create Campaign']"))).click();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Bharu"+randalpha);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("5");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg=toastmsg.getText();
		if(msg.contains("Bharu"))
		{
			System.out.println("Campaign is create");
		}
		else
		{
			System.out.println("Campaign is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(profile).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
		driver.quit();
		
		
		
		
		
		
	}
}
