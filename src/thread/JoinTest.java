package thread;

import java.util.concurrent.TimeUnit;

public class JoinTest {
    
    public static void main(String[] args) {
        System.out.println("main thread start");
        Thread t1 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println("t1 start");
                try {
                    TimeUnit.MILLISECONDS.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 ends");
            }
        });
        t1.setName("t1");
        t1.start();
        System.out.println("t1 join");
        try {
            t1.join(2000);//if specify the timeout, then main thread will wait 2000 at most
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("main thread end");
        
    }
}
