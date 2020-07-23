package com.github.cooker;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * grant
 * 23/7/2020 3:31 下午
 * 描述：
 */
public class ReferenceTest {

    /**
     * 虚引用，主要堆外内存回收
     * @throws InterruptedException
     */
    @Test
    public void phantom() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        PhantomReference<Object> pr = new PhantomReference<Object>(obj, queue);
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
        obj = null;
        System.gc();
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
    }


    /**
     * 如果一个对象具有软引用，内存空间足够，垃圾回收器就不会回收它；
     */
    @Test
    public void soft(){
        Object obj = new Object();
        SoftReference<Object> pr = new SoftReference<Object>(obj);
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
        obj = null;
        System.gc();
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
    }

    /**
     * 弱引用，gc后进行释放
     */
    @Test
    public void weak(){
        Object obj = new Object();
        WeakReference<Object> pr = new WeakReference<Object>(obj);
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
        obj = null;
        System.gc();
        System.out.println(pr.get());
        System.out.println(pr.isEnqueued());
    }
}
