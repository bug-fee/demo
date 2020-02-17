package com.wondersgroup.netty.demo.aio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
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
public class AIOServerHandler implements java.nio.channels.CompletionHandler<AsynchronousSocketChannel, AIOServer> {

    /**
     * 业务处理逻辑,当请求到来后，检大厅成功，应该做什么。
     * 一定要实现的逻辑：为下一次客户端请求开启监听。accept方法调用。
     * result参数：就是和客户端直接建立关联的通道。
     * 无论BIO、NIO、AIO中，一旦简历连接，两端是平等的。建立双向通道后两端平等关系。
     * result中有通道中的所有相关数据。如：OS操作系统准备好的读取数据缓存，或等待返回数据的缓存。
     *
     * @param result
     * @param attachment
     */
    @Override
    public void completed(AsynchronousSocketChannel result, AIOServer attachment) {
        // 处理下一次的客户端请求。类似递归逻辑
        attachment.getServerChannel().accept(attachment, this);
        doRead(result);
    }

    /**
     * 异常处理逻辑，当服务端代码出现异常时，做什么事情。
     *
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        exc.printStackTrace();
    }


    private void doRead(final AsynchronousSocketChannel channel) {
        //字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        /*
         * 异步读操作，read(Buffer destination,A attachment,CompletionHandler<Integer,? super A> handler)
         * destination - 目的地，是处理客户端传递数据的中转缓存。可以不使用。
         * attachment（客户端传递过来的数据，用什么处理） - 处理客户端传递数据的对象。通常使用Buffer处理。
         * handler - 处理逻辑
         */
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            /**
             * 业务逻辑，读取客户端传输数据
             * result - 数据的长度
             * attachement - 在completed方法执行的时候，OS已经将客户端请求的数据写入到Buffer中了
             *   但是未复位（flip）。使用前一定要复位。
             */
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                try {
                    System.out.println(attachment.capacity());
                    attachment.flip();
                    System.out.println("from client : " + new String(attachment.array(), "UTF-8"));
                    doWrite(channel);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    /**
     * 真实项目中，服务器返回的结果应该是根据客户端的请求数据计算得到的。不是等待控制台输入的。
     * @param result
     */
    private void doWrite(AsynchronousSocketChannel result) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.println("enter message send to client > ");
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            buffer.put(line.getBytes("UTF-8"));
            //重点：必须复位，必须复位，必须复位
            buffer.flip();
            //write方法时一个异步操作。具体实现由OS实现。可以增加get方法，实现阻塞，等待OS的写操作结束。
            result.write(buffer);
//            result.write(buffer).get();//调用get代表服务端线程阻塞，等待写操作完成
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

}
