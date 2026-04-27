package harcoding;

import java.time.Duration;
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

	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Bharuu");
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("5");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		WebElement CampaingId = driver.findElement(By.xpath("/td[text()='Bharuu']/preceding-sibling::td"));
		String CID = CampaingId.getText();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		driver.findElement(By.name("organizationName")).sendKeys("QSP");
		driver.findElement(By.name("title")).sendKeys("NINZA");
		driver.findElement(By.name("contactName")).sendKeys("Bharu");
		driver.findElement(By.name("mobile")).sendKeys("1234567890");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
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
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(toastmsg));
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
