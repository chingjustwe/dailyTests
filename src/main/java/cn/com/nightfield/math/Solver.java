package cn.com.nightfield.math;

public class Solver {

	public static void main(String[] args) {
		int maxNumber = 100000000;
		
		for (int i = 33388; i < maxNumber; i++) {
			int tempNumber = i;
			int tempRemainder = 0;
			int threeCount = 0;
			int eightCount = 0;
			boolean continueFlag = false;
			
			while (tempNumber > 0) {
				tempRemainder = tempNumber % 10;//get the remainder
				tempNumber = tempNumber / 10;//re-assign value

				if (tempRemainder == 4) {//if current number contains 4, then skip
					continueFlag = true;
					break;
				}
				if (tempRemainder == 3) {
					threeCount++;
				}
				if (tempRemainder == 8) {
					eightCount++;
				}
			}
			
			if (continueFlag) {//current number contains 4, should skip
				continue;
			}
			if (threeCount == 3 && eightCount == 2) {//contains three 3 and 2 eight, then println it
				System.out.println(i);
			}
			
		}
	}
}