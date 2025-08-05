package testNGPractice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ConfigAnnotation {
  @Test
  public void demo(){
	  
	  System.out.println("TC executed");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("login");  
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("logout");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("launch browser");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("close browser");
  }

  @BeforeTest
  public void beforeTest() {
	  
	  System.out.println("preconditions");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("postconditions");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("DB connectivity open");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("DB close");
  }

}
