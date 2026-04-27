package utilityimplementation;


import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import generalUtility.ExcelUtility;
import generalUtility.JavaUtility;
import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;

public class CreateCampaignwithCampaignStatus {

	public static void main(String[] args) throws IOException {
		JavaUtility ju=new JavaUtility();
		PropertyUtility prop=new PropertyUtility();
		ExcelUtility exl=new ExcelUtility();
		SeleniumUtility Su=new SeleniumUtility();
		String browser = prop.toReadDataFromPropertyFile("browser");
		String url = prop.toReadDataFromPropertyFile("url");
		String username = prop.toReadDataFromPropertyFile("username");
		String pwd = prop.toReadDataFromPropertyFile("password");
		
		String campaignname = exl.toReadDataFromExcel("campaign",1,0);
		String targetsize = exl.toReadDataFromExcel("campaign",1,1);
		String status = exl.toReadDataFromExcel("campaign",1,2);
		
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Browser in not in common property");
			return;
		}
		Su.implicitwait(driver);
		Su.maximzie(driver);
		driver.get(url);
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create Campaign']"))).click();
		driver.findElement(By.name("campaignName")).sendKeys(campaignname+ju.RandomAlphabet());
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys(targetsize);
		driver.findElement(By.name("campaignStatus")).sendKeys(status);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alert = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait11.until(ExpectedConditions.visibilityOf(alert));
		String msg=alert.getText();
		if(msg.contains(campaignname))
		{
			System.out.println("Campaign is created");
		}
		else
		{
			System.out.println("Campaign is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Su.logout(driver, profile);
		driver.quit();
		

	}

	}


