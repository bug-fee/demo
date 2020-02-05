package com.wondersgroup.thread.pool;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 就是一个Map。 key -> Thread.getCurrentThread(). value -> 线程需要保存的变量。
 * ThreadLocal.set(value) -> map.put(Thread.getCurrentThread(),value);
 * ThreadLocal.get() -> map.get(Thread.getCurrentThread());
 * 内存问题：在并发量高的时候，可能有内存溢出。
 * 使用ThreadLocacl的时候，一定注意回收资源问题，每个线程结束之前，将当前线程保存的线程变量一定要删除。
 * ThreadLocal.remove();
 * 如果线程变量没有删除，server容器线程复用时，第二个请求过来将可以拿到理应死亡的线程的变量。
 * ThreadLocal对象生命周期默认与JVM一致，若指定回收ThreadLocal对象或JVM结束后，Threadlocal对象才会被回收。
 *
 * 在一个操作系统中，线程和进程是有数量上限的。在操作系统汇总，确定线程和进程唯一性的唯一条件就是线程或进程ID。
 * 操作系统在回收线程或进程的时候，不是一定杀死线程或进程，在繁忙的时候，只会做清空线程栈数据的操作（而ThreadLocal在堆中为所有线程共用），重复使用线程或进程。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_10_ThreadLocal {

    volatile static String name = "zhangsan";
    static ThreadLocal<String> tl = new ThreadLocal<>();

    /**
     * 测试打印结果：
     * lisi
     * null
     *
     * 结论：
     * 线程1执行晚于线程2，线程2set变量后，线程1拿不到线程2set的变量，原因是它们不是同一个线程。
     * @author Wang si rui
     * @version 1.0
     * @param 参数
     * @return 返回值
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 				1、 <br>
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);//lisi
                System.out.println(tl.get());//null
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                name = "lisi";
                tl.set("wangwu");
            }
        }).start();
    }
}
