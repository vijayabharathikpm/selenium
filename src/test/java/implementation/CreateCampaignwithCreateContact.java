package implementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithCreateContact {

	public static void main(String[] args) throws IOException {
		
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
		driver.findElement(By.name("campaignName")).sendKeys("viji3"+randalpha);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("5");
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebElement CampaingId = driver.findElement(By.xpath("/td[text()='Viji3]/preceding-sibling::td"));
		String CID = CampaingId.getText();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		driver.findElement(By.name("organizationName")).sendKeys("jsp");
		driver.findElement(By.name("title")).sendKeys("NINZA");
		driver.findElement(By.name("contactName")).sendKeys("Bharu");
		driver.findElement(By.name("mobile")).sendKeys("1234567810");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> allWindows = driver.getWindowHandles();
		for(String win : allWindows)
		{
		    if(!win.equals(parentWindow))
		    {
		        driver.switchTo().window(win);
		        break;
		    }
		}
		driver.findElement(By.id("search-input")).sendKeys(CID);
		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.close();
		driver.switchTo().window(parentWindow);
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait11.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg=toastmsg.getText();
		if(msg.contains("Bharu"))
		{
			System.out.println("Contact is create");
		}
		else
		{
			System.out.println("Contact is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(profile).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
	

		
		
	}

}
