package generalUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtility {

		
		public void maximzie(WebDriver driver)
		{
			driver.manage().window().maximize();
		}
		public void implicitwait(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		public void explicitwait(WebDriver driver,WebElement ele)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		public void movetoElement(WebDriver driver,WebElement ele)
		{
			Actions act =new Actions(driver);
			act.moveToElement(ele).perform();
		}
		public void clickonElement(WebDriver driver,WebElement ele)
		{
			Actions act =new Actions(driver);
			act.moveToElement(ele).click().perform();
		}
		public void rightonElement(WebDriver driver,WebElement ele)
		{
			Actions act =new Actions(driver);
			act.contextClick(ele).perform();
		}
		public void doubleclickonElement(WebDriver driver,WebElement ele)
		{
			Actions act =new Actions(driver);
			act.doubleClick(ele).perform();
		}
		public void SelectByIndex(WebElement address, int index)
		{
			Select s=new Select(address);
			s.selectByIndex(index);
		}
		public void SelectByValue(WebElement address, String value)
		{
			Select s=new Select(address);
			s.selectByValue(value);
		}
		public void SelectByVisibletext(WebElement address, String text)
		{
			Select s=new Select(address);
			s.selectByVisibleText(text);
		}
		
		public void switchToWindow(WebDriver driver)
		{
			String parentWindow = driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
			allwindows.remove(parentWindow);
			for (String id : allwindows) {
				driver.switchTo().window(id);
		}
		}
		public void logout(WebDriver driver, WebElement profile)
		{
		    Actions act = new Actions(driver);
		    act.moveToElement(profile).perform();

		    WebDriverWait wait =
		        new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement logout =
		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//div[text()='Logout']")));

		    logout.click();
		}
		public void clickElement(WebDriver driver, WebElement element) 
		{
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		}
		public void PrensenceofElement(WebDriver driver, By locator)
		{
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		public void switchToFrame(WebDriver driver, int index)
		{
			driver.switchTo().frame(index);
		}
		public void switchToFrame(WebDriver driver, String nameorid)
		{
			driver.switchTo().frame(nameorid);
		}
		public void switchToFrame(WebDriver driver, WebElement Framelement) 
		{
			driver.switchTo().frame(Framelement);
		}
		public void switchToAlertAndAccept(WebDriver driver) 
		{
			driver.switchTo().alert().accept();
		}
		public void switchToAlertAnddismiss(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		public String switchToAlertAndGetText(WebDriver driver) 
		{
			String text = driver.switchTo().alert().getText();
			return text;
		}
		public void switchToAlertAndSendkeys(WebDriver driver,String text) 
		{
			driver.switchTo().alert().sendKeys(text);
		}
		public void passInput(WebDriver driver,WebElement element,String text)
		{
			Actions act = new Actions(driver);
			act.click(element).sendKeys(text).perform();
		}
		public void takesScreenshot(WebDriver driver,String filename)throws IOException 
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			File perm=new File("./errorshot/"+filename+".png");
			FileHandler.copy(temp, perm);
		}
		public void toScrollby(WebDriver driver,int x,int y)
		{
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy("+x+","+y+")");
		}
}	

