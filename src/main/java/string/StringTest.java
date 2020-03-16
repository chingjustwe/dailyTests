package string;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class StringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		long temp;
		File directory = new File("F:\\");
        File[] files = directory.listFiles();
        if (files.length == 0) temp = directory.lastModified();
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return new Long(o2.lastModified()).compareTo(o1.lastModified()); //latest 1st
            }});
        temp = files[0].lastModified();
        System.out.println(temp);
        
        System.out.println(null + " ");

		String[] s = new String[2];
		s[0] = "22";
		s[1] = "11";
		System.out.println(s.toString());
		
		s[1] = "";
		String str = s[0] + "," + s[1];
		System.out.println(str.split(",").length);
		
		String managerNameAll = "Silver Wu (silverw)";
		String managerLoginName = managerNameAll.split("\\)")[0].split("\\(")[1].trim();
		System.out.println(managerLoginName);
		String s1;
		if ((s1 = test()) != null){
			System.out.println(s1);
		}
		List<String> li = new ArrayList<String>();
		li.add("123123");
		li.add("drt4q2");
		li.add("5tgdf");
		li.add("3rew");
		li.add("");
		String st = convertListToString(li, " ");
		System.out.println(st);
		
		String splitString = "*er*erww";
		String dd = splitString.replaceAll("[*]", "23");
		System.out.println(dd);
		
		String searchValue = "**erqw*reg*dad****feiuh*";
		String regEx = searchValue.replaceAll("[*]+", "*").replaceAll("[*]", "+?.*");
		regEx = regEx.startsWith("+?.*") ? regEx.substring(2) : regEx;
		System.out.println(regEx);
		
		String chineseString = "中文字符";
		StringBuffer chineSb = new StringBuffer("中文字符");
		String eng = "abcde";
		System.out.println("length: " + chineseString.length());
		System.out.println("byte1[]: " + chineseString.getBytes("GB2312").length);
        System.out.println("byte2[]: " + chineseString.getBytes("UTF-8").length);
        System.out.println("length2: " + chineSb.length());
        System.out.println("byte3[]: " + chineSb.toString().getBytes("GB2312").length);
        System.out.println("byte4[]: " + chineSb.toString().getBytes("UTF-8").length);
        System.out.println("length3: " + eng.length());
        System.out.println("byte5[]: " + eng.getBytes("GB2312").length);
        System.out.println("byte6[]: " + eng.getBytes("UTF-8").length);
        
        Integer encode = 1;
        switch (encode) {
        case 1:
            System.out.println("OK");
            break;

        default:
            break;
        }
        
        System.out.println("split lenght: "+ "".split(",").length);
        
        System.out.println("**********************************");
        String ss1 = "123";
        String ss2 = new String("123");
        String ss3 = new String(ss1);
        String ss4 = new String("123".intern());
        String ss5 = new String("123").intern();
        String ss6 = "123";
        System.out.println(ss5 == ss1);
        System.out.println(ss5 == ss2);
        System.out.println(ss5 == ss3);
        System.out.println(ss5 == ss4);
        System.out.println(ss5 == ss6);
        
        System.out.println(java.lang.System.getProperty("https.protocols"));
	
	}
	
	private static String test(){
		return "1";
	}
	
	public static String convertListToString (List<?> list, String separator) {
    	
    	if (list.isEmpty()) {
    		return "";
    	}
    	
    	StringBuffer sb = new StringBuffer("");
    	for (Object obj : list){
    		sb.append(String.valueOf(obj));
    		sb.append(separator);
    	}
    	int i = separator.length();
    	
    	return sb.substring(0, sb.length() - separator.length()).trim();
    }
}
