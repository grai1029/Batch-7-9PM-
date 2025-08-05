package ImplementationOfUtilities;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithStatusOriginal {
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//get data from properties
		String BROWSER = putil.togetDataFromProperties("Browser");
		String URL = putil.togetDataFromProperties("Url");
		String USERNAME = putil.togetDataFromProperties("Username");
		String PASSWORD = putil.togetDataFromProperties("Password");
		//get data from Excel File
		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String status = eutil.toReadDataFromExcelFile("Campaign", 1, 4);
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
							
				driver.manage().window().maximize();
				wutil.waitForPagetoLoad(driver);
				driver.get(URL);
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[text()='Sign In']")).click(); 
				//create campaign
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();	
				driver.findElement(By.name("campaignName")).sendKeys(campname + jutil.togetRandomNumber());
				driver.findElement(By.name("campaignStatus")).sendKeys(status);
				WebElement target = driver.findElement(By.name("targetSize"));
				target.clear();
				target.sendKeys(targetSize);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				Thread.sleep(2000);
				
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
				WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
				wutil.mouseHover(driver, icon);
				WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
				wutil.clickOnWebElement(driver, logout);
				//close browser
				driver.quit();
		
	}
	

}
