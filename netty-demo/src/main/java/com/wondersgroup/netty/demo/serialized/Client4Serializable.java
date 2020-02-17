package com.wondersgroup.netty.demo.serialized;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import com.wondersgroup.netty.demo.Utils.GzipUtils;
import com.wondersgroup.netty.demo.Utils.RequestMessage;
import com.wondersgroup.netty.demo.Utils.SerializableFactory4Marshalling;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client4Serializable {

    // 处理请求和处理服务端响应的线程组
    private EventLoopGroup group = null;
    // 服务启动相关配置信息
    private Bootstrap bootstrap = null;

    public Client4Serializable() {
        init();
    }

    private void init() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 绑定线程组
        bootstrap.group(group);
        // 设定通讯模式为NIO
        bootstrap.channel(NioSocketChannel.class);
    }

    public ChannelFuture doRequest(String host, int port, final ChannelHandler... handlers) throws InterruptedException {
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(SerializableFactory4Marshalling.buildMarshallingDecoder());
                ch.pipeline().addLast(SerializableFactory4Marshalling.buildMarshallingEncoder());
                ch.pipeline().addLast(handlers);
            }
        });
        ChannelFuture future = this.bootstrap.connect(host, port).sync();
        return future;
    }

    public void release() {
        this.group.shutdownGracefully();
    }

    public static void main(String[] args) {
        Client4Serializable client = null;
        ChannelFuture future = null;
        try {
            client = new Client4Serializable();
            future = client.doRequest("localhost", 9999, new Client4SerialiableHandler());
            String attachment = "test attachment";
            //传送大对象时使用Gzip进行压缩
            byte[] attBuf = attachment.getBytes();
            attBuf = GzipUtils.zip(attBuf);
            RequestMessage msg = new RequestMessage(new Random().nextLong(),
                    "test", attBuf);
            future.channel().writeAndFlush(msg);
            TimeUnit.SECONDS.sleep(1);
            //处理完后自动关闭通道
            future.addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != future) {
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null != client) {
                client.release();
            }
        }
    }

}