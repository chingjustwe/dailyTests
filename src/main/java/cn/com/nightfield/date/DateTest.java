package cn.com.nightfield.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws ParseException {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String targetDate = dateFormat.format(cal.getTime());
        
        //System.out.print(targetDate);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println(now);
        
        //sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        String nowStr = sdf.format(now);
        System.out.println(nowStr);
        
        Date now1 = sdf.parse(nowStr);
        System.out.println(now1);
        
        Date date = new Date(1517299425906l);
        System.out.println(date);
        System.out.println("=================");

        Date date2 = sdf.parse("2018-01-30 04:13:00");
        System.out.println(date2);
        Date date3 = sdf.parse("2018-01-30 06:12:59");
        System.out.println(date3);
        Date date1 = new Date(1517285579998l);
        System.out.println(date1);
        
        
        
	}
}
