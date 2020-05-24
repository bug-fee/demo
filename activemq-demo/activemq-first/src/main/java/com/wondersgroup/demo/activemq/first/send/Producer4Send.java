package com.wondersgroup.demo.activemq.first.send;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

/**
 * 1.使用指定目的地发送消息，接收消息。
 * 2.设置消息有效时长
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Producer4Send {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
    private Message message = null;

    /**
     * 直接发送消息到默认目的地，默认目的地是test-send
     *
     * @param obj
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void sendMessage(Serializable obj) {
        try {
            this.init("test-send");

            message = session.createObjectMessage(obj);
            producer.send(message);
            System.out.println("sendMessage method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定目的地发送消息
     *
     * @param obj             消息内容
     * @param destinationName 目的地
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void sendMessage4Destination(Serializable obj, String destinationName) {
        try {
            this.init();
            message = session.createObjectMessage(obj);
            //创建临时目的地
            Destination destination = session.createQueue(destinationName);
            producer.send(destination, message);
            System.out.println("sendeMessage4Destination method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多参数发送消息
     * <pre>
     * void send(Message message ,int deliveryMode,int priority,long timeToLive)
     *  deliveryMode - 持久化模式。
     *     DeliveryMode.PERSISTENT - 持久化（kahadb,jdbc等）
     *     DelivertyMode.NON_PERSISTENT - 不持久化（消息只保存在内存中）
     *  priority - 优先级，0~9取值范围，取值越大优先级越高.不能保证绝对顺序。
     *     必须在activemq.xml配置文件的broker标签中增加配置
     *  timeToLive - 消息有效期。单位毫秒。有效期超时，消息自动放弃。</pre>
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void sendMessageWithParameters(Serializable obj, int deliveryMode, int priority, long timeToLive) {
        try {
            this.init("test-send-params");
            message = session.createObjectMessage(obj);
            producer.send(message, deliveryMode, priority, timeToLive);
            System.out.println("sendMessageWithParameters method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void init() throws InitJMSException {
        this.init(null);
    }

    private void init(String destination) throws InitJMSException {
        this.init("admin", "admin", "tcp://192.168.42.234:61616", destination);
    }

    private void init(String userName, String password, String brokerURL, String destinationName) throws InitJMSException {
        try {
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if (null != destinationName) {
                destination = session.createQueue(destinationName);
                producer = session.createProducer(destination);
            } else {
                producer = session.createProducer(null);
            }
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
            throw new InitJMSException(e);
        }
    }

    private void release() {
        if (producer != null) {
            try {
                producer.close();

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

    public static void main(String[] args) {
        Producer4Send producer = null;
        try {
            producer = new Producer4Send();
            //发送到默认目的地
//            producer.sendMessage("send message");

            //发送到指定目的地
//            producer.sendMessage4Destination("send message for destination", "test-send-des");
            //设置消息时长
            //若DeliveryMode设置为NON_PERSISTENT则，消息过期后不存入死信队列，也不存入数据库。
            producer.sendMessageWithParameters("send message for parameters", DeliveryMode.NON_PERSISTENT, 0, 1000 * 10);
            producer.release();
        } catch (InitJMSException e) {
            e.printStackTrace();
        }
    }

}
