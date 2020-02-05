package com.wonders.demo.thread.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 门栓 - CountDownLatch
 * 可以和锁混合使用，或者替代锁的功能。
 * 在门栓未完全开放之前等待。当门栓完全开放后执行。
 * 避免锁的效率低下问题。 门栓的效率要比synchronized高很多(线程无需进入等待队列)
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class CountDownLatchDemo {
    CountDownLatch countDownLatch = new CountDownLatch(5);

    public void m1() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 method end");
    }

    public void m2() {
        for (int i = 0; i < 10; i++) {
            if (countDownLatch.getCount() > 0) {
                System.out.println("latch count =" + countDownLatch.getCount());
                //减门栓上的锁
                countDownLatch.countDown();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2 method :" + i);
        }

    }

    public static void main(String[] args) {
        final CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        new Thread(new Runnable() {
            public void run() {
                countDownLatchDemo.m1();
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                countDownLatchDemo.m2();

            }
        }).start();


    }

}
