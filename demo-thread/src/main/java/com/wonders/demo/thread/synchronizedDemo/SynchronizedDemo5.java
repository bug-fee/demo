package com.wonders.demo.thread.synchronizedDemo;

/**
 * synchronized
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 同步方法 - 继承
 * 子类同步方法覆盖父类同步方法。可以指定调用父类的同步方法。
 * 相当于锁的重入
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo5 {
    public synchronized void m() {
        System.out.println("Super class m start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Super class m end");
    }


    public static void main(String[] args) {
        new MyTestThread().m();
    }
}

class MyTestThread extends SynchronizedDemo5 {

    public synchronized void m() {
        System.out.println("sub class m start");
        super.m();
        System.out.println("sub class m end");
    }
}
