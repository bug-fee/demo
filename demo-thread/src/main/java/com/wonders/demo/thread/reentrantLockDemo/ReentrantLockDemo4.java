package com.wonders.demo.thread.reentrantLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 公平锁：<br/>
 * 应用场景：轮循机制，但不建议并发量大的业务场景，因为同一个锁对象其他线程要访问有等待时间。<br/>
 * 相对效率比synchronized要高，量级较轻。推荐使用。<br/>
 * 使用重入锁，必须手动释放锁标记，一般是在finally代码块中定义释放锁标记的unloak方法
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class ReentrantLockDemo4 extends Thread {
    //构造函数传入参数true，创建公平锁（交替运行）。
    public static final ReentrantLock lock = new ReentrantLock(true);
    //构造函数不传入参数，创建非公平锁（多线程执行时竞争锁标志）。
//    public static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": get lock");
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }

    }

    /**
     * 公平锁可以使多个“锁同一个对象”的线程交替执行。
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
        ReentrantLockDemo4 reentrantLockDemo4 = new ReentrantLockDemo4();
        //new Thread时需要传入Runnable的实现类。由于Thread实现了Runnable接口，所以Thread的实现类可以直接作为入参创建线程。
        Thread t1 = new Thread(reentrantLockDemo4, "thread1");
        Thread t2 = new Thread(reentrantLockDemo4, "thread2");
        t1.start();
        t2.start();
    }
}
