package cn.com.nightfield.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * @author Rocky Chi
 * when execute 'jmap -histo pid', there are a lot of int[] class. and execute
 * 'jmap -histo:live pid', no int[] class.
 * 
 * "jmap" command will scan all young generation objects, and it will fill all
 * TLAB with int array to avoid in-consist head memory. so more threads, more
 * int classes.
 */
public class LotOfIntArray {
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(500);
        LotOfIntArray.TestClass[] classes = new LotOfIntArray.TestClass[500];
        
        for (int i = 0; i < 500; i++) {
            classes[i] = new LotOfIntArray.TestClass(latch);
            classes[i].start();
        }
        TimeUnit.SECONDS.sleep(200);
        for (int i = 0; i < 500; i++) {
            classes[i].setStop();
        }
    }
    
    public static class TestClass extends Thread {
        CountDownLatch latch;
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        boolean stop = false;
        
        public TestClass(CountDownLatch latch) {
            this.latch = latch;
        }
        
        public void setStop() {
            this.stop = true;
        }
        
        @Override
        public void run() {
            while (!this.stop) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] buf = new byte[0];//will cause a lot of int[] classes, to fill in TLAB. if delete this line, int array will disappear.
            }
        }
    }
}
