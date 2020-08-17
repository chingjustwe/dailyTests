package cn.com.nightfield.thread.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author Rocky Chi
 * CountDownLatch usage
 *
 */
public class CountDownTest3 {
    public static void main(String [] args){
        // main list
        List<String> data = new CopyOnWriteArrayList<>();
        // CDLatch with 100 state
        CountDownLatch countDownLatch = new CountDownLatch(100);
        // start 100 threads
        for(int i=0;i<100;i++){
            SampleTask task = new SampleTask(data,countDownLatch);
            Thread thread = new Thread(task);
            thread.start();
        }
        try{
            // main cn.com.nightfield.thread await until all threads are done
            countDownLatch.await();
        }catch (InterruptedException e){  
            e.printStackTrace();  
        } 
        // show main list size
        System.out.println(data.size());
    }
  }
  class SampleTask implements Runnable {
      CountDownLatch countDownLatch;
      List<String> data;
      public SampleTask(List<String> data,CountDownLatch countDownLatch){
          this.data = data;
          this.countDownLatch = countDownLatch;
      }
      @Override
      public void run() {
          // add 100 elements to main list
          for(int i = 0; i < 100; i++)  
          {  
              data.add("1");
          }  
          // add elements done, count down
          countDownLatch.countDown();
      }
  }

