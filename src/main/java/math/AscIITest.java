package math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AscIITest {
	public static void main(String[] args) {
		char c = '';
		int i = c;
		System.out.println(i);
		
		String pattern = "[\\x20-\\xff]+";
		Pattern pt = Pattern.compile(pattern);
		
		Matcher m = pt.matcher("");
		if (m.find()) {
			System.out.println("find");
		}
		else {
			System.out.println("no");
		}
	}
}
