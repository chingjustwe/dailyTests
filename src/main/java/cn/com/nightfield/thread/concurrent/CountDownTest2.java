package cn.com.nightfield.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Rocky Chi
 * CountDownLatch usage
 *
 */
public class CountDownTest2 {
    private static int n = 10;
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);
        //ExecutorService e = Executors.newFixedThreadPool(n);
        
        System.out.println("before countdown");
        for (int i = 0; i < n; ++i) {
            //e.execute(new WorkerRunnable(doneSignal, i));
            new Thread(new Worker(doneSignal, startSignal, i)).start();
        }
        
        System.out.println("start signal count down");
        startSignal.countDown();
        System.out.println("done signal is awaiting.");
        doneSignal.await();           // wait for all to finish
        System.out.println("await finished");
    }

}


class Worker implements Runnable {
    private final CountDownLatch doneSignal;
    private final CountDownLatch startSignal;
    private final int i;
    
    Worker(CountDownLatch doneSignal, CountDownLatch startSignal, int i) {
       this.doneSignal = doneSignal;
       this.startSignal = startSignal;
       this.i = i;
    }
    
    public void run() {
       try {
           System.out.println("worker " + i + " is awaiting.");
           startSignal.await();
           doWork();
           System.out.println(i + " is done, " + doneSignal.getCount() + " works to be done.");
           doneSignal.countDown();
       } catch (InterruptedException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }

    void doWork() throws InterruptedException {
        System.out.println(i + " is doing work");
        TimeUnit.MILLISECONDS.sleep(i * 100);
    }
}