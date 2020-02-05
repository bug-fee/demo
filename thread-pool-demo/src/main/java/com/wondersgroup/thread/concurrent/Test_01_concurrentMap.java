package com.wondersgroup.thread.concurrent;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_01_concurrentMap {
    /**
     * 执行结果：
     * hashTable 执行时间为：501毫秒！
     * ConcurrentHashMap 执行时间为：290毫秒！
     * ConcurrentSkipListMap 执行时间为：432毫秒！
     *
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
        //map jdk1.5版本之前的线程安全对象
//        final Map<String, String> map = new Hashtable<>();
//        final Map<String, String> map = new ConcurrentHashMap<>();
        final Map<String, String> map = new ConcurrentSkipListMap<>();
        final Random r = new Random();
        Thread[] array = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(array.length);

        long begin = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        map.put("key" + r.nextInt(100000), "value" + r.nextInt());
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

    }

}
