package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddProductWithMandatoryFields {
	public static void main(String[] args) throws IOException {
		//get Data from Properties File
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");	
		
		//get Data from Excel File
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScriptdata.xlsx");
	    Workbook wb = WorkbookFactory.create(fis1);
	    Sheet sh = wb.getSheet("Product");
		Row r = sh.getRow(1);
		String prodname = r.getCell(0).getStringCellValue();
		String quantity1 = r.getCell(1).getStringCellValue();
		String price1 = sh.getRow(1).getCell(2).getStringCellValue();
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		Random ran = new Random();
		int randcount= ran.nextInt(1000);
		
		//create product
		driver.findElement(By.partialLinkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(prodname + randcount);
		WebElement pc= driver.findElement(By.name("productCategory")); //dropdown
		Select sel1 = new Select(pc);
		sel1.selectByIndex(1);
		
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(quantity1);
		
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(price1);	
		
		WebElement v= driver.findElement(By.name("vendorId"));
		Select sel2 = new Select(v);
		sel2.selectByIndex(1);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
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
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();	
		
		//logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).perform();	
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		driver.quit();

	}

}
