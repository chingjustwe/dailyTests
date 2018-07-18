package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//thread unsafe
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        String targetDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        
        System.out.println(targetDate);
        
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar startCalendar = Calendar.getInstance();
        try {
            String ss = "2017-08-10 11:00:00";
            startCalendar.setTime(format.parse(ss));
        } catch (ParseException e) {
            startCalendar.setTime(new Date());
        }
	}
}
