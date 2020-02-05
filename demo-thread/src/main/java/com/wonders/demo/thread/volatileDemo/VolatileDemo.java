package com.wonders.demo.thread.volatileDemo;

/**
 * volatile 关键字
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * volatile 的可见性：<br/>
 * 1.临界资源增加volatile时，表示通知OS操作系统底层，在CPU计算过程中，每次都要检查内存中数据的有效性，保证最新的内存数据被使用。<br/>
 * 2.如果不使用volatile，则CPU在计算过程中只从高速缓存区获取临界资源，<br/>
 * 当临界资源在其它线程中被修改时，仅仅是修改了内存中的值。此时高速缓存中的资源与内存中的资源不同步。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class VolatileDemo {
    /**
     * volatile
     */
    volatile boolean b = true;

    public void m() {
        System.out.println(" m start ");
        while (b) {
            //如果不加volatile，CPU每次从高速缓存获取值。但是如果后面引用到了这个变量，高速缓存将会刷新。
            System.out.println(b);
        }
        System.out.println(" m end");
    }

    public static void main(String[] args) {
        final VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(new Runnable() {
            public void run() {
                volatileDemo.m();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        volatileDemo.b = false;
    }
}
