package com.wondersgroup.netty.demo.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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
public class AIOClient {
    private AsynchronousSocketChannel channel;

    public AIOClient(String host, int port) {
        init(host, port);
    }

    private void init(String host, int port) {
        try {
            // 开启通道
            channel = AsynchronousSocketChannel.open();
            // 发起请求，建立连接。
            channel.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String line) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(line.getBytes("UTF-8"));
            buffer.flip();
            channel.write(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //read方法时异步方法，OS实现。get方法时一个阻塞方法，会等待OS处理结束再返回。
            //真实应用场景中不会调用get方法，而是等待客户端手动调用完成，或服务端传输结束后调用get。
            channel.read(buffer).get();
            buffer.flip();
            System.out.println("from server : " + new String(buffer.array(), "UTF-8"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void doDestory() {
        if (null != channel) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        AIOClient client = new AIOClient("localhost", 9999);
        try{
            System.out.println("enter message send to server > ");
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            client.write(line);
            client.read();
        }finally {
            client.doDestory();
        }
    }


}
