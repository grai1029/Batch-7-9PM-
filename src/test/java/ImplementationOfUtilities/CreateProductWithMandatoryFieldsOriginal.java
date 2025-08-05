package ImplementationOfUtilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepositories.HomePage;
import ObjectRepositories.LoginPage;
import ObjectRepositories.ProductPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateProductWithMandatoryFieldsOriginal {
	public static void main(String[] args) throws IOException, InterruptedException {	
		//get Data from Properties File
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
				String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
				String quantity1 = eutil.toReadDataFromExcelFile("Product", 1, 1);
				String price1 = eutil.toReadDataFromExcelFile("Product", 1, 2);
				
				//Selecting Browser driver
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
				
				//create product
				HomePage hp = new HomePage(driver);
				hp.getProduct().click();
				ProductPage pp= new ProductPage(driver);
				pp.getAddProductBtn().click();
				pp.getProductNameTF().sendKeys(prodname+jutil.togetRandomNumber());
				pp.getProductCategoryDd();
				WebElement pc= pp.getProductCategoryDd(); //dropdown
				Select sel1 = new Select(pc);
				sel1.selectByIndex(1);
				WebElement quantity = pp.getQuantityDd();
				quantity.clear();
				quantity.sendKeys(quantity1);
				pp.getPriceDd();
				WebElement price = pp.getPriceDd();
				price.clear();
				price.sendKeys(price1);
				WebElement v= pp.getVendorIdDd();
				Thread.sleep(2000);
				Select sel2 = new Select(v);
				sel2.selectByIndex(2);
				pp.getCreateProductSubmitBtn().click();
			
				//validation
				WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(toastmsg));
				String msg = toastmsg.getText();
				if(msg.contains(prodname))
				 {
				  System.out.println("Product created");		
				 }
			    else
				 {
				  System.out.println("Product is not created");	
				 }	
				
				hp.getClosemsg().click();
			
				//logout
				WebElement icon = hp.getUserIcon();
				wutil.mouseHover(driver, icon);
				WebElement logout = hp.getLogOutBtn();
				wutil.clickOnWebElement(driver,logout);
				
				//close browser
				driver.quit();				
	}

}
