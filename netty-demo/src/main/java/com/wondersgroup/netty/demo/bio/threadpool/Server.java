package com.wondersgroup.netty.demo.bio.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class Server {
    public static void main(String[] args) {
        int port = genPort(args);
        ServerSocket server = null;
        //使用线程池处理客户端请求。
        ExecutorService service = Executors.newFixedThreadPool(50);
        try {
            server = new ServerSocket(port);
            System.out.println("server started");
            while (true) {
                Socket socket = server.accept();
                service.execute(new Handler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Handler implements Runnable {
        Socket socket = null;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                String readMessage = null;
                while (true) {
                    System.out.println("server reading");

                    if ((readMessage = reader.readLine()) == null) {
                        break;
                    }
                    System.out.println(readMessage);
                    //writer对象必须调用println方法才能结束一行的写入，否则flush方法一直处于阻塞状态，无法刷新缓存到流中。
                    writer.println("server recive:" + readMessage);
                    writer.flush();

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket = null;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    reader = null;
                    if (writer != null) {
                        writer.close();
                    }
                    writer = null;
                }

            }
        }
    }


    /**
     * 获取端口号，传入虚拟机参数时若传参参数错误则默认使用9999作为端口
     *
     * @param args
     * @return
     */
    public static int genPort(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                return 9999;
            }
        } else {
            return 9999;
        }
    }
}
