package com.wondersgroup;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

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
public class Test_jvmProperties {

    /**
     * demo1
     * 默认情况下执行输出结果：
     * PS Scavenge
     * PS MarkSweep
     *
     * demo2
     * 设置虚拟机参数-XX:+UseSerialGC(新生代收集器-串行收集器) 时输出结果：
     * Copy
     * MarkSweepCompact
     *
     * demo3
     * 设置虚拟机参数-XX:+UseG1GC(G1垃圾回收器-) 时输出结果：
     * G1 Young Generation
     * G1 Old Generation
     *
     * Scavenge：新生代回收器
     * MarkSweep：老年代回收期
     */
    public static void main(String[] args) {
        //获取虚拟机垃圾回收器的信息
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }

    }
}
