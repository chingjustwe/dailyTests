package cn.com.nightfield.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibInterceptor implements MethodInterceptor {
    
    public Object getProxy(TargetInterface target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before method " + method.getName());
        Object ret = proxy.invokeSuper(obj, args);
        System.out.println("after method " + method.getName());
        return ret;
    }

}
