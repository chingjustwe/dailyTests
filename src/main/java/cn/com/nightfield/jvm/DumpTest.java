package cn.com.nightfield.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Rocky Chi
 * heap dump on out of memory.
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\dump\dump2.hprof -XX:+PrintGCDetails
 * -verbose:gc -Xms500M -Xmx500M -XX:NewSize=10m -XX:MaxNewSize=10m -XX:+UseConcMarkSweepGC
 */
public class DumpTest {

    private static List<byte[]> global = new ArrayList<>();
    
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            //byte[] b = new byte[100 * 100];
            byte[] b = new byte[1024 * 1024];
            global.add(b);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
