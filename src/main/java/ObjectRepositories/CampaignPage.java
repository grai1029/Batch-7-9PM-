package ObjectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	WebDriver driver;
	public CampaignPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaignName")
	private WebElement campaignNameTF;
	
	@FindBy(name="campaignStatus")
	private WebElement campaignStatusTF;
	
	@FindBy(name="targetSize")
	private WebElement targetSizeTF;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedCloseDateTF;
	
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement createCampaignSubmitBtn;
	
	
	
	public WebDriver getDriver() {
		return driver;
		
	}

	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}

	public WebElement getCampaignStatusTF() {
		return campaignStatusTF;
	}

	public WebElement getTargetSizeTF() {
		return targetSizeTF;
	}

	public WebElement getExpectedCloseDateTF() {
		return expectedCloseDateTF;
	}

	public WebElement getCreateCampaignSubmitBtn() {
		return createCampaignSubmitBtn;
	}


}
