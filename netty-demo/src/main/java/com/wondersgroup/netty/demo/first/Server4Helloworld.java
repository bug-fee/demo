package com.wondersgroup.netty.demo.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.print.DocFlavor;

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
public class Server4Helloworld {
    //监听线程组，监听客户端请求
    private EventLoopGroup acceptorGroup = null;
    //处理客户端相关操作线程组，负责处理与客户端
    private EventLoopGroup clientGroup = null;
    //服务启动相关配置信息
    private ServerBootstrap bootstrap = null;

    public Server4Helloworld() {
        init();
    }

    private void init() {
        //初始化线程组，构建线程组的时候，如果不传递参数，则默认构建的线程组数时CPU核心数量。
//        acceptorGroup = new NioEventLoopGroup(1);
        //监听任务线程组
        acceptorGroup = new NioEventLoopGroup();
        //处理任务线程组
        clientGroup = new NioEventLoopGroup();
        //初始化服务的配置
        bootstrap = new ServerBootstrap();
        //绑定线程组
        //若传入的线程组为同一个，而且构建线程组时指定的参数为1，则出来的线程模型为单线程模型（监听线程和处理任务线程为同一个线程）。
        //若传入的参数是两个不同的线程组。负责监听的acceptor线程组，线程数为1，也就是构造参数为1。负责处理客户端任务的线程组线程数大于1，也就是构造参数大于1。这种开发方式，就是多线程模型。
//        bootstrap.group(acceptorGroup, acceptorGroup);
        bootstrap.group(acceptorGroup, clientGroup);
        //设定通讯模式为NIO，同步非阻塞
        bootstrap.channel(NioServerSocketChannel.class);
        //设定缓冲区大小,缓冲区的单位是字节。
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // SO_SNDBUF发送缓冲区，SO_RCVBUF接受缓冲区，SO_KEEPALIVE开启心跳检测（保证连接有效）
        bootstrap.option(ChannelOption.SO_SNDBUF,16*1024)
                .option(ChannelOption.SO_RCVBUF,16*1024)
                .option(ChannelOption.SO_KEEPALIVE,true);

    }

    /**
     * 监听处理逻辑。
     *
     * @param port             监听端口
     * @param acceptorHandlers 处理器，如何处理客户端请求。
     * @return
     */
    public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException {
        /*
         * childHandler是服务的Bootstrap独有的方法。是用于提供处理对象的。
         * 可以一次性增加若干个处理逻辑。是类似责任链模式的处理方式。
         * 增加A，B 两个处理逻辑，在处理客户端请求数据的时候，根据A->B 顺序依次处理。
         *
         * ChannelInitializer - 用于提供处理器的一个模型对象。
         * 其中定义了 一个方法，initChannel方法。
         * 方法用于初始化处理逻辑责任链条的。
         * 可以保证服务端的BootStrap只初始化一次处理器，尽量提供处理逻辑的重用。
         * 避免反复的创建处理器对象。节约资源开销。
         *
         */
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(acceptorHandlers);
            }
        });
        // bind方法 - 绑定监听端口的。ServerBootStrap可以绑定多个监听端口，多次调用bind方法即可。
        // sync - 开始启动监听逻辑。返回一个ChannelFuture。返回结果代表的是监听成功后的一个对应的未来结果
        // 可以使用ChannelFuture实现后续的服务器和客户端交互
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }

    /**
     * shutdownGraceFully - 方法是一个安全关闭的方法。可以保证不放弃任何一个已接收的客户端请求。
     * 安全关闭：等请求处理完毕后自动关闭。
     */
    public void release() {
        this.acceptorGroup.shutdownGracefully();
        this.clientGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        ChannelFuture futre = null;
        Server4Helloworld server = null;
        try {
            server = new Server4Helloworld();
            futre = server.doAccept(9999, new Server4HelloWorldHandler());
            System.out.println("server started.");

            //关闭连接的。
            futre.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (null != futre) {
                try {
                    futre.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (null != server) {
                server.release();
            }
        }
    }
}
