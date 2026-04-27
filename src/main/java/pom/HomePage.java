package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[text()='Create Contact']")
	private WebElement contact;
	@FindBy(linkText = "Campaigns")
	private WebElement campaign;
	@FindBy(linkText = "Products")
	private WebElement product;
	public WebElement getContact() {
		return contact;
	}
	public WebElement getContactslink() {
		return contactslink;
	}
	@FindBy(linkText="Contacts")
	private WebElement contactslink;
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement createCampaign;
	@FindBy(className = "user-icon")
	private WebElement UserIcon;
	@FindBy(xpath = "//div[text()='Logout ']")
	private WebElement Logout;
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastmsg;
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closemsg;
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getCampaign() {
		return campaign;
	}
	public WebElement getProduct() {
		return product;
	}
	public WebElement getCreateCampaign() {
		return createCampaign;
	}
	public WebElement getUserIcon() {
		return UserIcon;
	}
	public WebElement getLogout() {
		return Logout;
	}
	public WebElement getToastmsg() {
		return toastmsg;
	}
	public WebElement getClosemsg() {
		return closemsg;
	}

	}
	

	


