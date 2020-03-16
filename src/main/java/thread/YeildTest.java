package thread;

public class YeildTest {
	
	public static void main(String[] args) {
		System.out.println("1");
		while (true) {
			System.out.println("2");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Y();
			Thread.yield();
			System.out.println("4");
		}
	}

	public static void Y() {
		System.out.println("3");
	}
}
