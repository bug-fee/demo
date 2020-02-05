package com.wondersgroup.thread.pool;

import java.util.concurrent.*;

/**
 * 线程池
 * 带返回值的线程池
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_03_Future {

    /**
     * 带返回值的线程池执行示例
     * @param 参数
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        //Callable的构造器约束为一个泛型，约定了Future对象返回参数的类型为String,也可以是其他。
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaa");
                return Thread.currentThread().getName() + " - test executor";
            }
        });
        System.out.println(future);
        //查看线程是否执行结束，call方法是否执行结束
        System.out.println(future.isDone());

        //获取call方法的返回值，该方法为阻塞方法，待线程执行完毕后返回
        System.out.println(future.get());
        System.out.println(future.isDone());

    }
}
