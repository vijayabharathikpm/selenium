package screenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class Facebook_Screenshot {
	@Test
	
	public void screenshot() throws IOException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		Date date=new Date();
		String newdate = date.toString().replace(" ","_").replace(":","_");
		TakesScreenshot ts=(TakesScreenshot) driver;
		File scs = ts.getScreenshotAs(OutputType.FILE);
		
		
		File save = new File("./snaps_"+newdate+".png");
		FileHandler.copy(scs, save);
		driver.quit();
		
		
	}

}
