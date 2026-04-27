package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOn {

	@Test
	
	public void Create()
	{
		Reporter.log("created",true);
	}
	@Test(dependsOnMethods="Create")
	 public void Edit()
	 {
		Reporter.log("Edited",true);
	 }
	@Test(dependsOnMethods={"Create","Edit"})
	public void Delete()
	{
		Reporter.log("deleted",true);
	}
	
}
