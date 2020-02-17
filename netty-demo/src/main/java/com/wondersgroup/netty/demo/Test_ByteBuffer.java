package com.wondersgroup.netty.demo;

import java.nio.ByteBuffer;

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
public class Test_ByteBuffer {

    /**
     * 写入数据之前：java.nio.HeapByteBuffer[pos=0 lim=8 cap=8]
     * 写入数据之后：java.nio.HeapByteBuffer[pos=3 lim=8 cap=8]
     * 重置游标之后：java.nio.HeapByteBuffer[pos=0 lim=3 cap=8]
     * 0 - 3
     * 1 - 2
     * 2 - 1
     *
     *
     * Buffer的应用固体逻辑
     * 写操作顺序
     * 1.clear()
     * 2.put() -> 写操作
     * 3.flip -> 重置游标
     * 4.SocketChannel.write(buffer); -> 将缓存数据发送到网络的另一端
     * 5.clear
     *
     * 读操作顺序
     * 1.clear()
     * 2.SocketChannel.read(buffer); -> 从网络中读取数据
     * 3.buffer.flip -> 重置游标
     * 4.buffer.get() -> 读取数据
     * 5.buffer.clear()
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
        //定义ByteBuffer的容量为8个Byte
        ByteBuffer buffer = ByteBuffer.allocate(8);
        byte[] temp = new byte[]{3, 2, 1};
        //写入数据之前：java.nio.HeapByteBuffer[pos=0 lim=8 cap=8]
        // pos - 游标位置，lim - 限制数量， cap - 最大容量
        System.out.println("写入数据之前：" + buffer);

        buffer.put(temp);

        //写入数据之后：java.nio.HeapByteBuffer[pos=3 lim=8 cap=8]
        //游标为3，限制位8，容量为8
        System.out.println("写入数据之后：" + buffer);

        //重置游标
        //flip()的过程就是将pos的值赋值为lim，若连续重置两次游标（第二次将0赋值给了lim），然后再get数据，将会报BufferUnderflowException（没有有效数据）
        //两次flip后lim为0，限制为0 ，则不允许写入数据了，调用clear可重置ByteBuffer
        buffer.flip();

        //清空缓存，pos=0 ,lim=cap ,cap=cap
        //读之前flip,写之前clear+flip
//        buffer.clear();

        //重置游标之后：java.nio.HeapByteBuffer[pos=0 lim=3 cap=8]
        //游标为0，限制位3，容量为8
        /*
         * 若不重置游标则输出：
         * 0 - 3
         * 1 - 2
         * 2 - 1
         * 3 - 0
         * 4 - 0
         * 原因是buffer.remaining（）方法获取的是limit-pos，所以最多循环到下标4
         */
        System.out.println("重置游标之后：" + buffer);

        //获取当前游标指向位置的数据。
//        System.out.println(buffer.get());

        for (int i = 0; i < buffer.remaining(); i++) {
            //获取指定位置的数据。
            int data = buffer.get(i);
            System.out.println(i + " - " + data);
        }
    }
}
