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
        Class clz = customizedClassLoader.loadClass("jvm.classloader.Plugin");
        System.out.println(clz.getClassLoader());
        Object instance = clz.newInstance();
        System.out.println("instanceof Plugin: " + (instance instanceof Plugin));
        Plugin plugin = (Plugin) clz.newInstance();
    }
}
