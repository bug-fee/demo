package com.wondersgroup.thread.concurrent;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * CopyOnWriteList线程安全的list集合（写慢读快）
 * 写时复制集合。吸入效率低，读取效率高。每次写入数据都会创建一个新的底层数组。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_02_CopyOnWriteList {


    /**
     * 执行结果：
     * Vector 执行时间为：44毫秒！
     * CopyOnWriteArrayList 执行时间为：4572毫秒！
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
        //线程不安全，进行写操作时可能报空指针
//        List<String> list = new ArrayList<>();
        //线程安全list，效率高于CopyOnWriteArrayList
        final List<String> list = new Vector<>();
        //CopyOnWriteArrayList 特点：写慢（极其慢）读快、删除尾巴比较快。底层原理使用数组拷贝方式实现线程安全。适用于写得少读的多。
//        final List<String> list = new CopyOnWriteArrayList<>();
        final Random r = new Random();
        Thread[] array = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(array.length);

        long begin = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        list.add("value" + r.nextInt());
                    }
                    latch.countDown();
                }
            });
        }
        for (Thread t : array) {
            t.start();
        }
        try {
            //等待门栓开放，所有锁去掉后执行剩余代码
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间为：" + (end - begin) + "毫秒！");
        System.out.println(list.size());

    }
}
