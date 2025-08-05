package ImplementationOfUtilities;
                   
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.CampaignPage;
import ObjectRepositories.HomePage;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;


public class CreateCampaignWithMandatoryFields extends BaseClass {

	@Test
	public void toCreateCampaignWithMandatoryFields() throws EncryptedDocumentException, IOException, InterruptedException {
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//get Data from Properties		
			String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
			String targetSize = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
			
			//Launch Browser - BaseClass
			//Login - BaseClass
		
			//create campaign
			HomePage hp = new HomePage(driver);
			Thread.sleep(2000);
			hp.getCreateCampaignBtn().click();
			CampaignPage cp = new CampaignPage(driver);
		    cp.getCampaignNameTF().sendKeys(campname);
		    cp.getTargetSizeTF().clear();
		    cp.getTargetSizeTF().sendKeys(targetSize);
		    cp.getCreateCampaignSubmitBtn().click();
		
			//validation
		    WebElement toastmsg = hp.getToastmsg();
			wutil.waitForVisibilityOfElement(driver, toastmsg);
			String msg = toastmsg.getText();
			
			if(msg.contains(campname))
			{
				System.out.println("campaign created");
				
			}
		   else
			{
				System.out.println("campaign not created");
				
			}
			
			hp.getClosemsg().click();
			
			//logout - Base Class
			
		}
	

}
