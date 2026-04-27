package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class First {
	
	@Test(priority=-1)
	public void a10()
	{
		Reporter.log("a10",true);
	}
	@Test(priority=1)
	public void a9()
	{
		Reporter.log("a9",true);
	}
	@Test(priority=-2)
	public void a20()
	{
		Reporter.log("a20",true);
	}
	@Test(priority=2)
	public void a11()
	{
		Reporter.log("a11",true);
	}

	public class Second{
		@Test(priority=-3)
		public void amazon()
		{
			Reporter.log("amazon",true);
		}
		@Test(priority=3)
		public void baskinrobin()
		{
			Reporter.log("baskinrobin",true);
		}
		@Test(priority=-4)
		public void bigbsket()
		{
			Reporter.log("bigbsket",true);
		}
		@Test(priority=4)
		public void cricbuz()
		{
			Reporter.log("cricbuz",true);
		}
		
	public class Third{
		@Test
		public void Apple()
		{
			Reporter.log("Apple",true);
		}
		@Test
		public void banana()
		{
			Reporter.log("banana",true);
		}
		@Test
		public void Mango()
		{
			Reporter.log("Mango",true);
		}
		@Test
		public void cucumber()
		{
			Reporter.log("cucumber",true);
		}
		
	}
	}
}
