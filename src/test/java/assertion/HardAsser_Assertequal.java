package assertion;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class HardAsser_Assertequal {
	
	@Test
	
	public void Validate()
	{
		String expTitle = "Facebook";
		
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.facebook.com/");
		@Nullable
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,expTitle);
		System.out.println("hi");
		driver.quit();
		
	}
	

}
