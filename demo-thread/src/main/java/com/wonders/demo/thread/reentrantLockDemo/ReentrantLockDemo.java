package com.wonders.demo.thread.reentrantLockDemo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * ReentrantLock 重入锁
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class ReentrantLockDemo {
    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            //上锁
            lock.lock();
            System.out.println(" m1 start ");
            for (int i = 0; i < 10; i++) {
                System.out.println(" m1() method " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" m1 end ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //避免锁无法释放
            lock.unlock();//解锁
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println("m2() method");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 执行结果：当method1 运行结束之后才能运行method2
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
        final ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(new Runnable() {
            public void run() {
                reentrantLockDemo.m1();
            }
        }).start();

        try {
            //睡眠一秒钟
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                reentrantLockDemo.m2();
            }
        }).start();


    }
}
