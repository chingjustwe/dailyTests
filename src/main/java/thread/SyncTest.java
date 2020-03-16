package thread;

import java.util.concurrent.locks.ReentrantLock;

public class SyncTest {
    
    public static void main(String[] args) {
        final Sync sync = new Sync();
        final SyncLock lock = new SyncLock();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    sync.m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    sync.m2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    sync.m3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    lock.m1();
                    lock.m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    lock.m2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /*new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    lock.m3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
    
}

class Sync {
    public synchronized void m1 () throws InterruptedException {
        System.out.println("enter m1");
        Thread.sleep(2000);
        System.out.println("leave m1");
    }

    public synchronized void m2 () throws InterruptedException {
        System.out.println("enter m2");
        Thread.sleep(2000);
        System.out.println("leave m2");
    }
    
    public void m3 () throws InterruptedException {
        System.out.println("enter m3");
        Thread.sleep(1000);
        System.out.println("leave m3");
    }
}

class SyncLock {
    ReentrantLock lock = new ReentrantLock();
    public void m1 () throws InterruptedException {
        lock.lock();
        try {
            System.out.println("enter ml1");
            Thread.sleep(5000);
            System.out.println("leave ml1");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }
    
    public void m2 () throws InterruptedException {
        lock.lock();
        try {
            System.out.println("enter ml2");
            Thread.sleep(5000);
            System.out.println("leave ml2");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }
    
    public void m3 () throws InterruptedException {
        System.out.println("enter ml3");
        Thread.sleep(2000);
        System.out.println("leave ml3");
    }
}

