package thread.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Rocky Chi
 * simulate a dead lock case
 *
 */
public class DeadLockTest {
    public static boolean flag = true;
    
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    m1();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                m2();
            }
        }).start();
    }
    
    public synchronized static void m1() throws InterruptedException {
        System.out.println("enter m1");
        while (DeadLockTest.flag) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("waiting");
        }
        System.out.println("leave m1");
    }

    public synchronized static void m2() {
        System.out.println("enter m2");
        DeadLockTest.flag = false;
        System.out.println("leave m2");
    }
}
