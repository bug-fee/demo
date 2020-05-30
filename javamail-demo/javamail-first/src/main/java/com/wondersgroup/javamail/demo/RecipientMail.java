package com.wondersgroup.javamail.demo;

import javax.mail.*;
import java.util.Properties;

/**
 * 接收邮件
 * @author Wang si rui
 * @version 1.0
 * @return 返回值
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 				1、 <br>
 */
public class RecipientMail {
    //收件人账户名
    public static String recipientAccount = "779010269@qq.com";
    //收件人账户密码
    public static String recipientPassword = "yihbliozwadfbded";

    public static void main(String[] args) throws Exception {
        //1、连接邮件服务器的参数配置
        Properties props = new Properties();
        //设置传输协议
        props.setProperty("mail.store.protocol", "pop3");
        //设置收件人的POP3服务器
        props.setProperty("mail.pop3.host", "pop.qq.com");
        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);

        Store store = session.getStore("pop3");
        //连接收件人POP3服务器
        store.connect("pop.qq.com", recipientAccount, recipientPassword);
        //获得用户的邮件账户，注意通过pop3协议获取某个邮件夹的名称只能为inbox
        Folder folder = store.getFolder("inbox");
        //设置对邮件账户的访问权限
        folder.open(Folder.READ_WRITE);

        //得到邮件账户的所有邮件信息
        Message[] messages = folder.getMessages();
        for (int i = 0; i < messages.length; i++) {
            //获得邮件主题
            String subject = messages[i].getSubject();
            //获得邮件发件人
            Address[] from = messages[i].getFrom();
            //获取邮件内容（包含邮件内容的html代码）
            String content = (String) messages[i].getContent();
        }

        //关闭邮件夹对象,释放资源
        folder.close(true);
        //关闭连接对象
        store.close();
    }

}