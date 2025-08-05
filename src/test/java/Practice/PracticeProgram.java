package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class PracticeProgram {
	
	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8098");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys("ASCN188");
		driver.findElement(By.xpath("//input[@name='campaignStatus']")).sendKeys("ASCS1");
		driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys("50");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
}
