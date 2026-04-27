package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreageProjectPage {
	WebDriver driver;
	public CreageProjectPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
		@FindBy(xpath = "//button[text()='Add']")
		private WebElement Addproductbutton;
		public WebElement getAddproductbutton() {
		return Addproductbutton;
		}
		@FindBy(name = "productName")
		private WebElement productName;
		@FindBy(name = "productCategory")
		private WebElement productcategory;
		@FindBy(name = "quantity")
		private WebElement quantity;
		@FindBy(name = "price")
		private WebElement price;
		@FindBy(name = "vendorId")
		private WebElement vendorid;
		@FindBy(xpath ="//button[@class='btn btn-info']")
		private WebElement Addproduct;
		public WebElement getProductName() {
			return productName;
		}
		public WebElement getProductcategory() {
			return productcategory;
		}
		public WebElement getQuantity() {
			return quantity;
		}
		public WebElement getPrice() {
			return price;
		}
		public WebElement getVendorid() {
			return vendorid;
		}
		public WebElement getAddproduct() {
			return Addproduct;
		}
		
		

}
