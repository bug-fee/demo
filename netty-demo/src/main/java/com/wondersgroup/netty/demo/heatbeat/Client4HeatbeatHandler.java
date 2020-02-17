package com.wondersgroup.netty.demo.heatbeat;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.wondersgroup.netty.demo.Utils.HeatbeatMessage;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class Client4HeatbeatHandler extends ChannelHandlerAdapter {
    //计划任务的线程池,以定时方式向服务器传输计算机硬件使用信息。
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture heatbeat;
    private InetAddress remoteAddr;
    private static final String HEATBEAT_SUCCESS = "SERVER_RETURN_HEATBEAT_SUCCESS";

    /**
     * 若服务端要实现强制校验，可在handler中增加isGranted变量（handler不能使用@sharable），
     * 通道建立时标志为false，接受客户端认证信息后调整为true。若客户端不认证直接发送消息判断是否授权成功，若未授权则关闭通道。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 获取本地INET信息
        this.remoteAddr = InetAddress.getLocalHost();
        // 获取本地计算机名：从JVM环境变量中获取
        String computerName = System.getenv().get("COMPUTERNAME");
        String credentials = this.remoteAddr.getHostAddress() + "_" + computerName;
        System.out.println(credentials);
        // 发送到服务器，作为信息比对证书
        ctx.writeAndFlush(credentials);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof String) {
                if (HEATBEAT_SUCCESS.equals(msg)) {
                    this.heatbeat = this.executorService.scheduleWithFixedDelay(new HeatbeatTask(ctx), 0L, 2L, TimeUnit.SECONDS);
                    System.out.println("client receive - " + msg);
                } else {
                    System.out.println("client receive - " + msg);
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught method run...");
        // cause.printStackTrace();
        // 回收资源
        if (this.heatbeat != null) {
            this.heatbeat.cancel(true);
            this.heatbeat = null;
        }
        ctx.close();
    }

    class HeatbeatTask implements Runnable {
        private ChannelHandlerContext ctx;

        public HeatbeatTask() {

        }

        public HeatbeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        public void run() {
            try {
                HeatbeatMessage msg = new HeatbeatMessage();
                msg.setIp(remoteAddr.getHostAddress());
                Sigar sigar = new Sigar();
                // CPU信息
                CpuPerc cpuPerc = sigar.getCpuPerc();
                Map<String, Object> cpuMsgMap = new HashMap<String, Object>();
                cpuMsgMap.put("Combined", cpuPerc.getCombined());
                cpuMsgMap.put("User", cpuPerc.getUser());
                cpuMsgMap.put("Sys", cpuPerc.getSys());
                cpuMsgMap.put("Wait", cpuPerc.getWait());
                cpuMsgMap.put("Idle", cpuPerc.getIdle());

                // 内存信息
                Map<String, Object> memMsgMap = new HashMap<String, Object>();
                Mem mem = sigar.getMem();
                memMsgMap.put("Total", mem.getTotal());
                memMsgMap.put("Used", mem.getUsed());
                memMsgMap.put("Free", mem.getFree());

                // 文件系统
                Map<String, Object> fileSysMsgMap = new HashMap<String, Object>();
                FileSystem[] list = sigar.getFileSystemList();
                fileSysMsgMap.put("FileSysCount", list.length);
                List<String> msgList = null;
                for (FileSystem fs : list) {
                    msgList = new ArrayList<String>();
                    msgList.add(fs.getDevName() + "总大小:    " + sigar.getFileSystemUsage(fs.getDirName()).getTotal() + "KB");
                    msgList.add(fs.getDevName() + "剩余大小:    " + sigar.getFileSystemUsage(fs.getDirName()).getFree() + "KB");
                    fileSysMsgMap.put(fs.getDevName(), msgList);
                }

                msg.setCpuMsgMap(cpuMsgMap);
                msg.setMemMsgMap(memMsgMap);
                msg.setFileSysMsgMap(fileSysMsgMap);

                ctx.writeAndFlush(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}