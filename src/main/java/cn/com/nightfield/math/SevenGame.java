package cn.com.nightfield.math;

public class SevenGame {

	public static void main(String[] args) {
		int maxNumber = 10000;
		String outputString = "";
		
		for (int i = 1; i < maxNumber; i++) {
			if (i % 7 == 0 || String.valueOf(i).contains("7")) {
				continue;
			}
			outputString += i + ",";
		}
		
		System.out.println(outputString.substring(0, outputString.length() - 1));
	}
	
}
