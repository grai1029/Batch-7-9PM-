package testNGPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {
	
	@Test
	public void demo()
	
	{
		String expTitle = "Facebook - log in or sign up";
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		String actTitle = driver.getTitle();
		Assert.assertEquals(expTitle, actTitle);
		System.out.println("abc");
		System.out.println("zxc");
		
	}

}
