package com.wondersgroup.netty.demo.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

/**
 * @Sharable注解
 * 代表当前Handler是一个可以分享的处理器。也意味着，服务器注册此Handler后，可以分享给多个客户端同时使用。
 * 如果不使用注解描述类型，则每次客户端请求时，必须为客户端重新创建一个新的Handler对象（应用场景在新增业务逻辑，不可重复使用）。
 * 如果handler是一个Sharable的，一定避免定义可写的实例变量（例如：成员变量）。
 *         bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
 *             @Override
 *             protected void initChannel(SocketChannel ch) throws Exception {
 *                 ch.pipeline().addLast(new XXXHandler());
 *             }
 *         });
 *
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
@ChannelHandler.Sharable
public class Server4HelloWorldHandler extends ChannelHandlerAdapter {

    /**
     * 用于处理逻辑
     * 用于处理读取数据请求的逻辑
     * ctx - 上下文对象。起哄包含与客户端建立连接的所有资源。如：对应的Channel
     * msg - 读取到的数据。默认类型是ByteBuf。是Netty自定义的。是对ByteBuffer的封装。不需要考虑复位问题。
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 获取读取的数据，是一个缓冲
            ByteBuf readBuffer = (ByteBuf) msg;
            //创建一个字节数组，用于保存缓存中的数据。
            byte[] tempDatas = new byte[readBuffer.readableBytes()];
            //将缓冲中的数据读取到字节数组中。
            readBuffer.readBytes(tempDatas);
            String message = new String(tempDatas, StandardCharsets.UTF_8);
            System.out.println(" from client ：" + message);
            if ("exit".equals(message)) {
                ctx.close();
                return;
            }
            String line = "server message to client!";
            //写操作自动释放缓存，避免内存溢出问题。
            ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
            //如果调用的是write方法。不会刷新缓存，缓存中的数据不会发送到客户端，必须崽子调用flush方法才行。
//            ctx.write(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
//            ctx.flush();
        }finally {
            //用于释放缓存，防止内存溢出
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 异常处理逻辑，当客户端异常退出的时候，也会运行。
     * ChannelHandlerContext关闭，也代表当前与客户端连接的资源关闭。
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught method run .....");
        ctx.close();
    }
}
