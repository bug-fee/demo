package com.wondersgroup.thread.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发容器ConcurrentLinkedQueue 基础链表同步队列
 * 队列 - 链表实现的。先入先出。
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_03_ConcurrentLinkedQueue {
    /**
     * 执行输出结果：
     * [value0, value1, value2, value3, value4, value5, value6, value7, value8, value9]
     * 10
     * value0
     * 10
     * value0
     * 9
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
        Queue<String> queue = new ConcurrentLinkedQueue();
        for (int i = 0; i < 10; i++) {
            queue.offer("value" + i);
        }

        //输出queue所有内容
        System.out.println(queue);
        //输出queue大小
        System.out.println(queue.size());

        //查看第一个元素
        System.out.println(queue.peek());
        //查看queue大小
        System.out.println(queue.size());

        //取出第一个元素（会删除第一个元素）
        System.out.println(queue.poll());
        //查看queue大小
        System.out.println(queue.size());
    }
}
