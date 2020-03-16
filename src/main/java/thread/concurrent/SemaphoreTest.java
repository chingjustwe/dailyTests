package thread.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Rocky Chi
 * Semaphore usage
 *
 */
public class SemaphoreTest {
    private static int n = 12;
    
    public static void main(String[] args) throws InterruptedException {
        Semaphore s = new Semaphore(4, true);
        
        System.out.println("main thread start");
        for (int i = 0; i < n; ++i) {
            new Thread(new SemaphoreWorker(s, i)).start();
        }
        
        System.out.println("main thread end");
    }

}


class SemaphoreWorker implements Runnable {
    private final Semaphore s;
    private int i;
    
    SemaphoreWorker(Semaphore s, int i) {
        this.s = s;
        this.i = i;
    }
    
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            s.acquire();
            //while (!s.tryAcquire()) {};
            System.out.println(name + " acquires permit");
            doWork();
            System.out.println(name + " release permit, permits: " + s.availablePermits() + ", drain permits: " + s.drainPermits() + ", queue length: " + s.getQueueLength());
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(i * 100);
    }
}