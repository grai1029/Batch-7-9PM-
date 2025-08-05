package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Priority {
	
		@Test(priority =2)
		public void add()
		{
			Reporter.log("add",true);
			
		}
		
		@Test(priority =  -1)
		public void sub()
		{
			Reporter.log("sub",true);
			
		}
		
		
		@Test(priority =  1)
		public void div()
		{
			Reporter.log("div",true);
			
		}
		
		@Test
		public void a16()
		{
			Reporter.log("a16 executed",true);
			
		}
		
		

}
