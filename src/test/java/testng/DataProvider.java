package testng;

import org.testng.annotations.Test;

public class DataProvider {

	
	@Test(dataProvider="details")
	
	public void login(String UN, String pwd)
	{
		System.out.println(UN+"------"+pwd);
	}
	
	
	
	
	
	@org.testng.annotations.DataProvider
	public Object[][] details()
	{
		Object[][] obj=new Object[3][2];
		
		obj[0][0]="dhoni";
		obj[0][1]="dhoni7";
		obj[1][0]="virat";
		obj[1][1]="virat18";
		obj[2][0]="rohit";
		obj[2][1]="rohit48";
		
		return obj;
	}
}
