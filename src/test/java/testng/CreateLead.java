package testng;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateLead {

	public static void main(String[] args) {
		Random r=new Random();
		char randalpha1=(char) ('A' + r.nextInt(26));
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create Campaign']"))).click();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("viji3"+randalpha1);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("5");
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebElement CampaingId = driver.findElement(By.xpath("/td[text()='Viji3]/preceding-sibling::td"));
		String CID = CampaingId.getText();
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg=toastmsg.getText();
		if(msg.contains("arun"))
		{
			System.out.println("Campaign is create");
		}
		else
		{
			System.out.println("Campaign is not created");
		}
		
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//span[text()='Create Lead']")).click();
		driver.findElement(By.name("name")).sendKeys("Arun");
		driver.findElement(By.name("company")).sendKeys("BMW");
		driver.findElement(By.name("leadSource")).sendKeys("WEB");
		driver.findElement(By.name("industry")).sendKeys("Manufacturing");
		driver.findElement(By.name("phone")).sendKeys("1234567890");
		driver.findElement(By.name("leadStatus")).sendKeys("active");
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
		String ParentId=driver.getWindowHandle();
		Set<String> allWindows=driver.getWindowHandles();
		for(String id : allWindows)
		{
			if(!id.equals(ParentId))
			{
				driver.switchTo().window(id);
			}
		}
		driver.findElement(By.id("search-input")).sendKeys(CID);
		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.findElement(By.xpath("//button[text()='Create Lead']")).click();
	

	}

}
