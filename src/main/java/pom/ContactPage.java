package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	public ContactPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//span[text()='Create Contact']")
	private WebElement AddContactButton;
	
	@FindBy(name="organizationName")
	private WebElement OrganizationName;
	
	@FindBy(name="title")
	private WebElement TitleName;
	
	@FindBy(name="contactName")
	private WebElement ContactaName;
	
	@FindBy(name="mobile")
	private WebElement Mobile;
	
	@FindBy(xpath="//*[name()='svg' and @data-icon='plus']")
	private WebElement AddIcon;
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement SearchBar;
	
	@FindBy(xpath ="//button[@class='select-btn']")
	private WebElement SelectButton;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement SubmitButton;

	public WebElement getAddContactButton() {
		return AddContactButton;
	}

	public WebElement getOrganizationName() {
		return OrganizationName;
	}

	public WebElement getTitleName() {
		return TitleName;
	}

	public WebElement getContactaName() {
		return ContactaName;
	}

	public WebElement getMobile() {
		return Mobile;
	}

	public WebElement getAddIcon() {
		return AddIcon;
	}

	public WebElement getSearchBar() {
		return SearchBar;
	}

	public WebElement getSelectButton() {
		return SelectButton;
	}

	public WebElement getSubmitButton() {
		return SubmitButton;
	}
	
	
	
}
