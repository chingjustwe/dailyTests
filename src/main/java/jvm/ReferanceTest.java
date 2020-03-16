package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Rocky Chi
 * test of 4 kinds of references been GC.
 */
public class ReferanceTest {
    static List<String[][]> testList = new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        
        WeakReference<InnerReference> reference1 = new WeakReference<InnerReference>(new InnerReference());  
        WeakReference<String> reference2 = new WeakReference<String>(new String("123"));  
        WeakReference<String> reference3 = new WeakReference<String>("123");  
        WeakReference<String> reference4 = new WeakReference<String>(new String("123").intern());  
        System.out.println("weak object reference before GC: " + reference1.get()); //8388608
        System.out.println("weak new String reference before GC: " + reference2.get()); //123
        System.out.println("weak literal String reference before GC: " + reference3.get()); //123
        System.out.println("weak intern String reference before GC: " + reference4.get()); //123
        System.gc();  
        System.out.println("weak object reference after GC: " + reference1.get());  //null
        System.out.println("weak new String reference after GC: " + reference2.get()); //null
        System.out.println("weak literal String reference after GC: " + reference3.get()); //123
        System.out.println("weak intern String reference after GC: " + reference4.get()); //123
        System.out.println();  
        
        InnerReference ss = new InnerReference();
        //String ss = new String(new byte[2048*2048]);
        ReferenceQueue<Object> rq = new ReferenceQueue<>();
        Reference<InnerReference> sr = new SoftReference<InnerReference>(new InnerReference(), rq);
        Reference<InnerReference> wr = new WeakReference<InnerReference>(new InnerReference(), rq);
        Reference<InnerReference> pr = new PhantomReference<InnerReference>(new InnerReference(), rq);
        ss = null;
        while (sr.get() != null || wr.get() != null || pr.get() != null) {
            System.out.println("soft: " + (sr.get() == null ? "null" : sr.get().toString()));
            System.out.println("weak: " + (wr.get() == null ? "null" : wr.get().toString()));
            
            System.out.println("free: " + Runtime.getRuntime().freeMemory()/1000/1000);
            System.out.println("");
            String[][] o = new String[1024][1024];
            testList.add(o);
            
            System.gc();
            Thread.sleep(1000);
        }
        System.out.println("end");
    }
    
}

class InnerReference {
    private String ss = new String(new byte[2048*2048]); 
    private byte[] bb = new byte[2048*2048];
    
    @Override
    public String toString() {
        return ss.length() + bb.length + "";
    }
}