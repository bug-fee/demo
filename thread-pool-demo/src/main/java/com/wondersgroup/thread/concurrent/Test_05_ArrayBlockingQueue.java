package com.wondersgroup.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - ArrayBlockingQueue
 * 底层数组实现的有界队列。自动阻塞。根据调用API（add/put/offer）不同，有不同特性。
 * 当容量不足的时候，有阻塞能力。
 * add方法在容量不足的时候抛出异常。
 * put方法在容量不足的时候阻塞等待（等待消费）。
 * offer方法:
 * (1)单参数offer方法，容量不足的时候，返回false。
 * (2)三参数offer方法（offer(value,times,timeunit)），容量不足的时候，阻塞times时长（单位为timeunit），
 * 如果在阻塞时长内，有容量空闲，新增数据返回true。如果阻塞时长范围内无容量空间，放弃新增数据，返回false。
 * (3)put、take方法为阻塞方法，等待有空间/数据时获取并返回true
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_05_ArrayBlockingQueue {
    final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    /**
     * 执行打印结果：
     * demo1
     * add method : true
     * add method : true
     * add method : true
     * Exception in thread "main" java.lang.IllegalStateException: Queue full
     * <p>
     * demo2
     * put method : 0
     * put method : 1
     * put method : 2
     * <p>
     * demo3
     * offer method : true
     * offer method : true
     * offer method : true
     * offer method : false
     * offer method : false
     * [value0, value1, value2]
     * <p>
     * demo4
     * offer method : true
     * offer method : true
     * offer method : true
     * offer method : false
     * offer method : false
     * [value0, value1, value2]
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
        final Test_05_ArrayBlockingQueue t = new Test_05_ArrayBlockingQueue();
        for (int i = 0; i < 5; i++) {


            /*
             * demo 1 add方法
             * 直接填充数据，容量不足时抛出异常
             */
            //add方法在容量不足时抛出 java.lang.IllegalStateException: Queue full 异常
            //向queue中填充元素
//            System.out.println("add method : " + t.queue.add("value" + i));


            /*
             *demo 2 put方法
             * 填充数据，容量不足时处于阻塞状态
             */
//            try {
//                //
//                t.queue.put("put" + i);
//            } catch (InterruptedException e) {//线程阻塞可能抛出InterruptedException
//                e.printStackTrace();
//            }
//            System.out.println("put method : " + i);


            /*
             * demo3 offer方法单参数
             * 填充数据，容量不足时返回false，超出容量的部分被舍弃
             *
             */
//            System.out.println("offer method : " + t.queue.offer("value" + i));


            /*
             * demo3 offer方法多参数
             * 阻塞一秒（参数）等待有容量后填充入队列，超过一秒没有空间则返回false，不抛出异常。
             */
            try {
                System.out.println("offer method : " + t.queue.offer("value" + i, 1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(t.queue);

    }
}
