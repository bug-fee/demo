package com.wonders.demo.thread.synchronizedDemo;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 锁对象被修改问题
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo7 {
    Object o = new Object();

    public void m() {
        System.out.println(Thread.currentThread().getName() + " start ");
        synchronized (o) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    /**
     * 被锁的是“对象”不是引用，第一个被锁的对象存在于在堆中，第二个被重新引用的对象同样存在于堆中，二者不相同。
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
        final SynchronizedDemo7 synchronizedDemo7 = new SynchronizedDemo7();

        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo7.m();
            }
        }, "t1").start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronizedDemo7.m();
            }
        }, "t2");

        /*
         * 当锁对象被修改时，就起不到同步锁的作用了，可以在临界资源上加final，禁止锁对象被修改，
         * 锁的对象换了，但是线程有自己开辟出来的独立栈帧区域，锁对象的引用保存在栈帧中，而栈帧中的引用没有被更换，所以原来被锁的对象依然处于锁定状态，
         * 不妨碍第二个线程锁定新引用的对象。
         */
        synchronizedDemo7.o = new Object();
        thread2.start();
    }

}
