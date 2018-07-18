package aop;

import net.sf.cglib.core.DebuggingClassWriter;

public class CGLibMain {
    
    public static void main(String[] args) {
        //generate and write class file to file system: C:\\workspace\\ZTest\\cglib
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\workspace\\ZTest\\cglib");
        
        TargetInterface target = new TargetInterfaceImpl();
        TargetInterfaceImpl proxy = (TargetInterfaceImpl) new CGLibInterceptor().getProxy(target);
        
        /*
         * before method equals
         * after method equals
         * false
         */
        System.out.println(proxy.equals(new Object()));
        /*
         * before method hashCode
         * after method hashCode
         * 1327763628
         */
        System.out.println(proxy.hashCode());
        /*
         * before method toString
         * after method toString
         * aopTest.TargetInterfaceImpl$$EnhancerByCGLIB$$44dc9fb8@4f2410ac
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
         * class aopTest.TargetInterfaceImpl$$EnhancerByCGLIB$$44dc9fb8
         */
        System.out.println(proxy.getClass());
        /*
         * java.lang.IllegalMonitorStateException
         */
        //proxy.notify();
    }

}
