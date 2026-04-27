package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class CreateCampaignPage{

	WebDriver driver;
	public CreateCampaignPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignName")
	private WebElement campaignname;
	@FindBy(name="campaignStatus")
	private WebElement campaignstatus;
	@FindBy(name = "targetSize")
	private WebElement TargetSizeTf;
	@FindBy(name="expectedCloseDate")
	private WebElement ExpectedClosedate;
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement createCampaignSubmitBtn;
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getCampaignname() {
		return campaignname;
	}
	public WebElement getCampaignstatus() {
		return campaignstatus;
	}
	public WebElement getTargetSizeTf() {
		return TargetSizeTf;
	}
	public WebElement getExpectedClosedate() {
		return ExpectedClosedate;
	}
	public WebElement getCreateCampaignSubmitBtn() {
		return createCampaignSubmitBtn;
	}
	
	
}
