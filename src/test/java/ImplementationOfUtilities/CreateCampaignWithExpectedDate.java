package ImplementationOfUtilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.CampaignPage;
import ObjectRepositories.HomePage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithExpectedDate extends BaseClass {
	@Test
	public void toCreateCampaignWithExpectedDate() throws EncryptedDocumentException, IOException, InterruptedException  {
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
	
				//Get data from Excel
				String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
				String targetSize = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
				
				//Launch Browser - Base Class
						
				//Login - Base Class
			
				//Date after 30 days
				String daterequired = jutil.togetRequiredDate(30);
						
				//create campaign			
			    HomePage hp = new HomePage(driver);
				Thread.sleep(6000);
				hp.getCreateCampaignBtn().click();
				CampaignPage cp = new CampaignPage(driver);
			    cp.getCampaignNameTF().sendKeys(campname);
			    cp.getTargetSizeTF().clear();
			    cp.getTargetSizeTF().sendKeys(targetSize);
			    WebElement expClosedate = cp.getExpectedCloseDateTF();	
			    wutil.passInput(driver, expClosedate, daterequired);
			    cp.getCreateCampaignSubmitBtn().click();
				
				//validation 
			    WebElement toastmsg = hp.getToastmsg();
				wutil.waitForVisibilityOfElement(driver, toastmsg);
				String msg = toastmsg.getText();
				
				if(msg.contains(campname))
				{
					System.out.println("campaign created with expected date");
					
				}
			   else
				{
					System.out.println("campaign not created");
					
				}
	
				hp.getClosemsg().click();
				
				//logout
				
				//close browser
			
	}

}
