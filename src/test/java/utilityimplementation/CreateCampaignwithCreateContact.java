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

public class CreateCampaignwithCreateContact {

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
		
		String organizationname = exl.toReadDataFromExcel("contacts", 1, 0);
		String title = exl.toReadDataFromExcel("contacts", 1, 1);
		String contactname = exl.toReadDataFromExcel("contacts", 1, 2);
		
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("Edge"))
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
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement toastmsg = wait11.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@role='alert']")));
			String msg=toastmsg.getText();
			if(msg.contains(campaignname))
			{
			System.out.println("campaing is create");
			}
			else
			{
			System.out.println("campaing is not created");
			}
		WebElement v1 = driver.findElement(By.xpath("//td[text()='"+campaignname+ju.RandomAlphabet()+"']/preceding-sibling::td"));
		String id = v1.getText();////td[text()='newworldZ']/preceding-sibling::td
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		driver.findElement(By.name("organizationName")).sendKeys(organizationname);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactname);
		driver.findElement(By.name("mobile")).sendKeys(ju.RandomPhoneNumber());
		driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
		String parentWindow = driver.getWindowHandle();
		Su.switchToWindow(driver);
		driver.findElement(By.id("search-input")).sendKeys(id);
		driver.findElement(By.xpath("//button[@class='select-btn']")).click();
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement alert = wait1.until(
		        ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//div[@role='alert']")
		        ));
	
		WebDriverWait wait111=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait111.until(ExpectedConditions.visibilityOf(alert));
		String msg1=alert.getText();
		if(msg1.contains(contactname))
		{
			System.out.println("Contact is create");
		}
		else
		{
			System.out.println("Contact is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Su.logout(driver, profile);
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
	

		
		
	}

}
