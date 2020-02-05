package com.wondersgroup.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue
 * 同步队列，是一个容器为0的队列。是一个特殊的TransferQueue
 * 必须现有消费线程等待，才能使用的队列。
 * 与LinkedTransferQueue类似，区别在LinkedTransferQueue可以缓存数据（先add数据再take）,而SynchrousQueue先add数据直接报错无空间。
 *
 *
 * add方法，无阻塞。没有消费线程阻塞等待数据，则抛出异常。
 * put方法，有阻塞。若没有消费线程阻塞等待数据，则阻塞。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_08_SynchrousQueue {

    BlockingQueue<String> queue = new SynchronousQueue<>();

    /**
     * 执行打印结果：
     * output thread thread begin
     * output thread - test put
     * main queue size : 0
     * <p>
     * 结论：
     * put方法为阻塞方法，必须等待有take消费才可结束阻塞。
     *
     * @param 参数
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        final Test_08_SynchrousQueue t = new Test_08_SynchrousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " thread begin ");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "output thread").start();

        /*
         * demo1 没有调用take方法时，直接调用add将抛出异常Queue full
         */
        //add方法非阻塞方法
        //执行add方法将抛出Queue full，原因是SynchronousQueue是一个零容量集合。
//        t.queue.add("test add");

        /*
         * demo2 睡眠3秒后调用add方法（先take，再add），测试通过,未抛出异常。
         */
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t.queue.add("test add");


        /*
         * demo3 先put再take。
         * 测试结果：
         * put方法阻塞到消费者调用take才能终止阻塞状态。
         */
        try {
            //阻塞方法，等到take时才能向后执行。（阻塞方法均有可可能抛出InterruptedException）
            t.queue.put("test put");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " queue size : " + t.queue.size());
    }
}
