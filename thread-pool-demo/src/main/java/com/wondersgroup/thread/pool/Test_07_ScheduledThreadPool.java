package com.wondersgroup.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool
 * 计划任务线程池。可以根据计划自动执行任务的线程池。
 * 使用场景：
 * 计划任务时选用（与DelaydQueue考量，选哪个），如：电信行业中的数据整理，每分钟整理，每小时整理，你每天整理。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_07_ScheduledThreadPool {
    /**
     * 执行结果：每隔0.3秒执行一次任务。
     * 特性：定时任务执行时间越久，时间偏差越大（线程池分配线程需要一定的时间、线程获得时间片也需要一定的时间）。
     * 第一个任务开始执行，不管是否执行结束，开始计时300毫秒后执行第二个任务（必须第一个任务开始执行，才计时）。
     * <p>
     * 输出结果：
     * java.util.concurrent.ScheduledThreadPoolExecutor@1540e19d[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
     * pool-1-thread-1
     * pool-1-thread-1
     * pool-1-thread-2
     * pool-1-thread-2
     * pool-1-thread-3
     * ..................
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
        //初始线程池容量为3个线层。
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        System.out.println(service);

        //定时完成任务，scheduleAtFixedRate(Runnable,start_limit,limit,timeunit)
        //Runnable - 要执行的任务
        //start_limit - 第一次任务执行的间隔
        //limit - 多次任务执行的间隔
        //timeunit - 多次任务执行间隔的时间单位
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            }
        }, 0, 300, TimeUnit.MICROSECONDS);

    }
}
