package generalUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public char RandomAlphabet()
	{
		Random r=new Random();
		char randalpha=(char) ('A' + r.nextInt(26));
		return randalpha;
	}
	public String RandomPhoneNumber()
	{
		Random r=new Random();
		String randomPhoneNum = "9" + (100000000 + r.nextInt(900000000));
		return randomPhoneNum;
	}
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MMyyyy");
		String currentDate = sim.format(date);
		return currentDate;
		}
	public String ExpectedDate()
	{
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(d);
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, 15);
		String expectedDate = sim.format(cal.getTime());
		return expectedDate;
	}
	public int RandomNumber()
	{
		Random r=new Random();
		int randomnum = r.nextInt(1000);
		return randomnum;

	}

}
