package com.wondersgroup.thread.pool;
/**
 * ThreadPoolExecutor -- 线程池自定义
 * 线程池 底层实现，除ForJoinPool外，其他常用线程池底层都是使用ThreadPoolExecutor实现的。
 * 构造器：
 * public ThreadPoolExecutor(
 * int corePoolSize,//核心容量，创建线程池的时候，默认有多少线程。也是线程池保持的最少线程数。
 * int maximumPoolSize,//最大容量，线程池最多有多少线程。
 * long keepAliveTime,//声明周期，0为永久。当线程空闲多久后，自动回收
 * TimeUnit unit,//生命周期单位，为声明周期提供单位，如：秒，毫秒
 * BlockingQueue<Runnable> workAueue//任务队列，阻塞队列。泛型必须是Runnable。
 * )
 *
 * <p>
 * 使用场景：默认提供的线程池不满足条件时使用。如：初试线程数4，最大线程数200，线程空闲周期30秒。
 * 开发过程中有9成的可能性都是使用自定义的线程池
 * <p>
 * 模拟实现固定容量线程池
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test_09_ThreadPoolExecutor {

    /**
     * 本案例模拟构建FixedThreadPool
     * @author Wang si rui
     * @version 1.0
     * @param 参数
     * @return 返回值
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 				1、 <br>
     */
    public static void main(String[] args) {
        // 模拟fixedThreadPool， 核心线程5个，最大容量5个，线程的生命周期无限，线程生命周期单位毫秒，无界阻塞队列。
        //和县线程数推荐为CPU的逻辑核心数
        ExecutorService service =
                new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 6; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - test executor");
                }
            });
        }

        System.out.println(service);

        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }

}