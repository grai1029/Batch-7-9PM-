package testNGPractice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;


public class ScreenshotFacebook {
	
	@Test
	public void toTakeScreenshot() throws IOException {

		 WebDriver driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	     driver.get("https://www.facebook.com/");
	     Date d = new Date();
	     String newDate = d.toString().replace(" ", "_").replace(":", "_");
	     TakesScreenshot ts = (TakesScreenshot) driver;
	     File temp = ts.getScreenshotAs(OutputType.FILE);
	     File per = new File("./Screenshot/takeSS_"+newDate+".png");
	     FileHandler.copy(temp, per);
	   
	}

}
