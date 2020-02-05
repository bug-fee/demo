package com.wonders.demo.thread.volatileDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * wait()方法的作用是让当前线程进入等待状态，同时wait()也会让当前线程释放它所持有的锁（使其它锁同一个对象的方法可以执行）。
 * notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class VolatileExercise {
    volatile List<Integer> container = new ArrayList<Integer>();
    //门栓
    AtomicInteger max = new AtomicInteger(5);

    public void addElement() {
        synchronized (container) {
            for (int i = 0; i < 10; i++) {
                if (max.get() != 0) {
                    System.out.println("add element:" + i);
                    container.add(i);
                    try {
                        //调用notify和wait方法时，应使用被锁的对象调用。如果锁对象为当前对象可调用“this.”或不写this直接调用
                        //唤醒与当前线程锁了同一个“对象”的 “其它”线程。唤醒在此对象监视器上等待的所有线程。
                        container.notifyAll();
                        //唤醒在此对象监视器上等待的单个线程。
//                        notify();
                        //等待其它线程唤醒
                        container.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    container.notifyAll();
                    return;
                }
            }
        }
    }

    public int getCountElement() {
        while (true) {
            System.out.println("element count:" + container.size());
            synchronized (container) {
                max.decrementAndGet();
                container.notifyAll();
                try {
                    container.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (container.size() == 5) {
                    max.decrementAndGet();
                    System.out.println("stop addtion");
                    container.notifyAll();
                    return container.size();
                }
            }
        }
    }

    /**
     * 练习题：<br/>
     * 自定义容器，提供新增元素（add）和获取元素数量（size）的方法。<br/>
     * 启动两个线程，线程1向容器中新增10个数据，线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止所有线程。
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
        final VolatileExercise volatileExercise = new VolatileExercise();
        new Thread(new Runnable() {
            public void run() {
                volatileExercise.addElement();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                volatileExercise.getCountElement();
            }
        }).start();
    }
}
