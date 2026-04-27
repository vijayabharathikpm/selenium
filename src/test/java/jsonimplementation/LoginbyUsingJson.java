package jsonimplementation;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginbyUsingJson {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader reader=new FileReader("./src\\test\\resources\\CommonData.Json");
		JSONParser parser=new JSONParser();
		Object javaobj = parser.parse(reader);
		JSONObject jsonobj=(JSONObject)javaobj;
		String browser = jsonobj.get("browser").toString();
		String url = jsonobj.get("url").toString();
		String username = jsonobj.get("username").toString();
		String pwd = jsonobj.get("password").toString();
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.manage().window().maximize();
			driver.get(url);
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.id("inputPassword")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();

	}

}
