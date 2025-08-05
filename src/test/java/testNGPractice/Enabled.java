package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Enabled {
	@Test
	public void a11()
	{
		Reporter.log("a11 executed",true);
		
	}
	
	@Test(enabled = false) 
	public void a23()
	{
		Reporter.log("a23 executed",true);
		
	}
	
	@Test
	public void b25()
	{
		Reporter.log("b25 executed",true);
		
	}
	
	@Test
	public void b11()
	{
		Reporter.log("b11 executed",true);
		
	}
	
	@Test
	public void a16()
	{
		Reporter.log("a16 executed",true);
		
	}

}
