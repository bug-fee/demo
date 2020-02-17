package com.wondersgroup.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

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
public class NIOClient {

    public static void main(String[] args) {
        //远程地址创建
        InetSocketAddress remote = new InetSocketAddress("localhost", 9999);
        SocketChannel channel = null;

        //定义缓存。
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            //开启通道
            channel = SocketChannel.open();
            //连接远程服务器
            channel.connect(remote);
            Scanner reader = new Scanner(System.in);
            while (true) {
                System.out.println("put message for send to server > ");
                String line = reader.nextLine();
                if (line.equals("exit")) {
                    break;
                }
                //将控制台输入的数据写入到缓存
                buffer.put(line.getBytes("UTF-8"));
                //重置缓存游标
                buffer.flip();
                //将数据发送给服务器
                channel.write(buffer);
                //清空缓存数据
                buffer.clear();

                //读取服务器返回的数据
                //当设置serverChannel.configureBlocking(false)时改方法将不再是阻塞方法，读取不到服务端返回的数据。
                int readLength = channel.read(buffer);
                if (readLength == -1) {
                    break;
                }
                //重置缓存游标
                buffer.flip();
                byte[] datas = new byte[buffer.remaining()];
                //读取数据到字节数组
                buffer.get(datas);
                System.out.println("from server : " + new String(datas, "UTF-8"));
                //清空缓存
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != channel) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
