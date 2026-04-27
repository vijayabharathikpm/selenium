package pomImplementation;




import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
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
			System.out.println("Campaign is created");
		}
		else
		{
			System.out.println("Campaign is not created");
		}
		WebElement profile = hp.getUserIcon();
		Su.logout(driver, profile);
		
		hp.getLogout().click();
		driver.quit();
		
		
		
		
		
		
	}
}
