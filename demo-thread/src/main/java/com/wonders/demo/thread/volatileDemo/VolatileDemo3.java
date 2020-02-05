package com.wonders.demo.thread.volatileDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * atomicXxx类型<br/>
 * 原子操作类型,其中的每一个方法都是原子操作，可以保证线程安全。<br/>
 * <br/>
 * 原子性与可见性的区别：<br/>
 * 1.原子性保证多个线程操作同一个资源时，该资源不会出现超标或丢失。<br/>
 * 2.可见性保证多个线程访问同一个资源时，始终能获取到该资源的最终值。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class VolatileDemo3 {
    //Atomic类型可保证原子操作，相对于synchronized效率更高一些
    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {
            //相当于 “前++”，先++再取得值
            count.incrementAndGet();
            //相当于 “后++” ，先获取值再++
//            count.getAndIncrement();
        }
    }

    /**
     * 由于Atomic类型保证原子性，执行结果为10万，可保证线程安全。
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        final VolatileDemo3 volatileDemo3 = new VolatileDemo3();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new Runnable() {
                public void run() {
                    volatileDemo3.m();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(volatileDemo3.count);


    }
}
