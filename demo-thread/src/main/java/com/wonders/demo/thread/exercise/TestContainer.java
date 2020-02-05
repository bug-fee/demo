package com.wonders.demo.thread.exercise;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 消费者模式：多线程并发演示案例
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class TestContainer {
    //容器的最大容量
    private static final short MAX = 10;
    //容器
    public LinkedList<Integer> container = new LinkedList<Integer>();
    //容器中剩余元素总数
    public int count = 0;


    public synchronized void addElement(Integer i) {
        while (container.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        container.add(i);
        System.out.println(Thread.currentThread().getName() + ":add element");
        ++count;
        System.out.println(count);
        this.notifyAll();
    }

    public synchronized Integer getElement() {
        while (container.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        --count;
        System.out.println(Thread.currentThread().getName() + ":remove element");
        System.out.println(count);
        return container.removeFirst();
    }

    /**
     * 多线程并发：同时开启30个线程，从同一个容器中存、取数据。容器中最多能保存10个数据。
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
        final TestContainer testContainer = new TestContainer();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        testContainer.getElement();
                    }
                }
            }, "getThread" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 25; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 2; j++) {
                        testContainer.addElement(finalI * j);
                    }
                }
            }, "addThread" + i).start();
        }


    }
}
