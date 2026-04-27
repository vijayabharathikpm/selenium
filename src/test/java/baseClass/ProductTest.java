package baseClass;


import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;
import generalUtility.ExcelUtility;
import generalUtility.JavaUtility;
import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;
import pom.CreageProjectPage;
import pom.CreateCampaignPage;
import pom.HomePage;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductTest extends BaseClass
{ 
	@Test
	public void toCreateProductTest() throws EncryptedDocumentException, IOException, InterruptedException 
	{ 
		ExcelUtility eutil = new ExcelUtility(); 
		JavaUtility jutil = new JavaUtility();
		SeleniumUtility wutil = new SeleniumUtility(); 
		String prodname = eutil.toReadDataFromExcel("Product", 1, 0);
		String quantity1 = eutil.toReadDataFromExcel("Product", 1, 1);
		String price1 = eutil.toReadDataFromExcel("Product", 1, 2); 
		// enter details
		HomePage hp = new HomePage(driver);
		hp.getProduct().click();
		CreageProjectPage pp = new CreageProjectPage(driver);
		pp.getAddproduct().click();
		pp.getProductName().sendKeys(prodname + jutil.RandomNumber()); 
		WebElement categorydropdown = pp.getProductcategory();
		// Dropdown
		wutil.select(categorydropdown, 3);
WebElement quantity = pp.getQuantity();
quantity.clear();
quantity.sendKeys(quantity1); 
WebElement price = pp.getPrice(); 
price.clear(); price.sendKeys(price1); 
// DropDown 2
WebElement vendordropdown = pp.getVendorid(); 
wutil.select(vendordropdown, 1); 
pp.getAddProdSubmitBtn().click(); 
Thread.sleep(2000); 
CreateCampaignPage cp = new CreateCampaignPage(driver); 
cp.getClosemsg().click();
	}
}
