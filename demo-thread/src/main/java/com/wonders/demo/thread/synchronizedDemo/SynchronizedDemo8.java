package com.wonders.demo.thread.synchronizedDemo;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 常量问题：<br/>
 * 在定义同步代码块时，尽量不要使用常量对象作为锁对象
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo8 {
    String s1 = "1";
    String s2 = "1";
    //String.valueOf()会创建新的String对象不会从常量池取值
    //new关键字一定会从堆中创建一个新的对象
//    String s2 = String.valueOf(1);

    public void m1() {
        synchronized (s1) {
            System.out.println("m1 start");
            while (true) {

            }
        }
    }

    public void m2() {
        synchronized (s2) {
            System.out.println("m2 start");
            while (true) {

            }
        }
    }

    /**
     * 每一次new的字符串都存在于常量池中，如果被锁的对象不是new出来的新字符串，则可以实现锁同步效果
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
        final SynchronizedDemo8 synchronizedDemo8 = new SynchronizedDemo8();
        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo8.m1();
            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo8.m2();
            }
        }).start();


    }
}
