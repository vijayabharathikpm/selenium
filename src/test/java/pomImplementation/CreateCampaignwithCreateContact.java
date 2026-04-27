package pomImplementation;


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
import pom.ContactPage;
import pom.CreateCampaignPage;
import pom.HomePage;
import pom.LoginPage;

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
		
		String campaign = campaignname + ju.RandomAlphabet();
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
		ccp.getCampaignname().sendKeys(campaign);
		WebElement size = ccp.getTargetSizeTf();
		size.clear();
		size.sendKeys(targetsize);
		ccp.getCreateCampaignSubmitBtn().click();
		Su.explicitwait(driver, hp.getToastmsg());
		WebElement alert = hp.getToastmsg();
		Su.explicitwait(driver, alert);
			String msg=alert.getText();
			if(msg.contains(campaignname))
			{
			System.out.println("campaing is create");
			}
			else
			{
			System.out.println("campaing is not created");
			}
		WebElement v1 = driver.findElement(By.xpath("//td[text()='"+campaign+"']/preceding-sibling::td[1]"));
		String id = v1.getText();
		WebElement Closemsg=hp.getClosemsg();
		Closemsg.click();
		
		WebElement contact = hp.getContact();
		Su.explicitwait(driver, contact);
		contact.click();
		ContactPage cp=new ContactPage(driver);
		cp.getAddContactButton().click();
		cp.getOrganizationName().sendKeys(organizationname);
		cp.getTitleName().sendKeys(title);
		cp.getContactaName().sendKeys(contactname);
		cp.getMobile().sendKeys(ju.RandomPhoneNumber());
		cp.getAddIcon().click();
		String parentWindow = driver.getWindowHandle();
		Su.switchToWindow(driver);
		cp.getSearchBar().sendKeys(id);
		cp.getSelectButton().click();
		driver.switchTo().window(parentWindow);
		cp.getSubmitButton().click();
		Su.explicitwait(driver, hp.getToastmsg());
		WebElement alert1 = hp.getToastmsg();
		Su.explicitwait(driver, alert1);
		String msg1=alert1.getText();
		if(msg1.contains(contactname))
		{
			System.out.println("Contact is create");
		}
		else
		{
			System.out.println("Contact is not created");
		}
		WebElement profile = hp.getUserIcon();
		hp.getLogout().click();
	

		
		
	}

}
