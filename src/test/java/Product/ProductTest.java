package Product;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.HomePage;
import ObjectRepositories.ProductPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;

public class ProductTest extends BaseClass {
	
	@Test(groups= "smoke")
	public void toCreateProductWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException, InterruptedException  {	

		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
						
				//get data from Excel
				String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
				String quantity1 = eutil.toReadDataFromExcelFile("Product", 1, 1);
				String price1 = eutil.toReadDataFromExcelFile("Product", 1, 2);
				
				//Selecting Browser driver - Base Class
				
				// Login - BaseClass
				
				
				//create product
				HomePage hp = new HomePage(driver);
				hp.getProduct().click();
				ProductPage pp= new ProductPage(driver);
				pp.getAddProductBtn().click();
				pp.getProductNameTF().sendKeys(prodname+jutil.togetRandomNumber());
				pp.getProductCategoryDd();
				WebElement pc= pp.getProductCategoryDd(); //Dropdown
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
				sel2.selectByIndex(1);
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
				
				
			//close browser
							
	}
		

}
