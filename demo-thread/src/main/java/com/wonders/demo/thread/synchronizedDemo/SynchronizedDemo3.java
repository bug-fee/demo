package com.wonders.demo.thread.synchronizedDemo;

/**
 * Synchronized 脏读现象
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 * 同步方法 - 多方法调用原子性问题（业务）<br/>
 * 同步方法只能保证当前方法的原子性，不能保证多个业务方法之间的互相访问的原子性。<br/>
 * 注意：在商业开发中，多方法要求结果访问原子性操作，需要多个方法都加锁，且锁定同一个资源。<br/>
 * <p>
 * 一般来说，商业项目中，不考虑业务逻辑上的脏读问题，只考虑数据层面的脏读。<br/>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo3 {

    private double d = 0.0;

    public synchronized void m1(double d) {
        try {
            //复杂的业务逻辑代码
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.d = d;
    }

    public double m2() {
        return this.d;
    }

    /**
     * 测试脏读现象:<br/>
     * 当第一个线程还未执行完时，第二个线程已经读取第一个线程要修改的资源
     *
     * @param args
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        final SynchronizedDemo3 synchronizedDemo3 = new SynchronizedDemo3();
        //1.线程正在执行中
        new Thread(new Runnable() {
            public void run() {
                synchronizedDemo3.m1(100);
            }
        }).start();
        //2.第一个线程还未执行完毕，此处已经读取了临界资源
        System.out.println(synchronizedDemo3.m2());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(synchronizedDemo3.m2());

    }
}
