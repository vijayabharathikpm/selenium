package assertion;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class Softassert_assertequals {
	
	@Test
	
	public void Validate()
	{
		String expTitle = "Faceook";
		
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.facebook.com/");
		@Nullable
		String actTitle = driver.getTitle();
		SoftAssert soft=new SoftAssert();
		
		soft.assertEquals(actTitle,expTitle);
		System.out.println("hi");
		soft.assertAll();
		driver.quit();
		
	}
}
