package harcoding;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateProductwithMandatoryField {

	public static void main(String[] args)  {
		ChromeOptions settings=new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		WebDriver driver=new ChromeDriver(settings);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	//	WebElement productId = wait.until(
		        //ExpectedConditions.elementToBeClickable(
		         //       By.name("productId")));

		//productId.sendKeys("101");
		 
		//WebElement ProductId = driver.findElement(By.xpath("//input[@name='productId']"));
		

		//ProductId.clear();
		
		//ProductId.sendKeys("101");
		driver.findElement(By.name("productName")).sendKeys("Charger");
		WebElement Quantity = driver.findElement(By.name("quantity"));
		Quantity.clear();
		Quantity.sendKeys("5");
		WebElement Price = driver.findElement(By.name("price"));
		Price.clear();
		Price.sendKeys("0.05");
		WebElement Category = driver.findElement(By.name("productCategory"));
		Select s=new Select(Category);
		s.selectByVisibleText("Electronics");
		WebElement VendorId = driver.findElement(By.name("vendorId"));
		Select s1=new Select(VendorId);
		s1.selectByVisibleText("Vendor_27589 - (Electronics)");
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg=toastmsg.getText();
		if(msg.contains("Charger"))
		{
			System.out.println("Product is added");
		}
		else
		{
			System.out.println("Product is not added");
		}
		WebElement profile = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(profile).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout']")).click();
		driver.quit();
		
	}

}
