package cn.com.nightfield.jvm.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: zhochi
 * @create: 2020/8/14
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        URL url = new File("/Users/zhochi/Self/self-repo/dailyTests/target/classes").toURI().toURL();
        ClassLoader customizedClassLoader = new CustomizedClassLoader(new URL[]{url});
        Class clz1 = customizedClassLoader.loadClass("cn.com.nightfield.jvm.classloader.Plugin");
        ClassLoader customizedClassLoader2 = new CustomizedClassLoader2(new URL[]{url});
        customizedClassLoader2.loadClass(clz1.getName());
        Class clz2 = customizedClassLoader2.loadClass("cn.com.nightfield.jvm.classloader.Plugin");
        System.out.println(clz1.getClassLoader());
        Object instance = clz1.newInstance();
        System.out.println("instanceof Plugin: " + (instance instanceof Plugin));
        Plugin plugin = (Plugin) clz1.newInstance();
    }
}
