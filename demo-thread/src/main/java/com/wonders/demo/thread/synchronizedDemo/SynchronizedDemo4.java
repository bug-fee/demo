package com.wonders.demo.thread.synchronizedDemo;

/**
 * 锁重入现象
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 锁可重入现象：同一个线程，多次调用同步代码，锁定同一个锁对象，可重入。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo4 {
    synchronized void m1() {
        System.out.println("m1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2() {
        System.out.println("m2 start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    /**
     * 同一个线程多次调用同步代码，并且锁定同一个对象，不会出现线程锁死的情况。
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
        final SynchronizedDemo4 synchronizedDemo4 = new SynchronizedDemo4();
        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo4.m1();
            }
        }).start();
    }
}
