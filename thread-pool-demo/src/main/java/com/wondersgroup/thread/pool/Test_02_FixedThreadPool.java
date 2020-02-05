package com.wondersgroup.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * <p>
 * Executors - Executor的工具类。类似Collection和Collections的关系。
 * 可以快速的提供若干种线程池。如：固定容量的，容量为1等各种线程池。
 * 线程池是一个进程级的重量级资源。默认的生命周期和JVM一致。当开启线程池后，直到JVM关闭为止，是线程池的默认生命周期。
 * 乳沟手工调用shutdown方法，那么线程池执行所有的任务后，自动关闭。
 * 开始 - 创建线程池
 * 结束 - JVM关闭或调用shutdown并处理完所有的任务。
 * 类似Arrays，Collections等工具类型的功用。
 * <p>
 * ExecutorService - 线程池服务类型。所有的线程池类型都实现这个接口。实现这个接口。实现这个接口，代表可以提供线程池能力。
 * Executor接口的子接口。提供了一个新的服务方法，submit,有返回值（Future类型）。
 * submit方法提供了overload方法。其中有参数类型为Runnable的，不需要提供返回值的；
 * 有参数类型为Callable，可以提供线程执行后的返回值；
 * Future,是submit方法的返回值。代表未来，也就是线程执行结束后的一种结果。如返回值。
 * <p>
 * 常用方法 -void execute(Runnable), Future submit(Callable), Future submit(Runnable)
 * 线程池状态：Running , shutingDown , Termited
 * Running -- 线程池正在执行中。活动状态。
 * ShuttingDown -- 线程池正在关闭过程中。优雅关闭。一旦进入这个状态，线程池不再接受新的任务，处理所有已接受的任务，处理完毕后，不关闭线程池。
 * Termited -- 线程时已经关闭。
 * <p>
 * Future
 * 未来结果，代表线程任务执行结束后的结果。获取线程执行结果的方式是通过get方法获取的。
 * get无参，阻塞等待线程池执行结束，并得到结果。get有参，阻塞固定时长，等待线程执行结束后的结果，如果在阻塞时长范围内，线程未执行结束，抛出异常。
 * 常用方法： T get() T get(long,TimeUnit)
 * <p>
 * Callable
 * 可执行接口。类似Runnable接口。也是可以启动一个线程的接口。其中定义的方法是call。call方法的作用和Runnable中的run方法完全一致。call方法有返回值。
 * 接口方法：Object call();相当于Runnable接口中的run方法。区别为此方法有返回值。
 * 不能抛出已检查异常。
 * <p>
 * 和Runnable接口的选择 - 需要返回值或需要抛出异常时，使用Callable，其他情况可以任意选择。
 * <p>
 * FixedThreadPool - 固定容量线程池。创建线程池的时候，容量固定。
 * 容量固定的线程池。活动状态和线程池容量是有上限的线程池。所有的线程池中，都有一个任务队列。
 * 使用的是BlockingQueue<Runnable>作为任务的载体，当任务数量大于线程池容量的时候，没有运行的任务保存在任务队列中，当线程有空闲的，自动从队列中取出任务执行。
 * 使用场景：大多说情况下，使用的线程池，首选推荐FixedThreadPool。OS系统和硬件是有线程支持上线的。不能随意的无限制提供线程池。
 * 线程池默认的容量上限是Integer.MAX_VALUE
 * 常见的线程池容量：一般PC - 200 ； 服务器 - 1000-10000
 * 80个线程池基本上能处理2000个并发访问量了（并发处理能力换算原则：线程数量 * [10-18之间] ）。
 * queued tsks -- 任务队列
 * completed tasks -- 结束任务队列
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_02_FixedThreadPool {

    /**
     * 执行打印结果：
     * D:\workbench\jdk\jdk1.8.0_77\bin\java.exe -javaagent:D:\workbench\ideaIU-2018.2.4\lib\idea_rt.jar=2826:D:\workbench\ideaIU-2018.2.4\bin -Dfile.encoding=UTF-8 -classpath D:\workbench\jdk\jdk1.8.0_77\jre\lib\charsets.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\deploy.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\access-bridge-64.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\cldrdata.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\dnsns.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\jaccess.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\jfxrt.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\localedata.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\nashorn.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\sunec.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\sunjce_provider.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\sunmscapi.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\sunpkcs11.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\ext\zipfs.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\javaws.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\jce.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\jfr.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\jfxswt.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\jsse.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\management-agent.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\plugin.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\resources.jar;D:\workbench\jdk\jdk1.8.0_77\jre\lib\rt.jar;D:\workspace\demo\thread-pool-demo\target\classes com.wondersgroup.thread.pool.Test_02_FixedThreadPool
     * java.util.concurrent.ThreadPoolExecutor@135fbaa4[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
     * false
     * true
     * java.util.concurrent.ThreadPoolExecutor@135fbaa4[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
     * pool-1-thread-3 - test executor
     * pool-1-thread-1 - test executor
     * pool-1-thread-5 - test executor
     * pool-1-thread-4 - test executor
     * pool-1-thread-3 - test executor
     * pool-1-thread-2 - test executor
     * <p>
     * 结论：池线程3复用了。
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
        /*
         * ExecutorService - 线程池服务类型。所有的线程池类型都实现这个接口
         *  实现这个接口，代表可以提供线程池能力。
         *  shutdown - 优雅关闭，不是强项关闭线程池，回收线程池中的资源。而是不再进行新的任务，将已接收的任务处理完毕后再关闭。
         *  Executers -Executer的工具类。类似Collection和Collections的关系。
         *  可以更简单的创建若干种线程池。
         */
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MICROSECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - test executor");

                }
            });
        }

        System.out.println(service);

        //调用shutdown方法后表示关闭线程池（该方法非阻塞方法，只是标记为待关闭状态，所有线程执行完毕后自动关闭线程池），后续使用service对象继续调用excute、call方法将无效并抛出异常（标记为关闭后后续不再接收新的任务）。
        service.shutdown();
        //是否已经结束，相当于回收了资源。
        System.out.println(service.isTerminated());
        //是否已经关闭，是否调用过shutdown方法
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //shutingDown结束后,线程池将自动标记为Termited状态。此时继续调用excute和call方法也将抛出异常。不再接受新的任务调度。
    }
}
