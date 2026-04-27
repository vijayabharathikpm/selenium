package excelimplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
			FileInputStream fis2=new FileInputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
			Workbook wb = WorkbookFactory.create(fis2);
			Sheet sh = wb.getSheet("campaign");
			String campaignname = sh.getRow(1).getCell(0).getStringCellValue();
			String targetsize = sh.getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.name("campaignName")).sendKeys(campaignname+randalpha);
			WebElement size = driver.findElement(By.name("targetSize"));
			size.clear();
			size.sendKeys(targetsize);
			driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
			WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement toastmsg = wait11.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//div[@role='alert']")
		        ));
			String msg=toastmsg.getText();
			if(msg.contains(campaignname))
			{
			System.out.println("campaing is create");
			}
			else
			{
			System.out.println("campaing is not created");
			}
		WebElement v1 = driver.findElement(By.xpath("//td[text()='"+campaignname+randalpha+"']/preceding-sibling::td"));
		String id = v1.getText();
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		Sheet sh2 = wb.getSheet("contacts");
		String organizationame = sh2.getRow(1).getCell(0).getStringCellValue();
		String title = sh2.getRow(1).getCell(1).getStringCellValue();
		String contactname = sh2.getRow(1).getCell(2).getStringCellValue();
		//String mobile = sh2.getRow(1).getCell(3).getStringCellValue();
		driver.findElement(By.name("organizationName")).sendKeys(organizationame);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactname);
	
		String randomPhoneNum = "9" + (100000000 + r.nextInt(900000000));
		driver.findElement(By.name("mobile")).sendKeys(randomPhoneNum);
		driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
		String parentWindow = driver.getWindowHandle();
		//driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> allwindows = driver.getWindowHandles();
		allwindows.remove(parentWindow);
		for (String child : allwindows) {
			driver.switchTo().window(child);
			driver.findElement(By.id("search-input")).sendKeys(id);
			driver.findElement(By.xpath("//button[@class='select-btn']")).click();
		}
		
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
		Actions act=new Actions(driver);
		act.moveToElement(profile).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
	

		
		
	}

}
