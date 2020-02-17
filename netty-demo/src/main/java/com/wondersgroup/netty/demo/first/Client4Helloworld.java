package com.wondersgroup.netty.demo.first;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 因为客户端是请求的发起者，不需要监听。
 * 只需要定义唯一的一个线程组即可
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Client4Helloworld {

    //处理请求和处理服务端响应的线程组
    private EventLoopGroup group = null;
    //客户端启动相关配置信息
    private Bootstrap bootstrap = null;

    public Client4Helloworld() {
        init();
    }

    private void init() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        //绑定线程组
        bootstrap.group(group);
        //设定通讯模式为NIO
        bootstrap.channel(NioSocketChannel.class);
    }

    public ChannelFuture doRequest(String host, int port, final ChannelHandler... handlers) throws InterruptedException {
        /**
         * 客户端的BootStrap没有childHandler方法。只有handler方法。
         * 方法含义等同ServerBoostrap中的childHandler
         * 在客户端必须绑定处理器，也就是必须调用handler方法。
         * 服务器必须绑定处理器，必须调用childHandler方法。
         */
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(handlers);
            }
        });
        //建立连接。
        ChannelFuture future = this.bootstrap.connect(host, port).sync();
        return future;
    }

    public void release() {
        this.group.shutdownGracefully();
    }

    public static void main(String[] args) {
        Client4Helloworld client = null;
        ChannelFuture future = null;
        try {
            client = new Client4Helloworld();
            future = client.doRequest("127.0.0.1", 9999, new Client4HelloWroldHandler());

            Scanner s = null;
            while (true) {
                s = new Scanner(System.in);
                System.out.println("enter message send to server (enter 'exit' for close client) > ");
                String line = s.nextLine();
                if ("exit".equals(line)) {
                    // addListener - 增加监听，当某条件满足的时候，触发监听器。
                    // ChannelFutureListener.CLOSE - 关闭监听器，代表ChannelFuture执行返回候关闭连接。
                    future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")))
                            .addListener(ChannelFutureListener.CLOSE);
                    break;
                }
                //Unpooled是一个工具类，专门用来做buffer的转换的，copiedBuffer方法返回ByteBuf类型
                future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != future) {
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
