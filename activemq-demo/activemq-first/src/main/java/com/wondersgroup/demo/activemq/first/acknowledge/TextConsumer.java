package com.wondersgroup.demo.activemq.first.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TextConsumer {

    public String receiveTextMessage() {
        String resultCode = "";
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        //消息的消费者，用于接收消息的对象
        MessageConsumer consumer = null;
        TextMessage message = null;

        try {
//            factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.42.234:61616");
//            主从模式使用如下，方式进行连接（错误自动转移）
            factory = new ActiveMQConnectionFactory("admin", "admin", "failover:(tcp://192.168.42.234:61616,tcp://192.168.42.252:61616,tcp://192.168.42.235:61616)?randomize=false");
            connection = factory.createConnection();
            //消息的消费者必须启动连接，否支无法处理消息
            connection.start();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            destination = session.createQueue("first-mq");
            //创建消息消费者对象，在制定的目的地中获取消息
            consumer = session.createConsumer(destination);
            //获取队列中的消息,receive方法是一个主动获取消息的方法。执行一次，拉取一个消息。商业模式下较少用。
            message = (TextMessage) consumer.receive();

            //确认消息，发送处理消息确认消息。通知MQ删除对应的消息。
            message.acknowledge();

            //处理文本消息
            resultCode = message.getText();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {//回收消息消费者
                try {
                    consumer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {//回收会话对象
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {//回收连接对象
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultCode;
    }


    public static void main(String[] args) {
        TextConsumer consumer = new TextConsumer();
        String messageString = consumer.receiveTextMessage();
        System.out.print("接收的消息内容是：" + messageString);
    }

}