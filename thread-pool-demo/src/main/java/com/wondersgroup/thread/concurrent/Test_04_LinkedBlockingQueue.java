package com.wondersgroup.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingDeque 阻塞队列，队列容量不足自动阻塞，队列容量为0自动阻塞，等到有数据时再取出。
 * put & take - 自动阻塞
 * put自动阻塞，队列容量慢后，自动阻塞
 * take自动阻塞方法，队列容量为0后自动阻塞。
 *
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_04_LinkedBlockingQueue {

    final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    final Random r = new Random();

    /**
     * 执行输出结果（每隔一秒输出一次）：
     * consumer1 - value2772
     * consumer2 - value3270
     * consumer1 - value1601
     * consumer0 - value4597
     * consumer2 - value6332
     * ..............
     *
     * 结论，队列数据量为0时，take方法自动阻塞，等数据量大于0时在从中take数据。
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        final Test_04_LinkedBlockingQueue t = new Test_04_LinkedBlockingQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //每加一个数据睡眠一秒
                        t.queue.put("value" + t.r.nextInt(10000));
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "producer").start();

        //同时开启三个线程，死循环从queue中取数据
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            //take第一条数据，容量减一
                            System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "consumer" + i).start();
        }

    }
}
