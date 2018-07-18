package thread;

public class VolatileTest {
    public static boolean flag = true;
    public static boolean flag2 = false;
    
    public static void main(String[] args) throws InterruptedException {
        
        new Thread() {
            
            @Override
            public void run() {
                while (true) {
                    if (flag == !flag) {
                        System.out.println("!=");
                        flag2 = true;
                        break;
                    }
                }
            };
            
        }.start();
        
        Thread.sleep(20);
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                while (true) {
                    System.out.println("1");
                    flag = !flag;
                    System.out.println("2");
                    if (flag2) {
                        break;
                    }
                }
                
            }
        }).start();
    }
}
