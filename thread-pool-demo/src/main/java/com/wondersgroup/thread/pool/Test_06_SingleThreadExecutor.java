package com.wondersgroup.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单例线程池：单一容量的线程池。所有的任务交给一个线程来处理。
 * 使用场景：保证任务顺序使用。如：游戏大厅中的公共频道聊天（喇叭）、秒杀商品（保证商品抢购顺序有序进行）。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_06_SingleThreadExecutor {

    /**a
     * 执行输出结果：
     * java.util.concurrent.Executors$FinalizableDelegatedExecutorService@1540e19d
     * pool-1-thread-1 - test executor
     * pool-1-thread-1 - test executor
     * pool-1-thread-1 - test executor
     * pool-1-thread-1 - test executor
     * pool-1-thread-1 - test executor
     * <p>
     * 结论：所有任务交给一个线程来执行，所有待执行任务进入队列，待前一个任务执行完毕后再执行下一个任务。
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
        ExecutorService service = Executors.newSingleThreadExecutor();

        System.out.println(service);

        for (int i = 0; i < 5; i++) {
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


    }
}
