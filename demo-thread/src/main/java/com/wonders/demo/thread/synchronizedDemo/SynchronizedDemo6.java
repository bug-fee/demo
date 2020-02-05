package com.wonders.demo.thread.synchronizedDemo;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 同步方法 - 锁与异常<br/>
 * 当同步方法中发生异常的时候，自动释放锁。不会影响其他线程的执行。<br/>
 * 注意，同步业务逻辑中，如果发生异常如何处理。<br/>
 * try catch后对业务数据还原,并重新执行。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo6 {
    int i = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " - start");
        while (true) {
            i++;
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5) {
                //抛出异常：被除数不能为 0
                i = i / 0;
            }
        }
    }

    /**
     * 执行结果：线程1和线程2锁对象相同，两个线程同时访问同一个锁对象的方法，可实现同步。<br/>
     * 1.线程1 抛出异常释放锁。
     * 2.锁被释放，线程2开始执行同步方法。
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
        final SynchronizedDemo6 synchronizedDemo6 = new SynchronizedDemo6();
        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo6.m();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo6.m();
            }
        }, "t2").start();
    }

}
