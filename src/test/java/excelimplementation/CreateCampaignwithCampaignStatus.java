package excelimplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithCampaignStatus {

	public static void main(String[] args) throws IOException {
		Random r=new Random();
		char randalpha=(char) ('A' + r.nextInt(26));
		FileInputStream fis=new FileInputStream("./src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty("browser");
		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
		WebDriver driver=null;
		if(value.equalsIgnoreCase("edge"))
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
			FileInputStream fis2=new FileInputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
			Workbook wb = WorkbookFactory.create(fis2);
			Sheet sh = wb.getSheet("campaign");
			String campaignname = sh.getRow(1).getCell(0).getStringCellValue();
			String targetsize = sh.getRow(1).getCell(1).getStringCellValue();
			String status = sh.getRow(1).getCell(2).getStringCellValue();
		driver.findElement(By.name("campaignName")).sendKeys(campaignname+randalpha);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys(targetsize);
		driver.findElement(By.name("campaignStatus")).sendKeys(status);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement alert = wait1.until(
		        ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//div[@role='alert']")
		        ));
	
		WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait11.until(ExpectedConditions.visibilityOf(alert));
		String msg=alert.getText();
		if(msg.contains(campaignname))
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


