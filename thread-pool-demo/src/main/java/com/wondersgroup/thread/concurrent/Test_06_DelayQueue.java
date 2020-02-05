package com.wondersgroup.thread.concurrent;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - DelayQueue
 * 无界容器，支持阻塞，没有数据时处于阻塞状态。
 * 延时队列。根据比较机制，实现自定义处理顺序的队列。常用于定时任务。
 * 如：定时关机。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_06_DelayQueue {

    static BlockingQueue<MyTask_06> queue = new DelayQueue<>();

    /**
     * 执行输出结果：
     * [MyTask_06{compareValue=1580804697855}, MyTask_06{compareValue=1580804698355}, MyTask_06{compareValue=1580804699855}, MyTask_06{compareValue=1580804699355}, MyTask_06{compareValue=1580804698855}]
     * 1580804696855
     * MyTask_06{compareValue=1580804697855}
     * MyTask_06{compareValue=1580804698355}
     * MyTask_06{compareValue=1580804698855}
     * MyTask_06{compareValue=1580804699355}
     * MyTask_06{compareValue=1580804699855}
     *
     * 根据比较规则，从小到大一字排开。
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) throws InterruptedException {
        long value = System.currentTimeMillis();
        MyTask_06 task1 = new MyTask_06(value + 2000);
        MyTask_06 task2 = new MyTask_06(value + 1000);
        MyTask_06 task3 = new MyTask_06(value + 3000);
        MyTask_06 task4 = new MyTask_06(value + 2500);
        MyTask_06 task5 = new MyTask_06(value + 1500);

        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        queue.put(task4);
        queue.put(task5);

        System.out.println(queue);
        System.out.println(value);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }
    }


    /**
     * DelayQueue中能够保存的对象必须要实现Delayed接口，而Delayed接口实现了Comparable接口，
     * 所以通常情况下要重写比较函数。
     *
     * @author Wang si rui
     * @version 1.0
     * @return 返回值
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    static class MyTask_06 implements Delayed {
        private long compareValue;

        public MyTask_06(long compareValue) {
            this.compareValue = compareValue;
        }

        /**
         * 获取计划时长的方法。
         * 根据参数TimeUnit来决定，如何返回结果值（如果TimUnit传入的是秒，则long返回的多少多少秒）。
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            //将数字转换为时间单位整数（例如：1001毫秒，则转换成1秒或1000毫秒）。
            //实际开发过程中不一定传入System.currentTimeMillis，根据实际情况传入
            return unit.convert(compareValue - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 比较大小。自动实现升序
         * 建议和getDelay方法配合完成。
         * 如果在DelayQueue是需要按时间完后的计划任务，必须配合getDelay方法完成。
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyTask_06{" +
                    "compareValue=" + compareValue +
                    '}';
        }
    }
}
