package com.wonders.demo.thread.synchronizedDemo;

/**
 * synchronised 同步锁测试类
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * 加锁的目的：就是为了保证操作的原子性
 * 锁对象的使用优化(尽量缩小锁的范围)：
 * 1.如果加锁的时候对当前对象的资源访问限定要求不是特别高时，建议锁当前对象内的对象或方法
 * 2.如果加锁的时候对当前对象的资源访问限定要求比较高时，建议锁当前对象。
 * 3.如果加锁的时候锁对象为当前类的实例对象时，不建议在方法上直接加synchronized修饰词，此编码方式过于重量级；
 *   建议在方法内以锁当前对象的方式锁定部分代码块，该方式更加灵活且效率更高。
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class SynchronizedDemo {
    /**
     * 临界对象：是指当使用某个线程访问共享资源时，必须使代码段独享该资源，不允许其他线程返问该资源
     * 临界区：一个程序中单独的或并发的线程对同一个对象进行访问的代码段，称为临界区。在Java语言中，临界区可以是一个语句块或是一个方法，并且用synchronized关键字标识.
     */
    private int count = 0;
    private Object o = new Object();
    private static int staticCount = 0;

    /**
     * 线程同步用例一：
     * 1.锁对象为当前[类实例]的成员对象，如果使用当前类的[同一个实例对象]执行该方法线程同步可生效。
     * 2.如果该变量为静态变量，则该类的所有实例对象在执行该方法时线程同步都将生效。
     */
    public void testSync1() {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "count=" + count++);
        }
    }

    /**
     * 线程同步用例二：
     * 1.锁对象为当前对象,使用当前类的[同一个实例对象]执行该方法时线程同步将生效
     */
    public void testSync2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "count=" + count++);
        }
    }

    /**
     * 线程同步用例三：
     * 1.与用例二相同，锁对象为当前类的实例对象，使用当前类的[同一个实例对象]执行该方法时线程同步可生效。
     * 2.如果该方法为静态方法时，锁对象为当前类，该类的所有实例对象执行该方法时线程同步都将生效。
     */
    public synchronized void testSync3() {
        System.out.println(Thread.currentThread().getName() + "count=" + count++);
    }

    /**
     * 线程同步用例四：
     * 1.锁对象为当前类的class对象，如果使用同一个类的实例对象调用该方法线程同步将生效。
     */
    public static synchronized void testSync4() {
        System.out.println(Thread.currentThread().getName() + "staticCount=" + staticCount++);
    }

    /**
     * 线程同步用例五：
     * 1.锁对象为当前类的class对象，如果使用当前类的实例对象或使用该类直接调用该方法线程同步都将生效。
     */
    public static void testSync5() {
        synchronized (SynchronizedDemo.class) {
            System.out.println(Thread.currentThread().getName() + "staticCount=" + staticCount++);
        }
    }
}
