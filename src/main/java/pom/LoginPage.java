package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

		WebDriver driver;
		
		public LoginPage(WebDriver driver) {
		
			this.driver = driver;
			PageFactory.initElements(driver, this);
			}
			@FindBy(id = "username")
			private WebElement UN;
			@FindBy(id = "inputPassword")
			private WebElement PWD;
			@FindBy(xpath = "//button[text()='Sign In']")
			private WebElement loginBtn;

			public WebDriver getDriver() {
				return driver;
			}
			public WebElement getUN() {
				return UN;
			}
			public WebElement getPWD() {
				return PWD;
			}
			public WebElement getLoginBtn() {
				return loginBtn;
			}
			
			

	}


