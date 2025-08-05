package ImplementationOfUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepositories.CampaignPage;
import ObjectRepositories.HomePage;
import ObjectRepositories.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithMandatoryFieldsOriginal {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

			//get Data from Properties
			String BROWSER = putil.togetDataFromProperties("Browser");
			String URL = putil.togetDataFromProperties("Url");
			String USERNAME = putil.togetDataFromProperties("Username");
			String PASSWORD = putil.togetDataFromProperties("Password");
					
			//get data from Excel
			String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
			String targetSize = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
			
			//Launch Browser
			WebDriver driver=null;
			
			if(BROWSER.equals("Edge"))
			{
						
			driver =  new EdgeDriver();
					
			}
							
			else if(BROWSER.equals("Chrome"))	
						
			{
			 driver = new ChromeDriver();
						
			}
			else 
			{
			driver= new FirefoxDriver();
			}
				
			//login
			driver.manage().window().maximize();	
			wutil.waitForPagetoLoad(driver);
			driver.get(URL);
			LoginPage lp = new LoginPage(driver);
			lp.getUN().sendKeys(USERNAME);
			lp.getPW().sendKeys(PASSWORD);
			lp.getLoginBtn().click();
		
			//create campaign
			HomePage hp = new HomePage(driver);
			hp.getCreateCampaignBtn().click();
			CampaignPage cp = new CampaignPage(driver);
		    cp.getCampaignNameTF().sendKeys(campname+jutil.togetRandomNumber());
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
			
			//logout
			WebElement icon = hp.getUserIcon();
			wutil.mouseHover(driver, icon);
			WebElement logout = hp.getLogOutBtn();
			wutil.clickOnWebElement(driver, logout);
			//close browser
			driver.quit();
			
		}
		
}
