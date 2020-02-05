package com.wondersgroup.thread.concurrent;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 并发容器 - LinkedTransferQueue
 * 转移队列 - 无容量
 * 转移队列，使用transfer方法，实现数据的即时处理。没有消费者，就阻塞。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * <p>
 * add - 队列保存数据，不做阻塞等待
 * transfer - 是TransferQueue的特有方法。必须有消费者（take()），如果没有任意线程消费数据，transfer方法阻塞。一般用于处理即时消息（例如：打电话，必须要有对方接电话）。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_07_TransferQueue {

    TransferQueue<String> queue = new LinkedTransferQueue<>();

    /**
     * 执行输出结果：
     * output threadthread begin
     * output thread - test string
     * <p>
     * <p>
     * 结论：TransferQueue.take数据时如果容器中没有数据则处于阻塞状态
     * 当其它线程在TransferQueue中填充（transfer）数据时，take方可立即拿到被传递过来的数据。
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
        final Test_07_TransferQueue t = new Test_07_TransferQueue();

        /*
         * demo 1
         * 先从queue中获取数据，测试结果为阻塞状态，等待填充入数据后立即take到数据。
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "thread begin ");
//                    //从容器中获取数据，该方法为阻塞方法。
//                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "output thread").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            //向容器中传输数据
//            t.queue.transfer("test string");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        /*
         * demo2 先在容器中填充(transfer)数据，再take数据。
         * 执行输出结果：
         * 无
         *
         * 结论：
         * 阻塞在了transfer函数处，该方法为阻塞方法等待其它线程调用take函数（也是阻塞函数）。
         */
//        try {
//            t.queue.transfer("test string");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "thread begin ");
//                    //从容器中获取数据，该方法为阻塞方法。
//                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "output thread").start();


        /*
         * demo3 开启两个不同的线程，先调用transfer函数，睡眠两秒后再调用take函数。
         *
         * 执行输出结果：
         * add ok
         * output threadthread begin
         * output thread - test String
         *
         * 结论：take/transfer函数均为阻塞函数等待对方传输/获取数据后结束执行
         * add方法非阻塞方法
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //transfer方法为阻塞方法
////                    t.queue.transfer("test String");
//                    //add方法非阻塞方法
//                    t.queue.add("test String");
//                    System.out.println("add ok");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (
//                InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " thread begin ");
//                    //从容器中获取数据，该方法为阻塞方法。
//                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "output thread").start();




        /*
         * demo4 开启两个不同的线程，先调用transfer函数，睡眠两秒后再调用take函数。
         *
         * 执行输出结果（add ok 在后面输出，take在前输出）：
         * output thread thread begin
         * output thread - test String
         * add ok
         *
         * 结论：transfer - 是TransferQueue的特有方法。必须有消费者（take()），如果没有任意线程消费数据，transfer方法阻塞。
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //transfer方法为阻塞方法
//                    t.queue.transfer("test String");
//                    //add方法非阻塞方法
////                    t.queue.add("test String");
//                    System.out.println("add ok");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (
//                InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " thread begin ");
//                    //从容器中获取数据，该方法为阻塞方法。
//                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "output thread").start();








        /*
         * demo5 先调用add方法，再调用transfer方法。
         */
        //add方法非阻塞方法
        t.queue.add("test String");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " thread begin ");
                    //从容器中获取数据，该方法为阻塞方法。
                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "output thread").start();

    }


}
