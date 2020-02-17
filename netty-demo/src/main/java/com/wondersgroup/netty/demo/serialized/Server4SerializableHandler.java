package com.wondersgroup.netty.demo.serialized;

import com.wondersgroup.netty.demo.Utils.GzipUtils;
import com.wondersgroup.netty.demo.Utils.RequestMessage;
import com.wondersgroup.netty.demo.Utils.ResponseMessage;
import io.netty.channel.ChannelHandler.Sharable;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class Server4SerializableHandler extends ChannelHandlerAdapter {

    // 业务处理逻辑
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("from client : ClassName - " + msg.getClass().getName()
                + " ; message : " + msg.toString());
        if (msg instanceof RequestMessage) {
            RequestMessage request = (RequestMessage) msg;
            byte[] attachment = GzipUtils.unzip(request.getAttachment());
            System.out.println(new String(attachment));
        }
        ResponseMessage response = new ResponseMessage(0L, "test response");
        ctx.channel().writeAndFlush(response);
    }

    // 异常处理逻辑
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught method run...");
        cause.printStackTrace();
        ctx.close();
    }

}