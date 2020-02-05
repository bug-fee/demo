package com.wonders.demo.thread.reentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 尝试锁
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class ReentrantLockDemo2 {
    Lock lock = new ReentrantLock();

    public void m1() {
        //上锁
        lock.lock();
        try {

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
            lock.unlock();//解锁
        }
    }

    public void m2() {
        boolean isLocked = false;
        //尝试锁，如果有锁，无法获取所标记，返回false
        //如果获取锁标记，返回true
//        isLocked = lock.tryLock();
        try {
            //阻塞尝试锁，阻塞参数代表时长，在该时长内尝试获取锁标记。
            //如果超时，不等待，直接返回。如果在指定时间内获取到了锁标记，则终止阻塞状态，返回tru，执行同步代码）。
            isLocked = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * 1.执行同步代码:当前锁已经被锁住，执行完之后可以释放锁。
         * 2.执行非同步代码：当前锁已经被锁住，可以执行其它逻辑。
         */
        if (isLocked) {
            System.out.println(" m2() method is synchronized ");
        } else {
            System.out.println(" m2() method is unsynchronized ");
        }

        //尝试锁在释放锁时一定要判断是否获取到锁标记，如果当前线程没有获取到锁标记会抛出异常
        if (isLocked) {
            lock.unlock();
        }
    }

    /**
     * “尝试锁” 两种用法:<br/>
     * 1.尝试获取锁如果能获取到，返回true标记，可根据锁标记获取情况执行相应业务代码<br/>
     * 2.阻塞当前线程并尝试获取锁，如果能获取到返回true标记并提前结束阻塞状态。如果超时未获取到锁，返回false
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
        final ReentrantLockDemo2 reentrantLockDemo2 = new ReentrantLockDemo2();
        new Thread(new Runnable() {
            public void run() {
                reentrantLockDemo2.m1();
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
                reentrantLockDemo2.m2();
            }
        }).start();


    }
}
