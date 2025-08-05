package testNGPractice;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class InvCount {
	@Test(invocationCount =3)
	public void login()
	WebDriver driver = new ChromeDriver();
	driver.quit();

}
