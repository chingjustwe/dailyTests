package cn.com.nightfield.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Rocky Chi
 * CountDownLatch usage
 *
 */
public class CountDownTest {
    private static int n = 10;
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(n);
        ExecutorService e = Executors.newFixedThreadPool(n);
        
        System.out.println("before countdown");
        for (int i = 0; i < n; ++i) {
            e.execute(new WorkerRunnable(doneSignal, i));
            //new Thread(new WorkerRunnable(doneSignal, i)).start();
        }
        
        System.out.println("signal await");
        doneSignal.await();           // wait for all to finish
        System.out.println("await finished");
    }

}


class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;
    
    WorkerRunnable(CountDownLatch doneSignal, int i) {
       this.doneSignal = doneSignal;
       this.i = i;
    }
    
    public void run() {
       try {
           doWork(i);
           System.out.println(i + " is done, " + doneSignal.getCount() + " works to be done.");
           doneSignal.countDown();
       } catch (InterruptedException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }

    void doWork(int i) throws InterruptedException {
        System.out.println(i + " is doing work");
        TimeUnit.MILLISECONDS.sleep(i * 100);
    }
}