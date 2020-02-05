package com.wondersgroup.thread.pool;

import java.util.concurrent.Executor;

/**
 * 线程池
 * Executor - 线程池底层处理机制
 * 在使用线程池时候，底层如何调用线程中的逻辑
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * <p>
 * Executor
 * 线程池顶级接口。方法时用于处理任务的一个服务方法。调用者提供Runnable接口的实现，线程池通过线程执行这个Runnable。
 * 服务方法是无返回值的，原因是Runnable接口中的run方法无返回值。
 * 常用方法 -void execute(Runnable)
 * 作用是：启动线程任务的
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_01_MyExecutor implements Executor {
    public static void main(String[] args) {
        new Test_01_MyExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - test excutor");
            }
        });
    }


    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}


