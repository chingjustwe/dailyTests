package cn.com.nightfield.math;

import java.util.Scanner;

public class Pattern {

	/**
	 * \<-p->|<-p->/
	 *  \    |    /
	 *   \   |   /
	 *    \  |  /
	 *     \ | /
	 * <-m->\|/
	 *       V
	 * m is marginSpace, n is paddingSpace
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		while (true) {//infinite loop to enable continuous input
			System.out.print("Input N: ");
			Scanner in = new Scanner(System.in);
			int N = in.nextInt();
			
			if (N <= 0) {//if N is non-positive, do nothing
				continue;
			}
			
			for (int i = 1; i < N; i++) {//loop starts from N = 2, because if N = 1, it only prints a "V"
				String outputString = "";
				String marginSpace = new String(new char[i - 1]).replace('\0', ' ');//generate a String with 'i-1' space
				String paddingSpace = new String(new char[N - i - 1]).replace('\0', ' ');//generate a String with 'N-i-1' space
				outputString += marginSpace;
				outputString += "\\";
				outputString += paddingSpace;
				outputString += "|";
				outputString += paddingSpace;
				outputString += "/";
				System.out.println(outputString);
			}
			
			String lastMargin = "";
			if (N >= 2) {
				lastMargin = new String(new char[N - 1]).replace('\0', ' ');//generate margin space before "V"
			}
			System.out.println(lastMargin + "V");//print "V" anyway
		}
	}
}