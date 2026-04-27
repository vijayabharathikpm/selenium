package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginByUsingReadingData {

	public static void main(String[] args) throws IOException {
		//WebDriver driver=new ChromeDriver();
		
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
			//driver.findElement(By.linkText("Campaigns")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create Campaign']"))).click();
			//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			driver.findElement(By.name("campaignName")).sendKeys("viji");
			WebElement size = driver.findElement(By.name("targetSize"));
			size.clear();
			size.sendKeys("5");
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			//wait.until(ExpectedConditions.visibilityOf(toastmsg));
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
