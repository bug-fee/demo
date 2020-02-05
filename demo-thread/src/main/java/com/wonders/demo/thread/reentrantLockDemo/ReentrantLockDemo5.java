package com.wonders.demo.thread.reentrantLockDemo;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 生产者消费者模式
 * 重入锁&条件<br/>
 * 条件 - condition，为lock增加条件。当条件满足时做什么事情，如加锁或解锁；
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class ReentrantLockDemo5<E> {
    //容器
    private final LinkedList<E> contianer = new LinkedList<E>();
    //容器最大容量
    private static final int MAX = 10;
    //当前容器中的元素数量
    private int count = 0;
    //重入锁
    private ReentrantLock lock = new ReentrantLock();
    //锁条件：生产者
    Condition producer = lock.newCondition();
    //锁条件：消费者
    Condition consumer = lock.newCondition();

    /**
     * 往容器中添加元素
     *
     * @param i
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void put(E i) {
        lock.lock();
        try {
            while (count == MAX) {
                //进入等待队列释放锁标记
                //借助条件，进入等待队列
                producer.await();
                System.out.println(Thread.currentThread().getName() + "等待...");
            }
            System.out.println(Thread.currentThread().getName() + "put...");
            contianer.add(i);
            //增加容器中的数量
            count++;
            //唤醒消费者
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //方法结束之后释放锁
            lock.unlock();
        }

    }

    /**
     * 从容器中获取元素
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public E get() {
        E e = null;
        lock.lock();
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName() + "等待...");
                //进入等待队列释放锁标记
                //借助条件，进入等待队列
                consumer.await();
            }
            //唤醒所有生产者
            producer.signalAll();
            e = contianer.removeFirst();
            count--;
            System.out.println(Thread.currentThread().getName() + "get" + e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
        //返回第一个元素并删除
        return e;
    }

    /**
     * 测试结果：<br/>
     * 1.容器中的元素数量永远不会超过10个；<br/>
     * 2.当数量低于10个时往容器中添加元素；<br/>
     * 3.消费者不断从容器中获取数据；<br/>
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
        final ReentrantLockDemo5<Integer> elementFactory = new ReentrantLockDemo5<Integer>();
        //启动10个线程，每次向容器中添加5个元素
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    int sub = finalI + 1;
                    for (int j = 0; j < 5; j++) {
                        //添加元素
                        elementFactory.put(sub * j);
                    }
                }
            });
            thread.setName("producer" + finalI);
            thread.start();
        }

        //启动25个线程，每次向容器中获取2个元素
        for (int i = 0; i < 25; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 2; j++) {
                        elementFactory.get();
                    }
                }
            });
            thread.setName("consumer" + finalI);
            thread.start();
        }
    }
}
