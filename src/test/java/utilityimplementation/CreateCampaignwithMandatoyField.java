package utilityimplementation;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

import generalUtility.ExcelUtility;
import generalUtility.JavaUtility;
import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;
import pom.CreateCampaignPage;
import pom.HomePage;
import pom.LoginPage;

public class CreateCampaignwithMandatoyField
{
	public static void main(String[] args) throws IOException
	{
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

			LoginPage lp=new LoginPage(driver);
			lp.getUN().sendKeys(username);
			lp.getPWD().sendKeys(pwd);
			lp.getLoginBtn().click();
			HomePage hp=new HomePage(driver);
			Su.explicitwait(driver, hp.getCreateCampaign());
			hp.getCreateCampaign().click();
			CreateCampaignPage ccp=new CreateCampaignPage(driver);
			ccp.getCampaignname().sendKeys(campaignname+ju.RandomAlphabet());
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		Su.explicitwait(driver, toastmsg);
		String msg=toastmsg.getText();
		if(msg.contains(campaignname))
		{
			System.out.println("Campaign is create");
		}
		else
		{
			System.out.println("Campaign is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Su.logout(driver, profile);
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
		driver.quit();
		
		
		
		
		
		
	}
}
