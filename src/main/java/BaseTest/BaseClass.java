package BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ObjectRepositories.HomePage;
import ObjectRepositories.LoginPage;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver=null; //listener
	public PropertiesFileUtility putil = new PropertiesFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
		
		@BeforeSuite(groups = {"smoke", "regression"})
		public void beforeSuite() {
		System.out.println("DB connectivity open");
		}
		
		@BeforeClass(groups= {"smoke", "regression"})
		public void beforeClass() throws IOException {
		String BROWSER = putil.togetDataFromProperties("Browser");	
		if (BROWSER.equals("Edge")) {
		driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
		driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
		driver = new FirefoxDriver();
		}
		sdriver=driver; //listener
		System.out.println("Launching browser");
		}
		
		@BeforeMethod(groups= {"smoke", "regression"})
		public void beforeMethod() throws IOException {
		String URL = putil.togetDataFromProperties("Url");
		String USERNAME = putil.togetDataFromProperties("Username");
		String PASSWORD = putil.togetDataFromProperties("Password");
		driver.manage().window().maximize();
		wutil.waitForPagetoLoad(driver); 
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		System.out.println("Login");
		}
		
		@AfterMethod(groups= {"smoke","regression"})
		public void afterMethod() {
		HomePage hp = new HomePage(driver);
		WebElement icon = hp.getUserIcon();
		wutil.mouseHover(driver, icon);
		WebElement logout = hp.getLogOutBtn();
		wutil.clickOnWebElement(driver, logout);
		System.out.println("logout done");
		}
		
		@AfterClass(groups= {"smoke","regression"})
		public void afterClass() {
		driver.quit();
		System.out.println("Closing browser");
		}

		
		@AfterSuite(groups= {"smoke","regressiion"})
		public void afterSuite() {
		System.out.println("DB close");
		}
	
}
	

