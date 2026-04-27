package utilityimplementation;


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
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products")));
		productsLink.click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(uniqueProduct);
		
		WebElement Category = driver.findElement(By.name("productCategory"));
		Su.SelectByVisibletext(Category, category);
		
		WebElement Quantity = driver.findElement(By.name("quantity"));
		Quantity.clear();
		Quantity.sendKeys(quantity);
		
		WebElement Price = driver.findElement(By.name("price"));
		Price.clear();
		Price.sendKeys(ppu);
		
		WebElement VendorId = driver.findElement(By.name("vendorId"));
		Su.SelectByVisibletext(VendorId, vendor);
		
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		
		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement alert1 = wait11.until(
		        ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//div[@role='alert']")
		        ));
	
		Su.explicitwait(driver, alert1);
		String msg=alert1.getText();
		if(msg.contains(uniqueProduct))
		{
			System.out.println("product is created");
		}
		else
		{
			System.out.println("product is not created");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		 Su.logout(driver, profile);
		 driver.quit();
		
	}

}
