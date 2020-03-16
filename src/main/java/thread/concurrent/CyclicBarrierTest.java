package thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Rocky Chi
 * CyclicBarrier usage
 *
 */
public class CyclicBarrierTest {
    private static int n = 12;
    
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(4, new Runnable() {
            
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", all 4 threads are done.");
            }
        });
        
        System.out.println("main thread start");
        for (int i = 0; i < n; ++i) {
            new Thread(new CyclicWorker(cb, i)).start();
        }
        
        System.out.println("main thread end");
    }

}


class CyclicWorker implements Runnable {
    private final CyclicBarrier cb;
    private int i;
    
    CyclicWorker(CyclicBarrier cb, int i) {
        this.cb = cb;
        this.i = i;
    }
    
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " is doing work");
            doWork();
            System.out.println(name + " is done, number waiting: " + cb.getNumberWaiting() + ", parties: " + cb.getParties());
            cb.await();
            System.out.println(name + " go on");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(i * 100);
    }
}