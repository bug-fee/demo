package com.wondersgroup.thread.pool;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 分支连接线程池
 * 分支合并线程池（mapduce类似的设计思想，类似于递归循环）。适合用于处理复杂任务。
 * 初始化线程容量与CPU核心数有关。
 * 线程池中运行的内容必须是ForkJoinTask的子类型（RecursiveTask,RecursiveAction）.
 * ForkJlinPool --分支合并线程池。可以递归完成复杂任务。要求可分支合并的任务必须是ForkJoinTask类型的子类型。其中提供了分支和合并的能力。
 * 当前ForkJoinTask类型提供了两个抽象子类型，Recursive有返回结果的分支合并任务，RecursiveAction无返回结果的分支合并任务（Callable/Runnable）。
 * compute方法就是任务的执行逻辑。
 * ForkJoinPool没有所谓的容量。默认都是一个线程。根据任务自动的分支新的子线程。当子线程任务结束后，自动合并。所谓自动是根据fork和join两个方法实现的。
 * 底层机制：利用空间换取时间，若计算复杂度也高，效率越高，同时取决于CPU计算能力。
 * 应用场景：主要是做科学计算或天文计算的。或者数据分析。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_08_ForkJoinPool {

    final static int[] numbers = new int[1000000];
    final static int MAX_SIZE = 50000;
    final static Random r = new Random();

    static {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(1000);
        }
    }

    /**
     * 继承递归任务接口(RecursiveTask)
     * 要求必须继承ForkJoinTask超类，否则无法实现分支合并
     *
     * @author Wang si rui
     * @version 1.0
     * @return 返回值
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    static class AddTask extends RecursiveTask<Long> {
        int begin, end;

        public AddTask(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        /**
         * 任务的执行逻辑，具体业务处理
         * @author Wang si rui
         * @version 1.0
         * @param 参数
         * @return 返回值
         * @lastAuthor 最后修改人
         * @lastDate最后时间
         * @Rewrite record 修改记录：
         * 				1、 <br>
         */
        protected Long compute() {
            System.out.println(Thread.currentThread().getName() + " - test frokJoinPool");
            if ((end - begin) < MAX_SIZE) {
                long sum = 0L;
                for (int i = begin; i < end; i++) {
                    sum += numbers[i];
                }
                return sum;
            } else {
                //将任务总量分隔成一半递归执行
                int middle = begin + (end - begin) / 2;
                AddTask task1 = new AddTask(begin, middle);
                AddTask task2 = new AddTask(middle, end);
                //fork一个线程执行新任务
                task1.fork();
                //就是用于开启新的任务的。就是分支工作的。就是开启一个新的线程任务
                task2.fork();
                //合并执行结果。将任务的结果获取。这是一个阻塞方法，一定会得到结果数据。
                return task1.join() + task2.join();
            }
        }
    }

    /**
     * 执行输出结果：
     * 499891736
     * ForkJoinPool-1-worker-1 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-1 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-1 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-5 - test frokJoinPool
     * ForkJoinPool-1-worker-2 - test frokJoinPool
     * 499891736
     * <p>
     * <p>
     * 结论：
     * fork 20+个任务分批次执行（线程复用）。若数组长度大于等于5000则分隔成两个线程同时执行。
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long result = 0L;
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i];
        }
        System.out.println(result);

        //创建分支连接线程池对象
        ForkJoinPool pool = new ForkJoinPool();
        //开启任务
        AddTask task = new AddTask(0, numbers.length);
        //执行任务
        Future<Long> future = pool.submit(task);
        //返回执行结果
        System.out.println(future.get());
    }
}
