package cn.com.nightfield.other;

import java.util.Collection;
import java.util.concurrent.Callable;

public interface Interface1<E> extends Callable<Object>, Collection<E> {
    public void method1();
    public void method2();
}
