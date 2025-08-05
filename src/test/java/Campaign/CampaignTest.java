package Campaign;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.CampaignPage;
import ObjectRepositories.HomePage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class CampaignTest extends BaseClass{
	@Test
     public void toCreateCampaignWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException, InterruptedException {
		
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
				System.out.println("campaign created with mandatory fields");
				
			}
		   else
			{
				System.out.println("campaign not created");
				
			}
			
			hp.getClosemsg().click();
			
			//logout - Base Class
			
		}
	
	@Test(groups="smoke")
	
	public void toCreateCampaignWithStatusTest() throws EncryptedDocumentException, IOException, InterruptedException {	
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//get data from properties
		
		//get data from Excel File
		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String status = eutil.toReadDataFromExcelFile("Campaign", 1, 4);
		String targetSize = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
		
		//Launch Browser - Base Class
		
		//login - BaseClass
				
				
				//create campaign
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();	
				driver.findElement(By.name("campaignName")).sendKeys(campname + jutil.togetRandomNumber());
				driver.findElement(By.name("campaignStatus")).sendKeys(status);
				WebElement target = driver.findElement(By.name("targetSize"));
				target.clear();
				target.sendKeys(targetSize);
				//Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				//Thread.sleep(2000);
				
				//validation
				WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
				wutil.waitForVisibilityOfElement(driver, toastmsg);
				String msg = toastmsg.getText();
				
				if(msg.contains(campname))
				{
					System.out.println("campaign created with Status");
					
				}
			   else
				{
					System.out.println("campaign not created");
					
				}
				
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();
				
				//logout
				
				//close browser
				
		
	}
	
	@Test(groups = "regression")

 public void toCreateCampaignWithExpectedDateTest() throws EncryptedDocumentException, IOException, InterruptedException  {
		
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
