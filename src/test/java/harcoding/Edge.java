package harcoding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Edge {

	public static void main(String[] args) {
		Date d=new Date();
		System.out.println(d);
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(d);
		System.out.println(currentDate);
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, 15);
		String expectedDate = sim.format(cal.getTime());
		System.out.println(expectedDate);
		
		
		
		

	}

}
