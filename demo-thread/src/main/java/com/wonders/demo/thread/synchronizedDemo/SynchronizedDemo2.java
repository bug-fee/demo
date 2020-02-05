package com.wonders.demo.thread.synchronizedDemo;

/**
 * 演示锁对象（无法达到同步的案例）
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * synchronized关键字
 * 同步方法 - 同步方法和非同步方法的调用
 * 同步方法只影响其他线程调用同一个同步方法时的锁问题。不影响其他线程调用非同步方法，或调用其他锁资源的同步方法。
 * <p>
 * 锁对象时建议使用m3锁特定临界资源（多线程可共享的资源），该对象通常是方法传入的。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo2 {
    Object o = new Object();

    /**
     * m1睡眠3秒钟、m2睡眠2秒钟<br/>
     * 预期:m1执行完成之前m2不会执行。<br/>
     * 测试结果为:m1未执行完同时m2执行了，m1与m2未实现同步。
     * <p>
     * 评估：重量级访问操作。任意多个线程、任意多个资源不会被多线程访问时候所影响，效率相对于低下。
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public synchronized void m1() {
        System.out.println("m1 sleep start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 sleep end");
    }

    /**
     * 非同步方法<br/>
     * 测试结果：未加锁的方法不与任何方法实现同步
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void m2() {
        System.out.println("m2 sleep start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 sleep end");
    }

    /**
     * 锁其他对象的测试方法<br/>
     * 测试结果：锁对象不同时无法实现同步
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void m3() {
        synchronized (o) {
            System.out.println("m3 sleep start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m3 sleep end");
        }
    }

    public static class MyTestThread implements Runnable {

        int i;
        SynchronizedDemo2 synchronizedDemo2;

        public MyTestThread(int i, SynchronizedDemo2 t) {
            this.synchronizedDemo2 = t;
            this.i = i;
        }

        public void run() {
            if (i == 0) {
                synchronizedDemo2.m1();
            } else if (i == 1) {
                synchronizedDemo2.m2();
            } else {
                synchronizedDemo2.m3();
            }
        }
    }

    /**
     * 测试：<br/>
     * 1,加了锁的方法与未加锁的方法同时执行起不到同步的效果
     * 2.锁对象不同时，同样起不到同步效果。
     *
     * @param args
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        SynchronizedDemo2 synchronizedDemo2 = new SynchronizedDemo2();
        new Thread(new SynchronizedDemo2.MyTestThread(0, synchronizedDemo2)).start();
        new Thread(new SynchronizedDemo2.MyTestThread(1, synchronizedDemo2)).start();
        new Thread(new SynchronizedDemo2.MyTestThread(2, synchronizedDemo2)).start();
    }

}
