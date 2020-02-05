package com.wondersgroup.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 无限容量限制的线程池（最大容量默认为Integer.MAX_VALUE）
 * <p>
 * CachedThreadPool
 * 缓存的线程池。容量不限（Integer.MAX_VALUE）。自动扩容。容量管理策略：如果线程池汇总的线程数量不满足任务执行，
 * 创建新的线程。每次有新任务无法即使处理的时候，都会创建新的线程。
 * 默认线程空闲60秒，自动销毁。当线程池中的线程空闲时长达到一定的临界值（默认 60秒），自动释放线程。
 * 应用场景：内部应用或测试应用。
 * 内部应用，有条件的内部数据瞬时处理时应用，如：电信平台夜间执行数据整理（有把握在短时间内处理完所有工作，且对硬件和软件有足够的信心）
 * 测试应用：测试的时候，尝试得到硬件或软件的最高负载量，用于提供FixedThreadPool容量的指导。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_05_CachedThreadPool {

    /**
     * 执行输出结果：
     * java.util.concurrent.ThreadPoolExecutor@677327b6[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
     * java.util.concurrent.ThreadPoolExecutor@677327b6[Running, pool size = 5, active threads = 5, queued tasks = 0, completed tasks = 0]
     * pool-1-thread-1
     * pool-1-thread-2
     * pool-1-thread-3
     * pool-1-thread-4
     * pool-1-thread-5
     * java.util.concurrent.ThreadPoolExecutor@677327b6[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 5]
     *
     * 结论：任务执行完毕后60秒后自动回收线程
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

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
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(service);
    }
}
