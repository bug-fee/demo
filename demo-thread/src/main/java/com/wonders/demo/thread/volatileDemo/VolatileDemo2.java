package com.wonders.demo.thread.volatileDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 关键字
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 非原子性问题：<br/>
 * volatile 只能保证数据可见性，不能保证原子性，只是内存数据可见。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class VolatileDemo2 {
    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        final VolatileDemo2 volatileDemo2 = new VolatileDemo2();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new Runnable() {
                public void run() {
                    volatileDemo2.m();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                //当前线程持有某一线程的对象时调用某一线程的join方法，会等待该线程被执行完毕之后，当前线程才会向后执行。
                //表示等待某一线程执行完毕，才执行当前线程。
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
         * 预期返回10万，但是结果：返回7万(50% similarity)。
         * 由于volatile关键字修饰的变量无法保证原子性：计算和保存过程中CPU操作多次，还未写入时就已被其它线程读取并修改。
         * 添加synchronized 关键字可解决该问题
         */
        System.out.println(volatileDemo2.count);
    }

}
