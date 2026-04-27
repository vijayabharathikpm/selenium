package pomImplementation;


import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import generalUtility.ExcelUtility;
import generalUtility.JavaUtility;
import generalUtility.PropertyUtility;
import generalUtility.SeleniumUtility;
import pom.CreageProjectPage;
import pom.HomePage;
import pom.LoginPage;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateProductwithMandatoryField {

	public static void main(String[] args) throws IOException  {
		JavaUtility ju=new JavaUtility();
		PropertyUtility prop=new PropertyUtility();
		ExcelUtility exl=new ExcelUtility();
		SeleniumUtility Su=new SeleniumUtility();
		
		String browser = prop.toReadDataFromPropertyFile("browser");
		String url = prop.toReadDataFromPropertyFile("url");
		String username = prop.toReadDataFromPropertyFile("username");
		String pwd = prop.toReadDataFromPropertyFile("password");
		
		String productname = exl.toReadDataFromExcel("Product", 1, 0);
        String quantity = exl.toReadDataFromExcel("Product", 1, 1);
        String ppu = exl.toReadDataFromExcel("Product", 1, 2);
        String category = exl.toReadDataFromExcel("Product", 1, 3);
        String vendor = exl.toReadDataFromExcel("Product", 1, 4);
        
        String uniqueProduct = productname + ju.RandomNumber();
		
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
		LoginPage lp=new LoginPage(driver);
		lp.getUN().sendKeys(username);
		lp.getPWD().sendKeys(pwd);
		lp.getLoginBtn().click();
		HomePage hp=new HomePage(driver);
		Su.implicitwait(driver);
		WebElement productsLink = hp.getProduct();
		Su.explicitwait(driver, productsLink);
		productsLink.click();
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlContains("products"));
		CreageProjectPage cpp=new CreageProjectPage(driver);
		
		WebElement addProduct = cpp.getAddproduct();

		addProduct.click();
		cpp.getProductName().sendKeys(uniqueProduct);
		
		WebElement Category = cpp.getProductcategory();
		Su.SelectByVisibletext(Category, category);
		
		WebElement Quantity = cpp.getQuantity();
		Quantity.clear();
		Quantity.sendKeys(quantity);
		
		WebElement Price = cpp.getPrice();
		Price.clear();
		Price.sendKeys(ppu);
		
		WebElement VendorId = cpp.getVendorid();
		Su.SelectByVisibletext(cpp.getVendorid(), vendor);
		
		cpp.getAddproductbutton().click();
		//Su.explicitwait(driver, hp.getToastmsg());
		WebElement alert = hp.getToastmsg();
		Su.explicitwait(driver, alert);
		String msg=alert.getText();
		if(msg.contains(uniqueProduct))
		{
			System.out.println("product is created");
		}
		else
		{
			System.out.println("product is not created");
		}
		WebElement profile = hp.getUserIcon();
		Su.logout(driver, profile);
		
		driver.quit();
	}

}
