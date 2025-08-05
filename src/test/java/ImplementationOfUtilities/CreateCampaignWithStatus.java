package ImplementationOfUtilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithStatus extends BaseClass{
	
	@Test

	public void toCreateCampaignWithStatus() throws EncryptedDocumentException, IOException, InterruptedException {	
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
					System.out.println("campaign created");
					
				}
			   else
				{
					System.out.println("campaign not created");
					
				}
				
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();
				
				//logout
				
				//close browser
				
		
	}

}
