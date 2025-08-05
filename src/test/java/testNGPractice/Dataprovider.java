package testNGPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Need to know logic?

public class Dataprovider {
	
	@Test(dataProvider = "loginDetails")
	
	public void login(String firstname, String lastname)
	{
		System.out.println(firstname+" "+ lastname);
		
	}
	
	
	@DataProvider
	public Object[][] loginDetails()
	{
		Object[][] objarr = new Object[3][2];
		objarr[0][0]="sam";
		objarr[0][1]="ruth";
		objarr[1][0]="jhon";
		objarr[1][1]="cena";
		objarr[2][0]="peter";
		objarr[2][1]="parker";
		return objarr;
	
	}

}
