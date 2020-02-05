package com.wonders.demo.thread.reentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 可打断：<br/>
 * 阻塞状态：(包括普通阻塞，等待队列，锁池队列)<br/>
 * 普通阻塞：sleep(10000)，可以被打断。调用thread.interrupt()方法，可以打断阻塞状态，抛出异常。<br/>
 * 等待队列：wait()方法被调用，也是一种阻塞状态，只能由notify唤醒。无法打断。<br/>
 * 锁池队列：暂时无法获取锁标记的线程。不是所有的锁池队列都可被打断。<br/>
 * <pre>
 *          使用ReentrantLock的lock方法，获取锁标记的时候，如果需要阻塞等待锁标记，无法被打断。<br/>
 *          使用ReentrantLock的lockInterruptbly方法，获取锁标记的时候，如果需要阻塞等待，可以被打断。<br/>
 * </pre>
 * synchronized加锁的线程无法被打断。
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class ReentrantLockDemo3 {
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
            lock.unlock();
        }
    }

    public void m2() {
        //阻塞至获取锁标记
        try {
            //阻塞等待锁：等待并获取锁标志，并可以被其他线程尝试打断阻塞状态。(该线程处于阻塞状态，等待获取锁标记后才会执行，阻塞期间可以被打断)。
            lock.lockInterruptibly();
            System.out.println(" m2 method ");
        } catch (InterruptedException e) {
            //线程被打断后抛出异常
            System.out.println(" m2 method interrupted ");
        } finally {
            //线程被打断后抛出异常，finally语句必定执行
            try {
                lock.unlock();//解锁
            } catch (Exception e) {
                //未获得锁标记时调用释放锁的方法，会抛出异常。
                e.printStackTrace();
            }
        }

    }

    /**
     * 执行结果为：<br/>
     * 线程2被打断后抛出异常，输出 “m2 method interrupted”
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
        final ReentrantLockDemo3 reentrantLockDemo3 = new ReentrantLockDemo3();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                reentrantLockDemo3.m1();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                reentrantLockDemo3.m2();
            }
        });
        thread2.start();

        try {
            //睡眠一秒钟
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //睡眠状态不可打断，仅 调用lockInterruptibly()方法的线程可以打断
        thread1.interrupt();

        //打断线程2的休眠状态。非正常结束阻塞状态的线程，都会抛出异常。
        //打断只能打断阻塞状态的线程，不能打断执行中的线程。
        thread2.interrupt();

    }
}
