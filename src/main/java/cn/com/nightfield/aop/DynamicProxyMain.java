package cn.com.nightfield.aop;

import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

public class DynamicProxyMain {
    
    public static void main(String[] args) throws InterruptedException {
        TargetInterface target = new TargetInterfaceImpl();
        DynamicProxyHandler handler = new DynamicProxyHandler(target);
        TargetInterface proxy = (TargetInterface) handler.getProxy();
        /*
         * before method equals
         * after method equals
         * false
         */
        System.out.println(proxy.equals(new Object()));
        /*
         * before method hashCode
         * after method hashCode
         * 149147429
         */
        System.out.println(proxy.hashCode());
        /*
         * before method toString
         * after method toString
         * aopTest.TargetInterfaceImpl@8e3cf25
         */
        System.out.println(proxy.toString());
        /*
         * before method rockyTest1
         * rocky test 1
         * after method rockyTest1
         * rocky result
         */
        System.out.println(proxy.rockyTest1());
        /*
         * before method rockyTest2
         * rocky test 2
         * after method rockyTest2
         */
        proxy.rockyTest2();
        /*
         * class com.sun.proxy.$Proxy0
         */
        System.out.println(proxy.getClass());
        /*
         * java.lang.IllegalMonitorStateException
         */
        //proxy.notify();
        
        //generate and write class file to file system: workspace/ZTest/com.sun.proxy.$Proxy0 (requires sunJDK instead of openJDK)
        byte[] classFile = ProxyGenerator.generateProxyClass("com.sun.proxy.$Proxy0", target.getClass().getInterfaces());
        FileOutputStream out;
        try {
            out = new FileOutputStream("com.sun.proxy.$Proxy0.class");
            out.write(classFile);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
