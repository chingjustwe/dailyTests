package math;

public class BitTest {
	public static void main(String[] args) {
		int c = 2069;
		
		int n = c - 1;
		System.out.println(n);
		System.out.println(n >>> 1);
		System.out.println();
        n |= n >>> 1;
        System.out.println(n);
		System.out.println(n >>> 1);
		System.out.println();
        n |= n >>> 2;
        System.out.println(n);
		System.out.println(n >>> 1);
		System.out.println();
        n |= n >>> 4;
        System.out.println(n);
		System.out.println(n >>> 1);
		System.out.println();
        n |= n >>> 8;
        System.out.println(n);
		System.out.println(n >>> 1);
		System.out.println();
        n |= n >>> 16;
        System.out.println(n + 1);
	}
}
