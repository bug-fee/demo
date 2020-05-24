package com.wondersgroup.demo.activemq.first.send;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 使用监听器的方式，实现消息的处理【消费】
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Consumer4Send {
    /**
     * 处理消息
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void consumMessage() {

        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;

        try {
            factory = new ActiveMQConnectionFactory("admin", "admin",
                    "tcp://192.168.42.234:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            destination = session.createQueue("test-send");
//            destination = session.createQueue("test-send-des");
            destination = session.createQueue("test-send-params");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                /**
                 * 监听器一旦注册，永久有效。
                 * 永久- consumer线程不关闭。
                 * 处理消息的方式：只要有消息未处理，自动调用onMessage方法，处理消息。
                 * 监听器可以注册若干。注册多个监听器，相当月集群。
                 * ActiveMQ自动的循环调用多个监听器，处理队列中的消息。实现并行处理。
                 *
                 * 处理消息的方法。就是监听方法。
                 * 监听的事件是：消息，消息未处理。
                 * @Param message -未处理的消息。
                 * @author Wang si rui
                 * @version 1.0
                 * @return 返回值
                 * @lastAuthor 最后修改人
                 * @lastDate最后时间
                 * @Rewrite record 修改记录：
                 * 				1、 <br>
                 */
                public void onMessage(Message message) {
                    try {
                        ObjectMessage om = (ObjectMessage) message;
                        Object data = om.getObject();
                        System.out.println(data);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {
                try {
                    consumer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Consumer4Send listener = new Consumer4Send();
        listener.consumMessage();
    }
}
