package baseClass;


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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import generalUtility.ExcelUtility;
import generalUtility.JavaUtility;
import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;
import pom.CreateCampaignPage;
import pom.HomePage;
import pom.LoginPage;

@Listeners(listenerUtility.ListenerImplenetation.class)
public class CampaignTest extends BaseClass{ 
	@Test

	public void toCreateCampaignWithMandatoryFieldsTest() throws IOException {
	{
		ExcelUtility eutil = new ExcelUtility(); 
		JavaUtility jutil = new JavaUtility(); 
		SeleniumUtility wutil = new SeleniumUtility(); 
		String campname = eutil.toReadDataFromExcel("Campaign", 1, 0); 
		String target = eutil.toReadDataFromExcel("Campaign", 1, 1);
// create campaign 
		HomePage hp = new HomePage(driver);
		hp.getCreateCampaign().click();
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.getCampaignname().sendKeys(campname);
		cp.getTargetSizeTf().sendKeys(target); 
		cp.getCreateCampaignSubmitBtn().click(); 
		
		// validation 
		WebElement toastmsg = hp.getToastmsg();
		wutil.explicitwait(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		hp.getClosemsg().click();
		
		driver.quit();
	}
	}
}

