package com.wondersgroup.demo.activemq.first.priority;

import com.wondersgroup.demo.activemq.first.send.InitJMSException;
import com.wondersgroup.demo.activemq.first.send.Producer4Send;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.List;

/**
 * 测试消息优先级
 * 需要在activemq中开启消息优先策略配置
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class PriorityProducer {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
    private Message message = null;

    /**
     * 指定目的地发送消息
     *
     * @param destinationName 目的地
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public void sendMessage4Destination(String destinationName) {
        try {
            this.init(destinationName);
            producer.send(session.createObjectMessage("2"), DeliveryMode.PERSISTENT, 2, 0);
            producer.send(session.createObjectMessage("9"), DeliveryMode.PERSISTENT, 9, 0);
            producer.send(session.createObjectMessage("5"), DeliveryMode.PERSISTENT, 5, 0);
            producer.send(session.createObjectMessage("7"), DeliveryMode.PERSISTENT, 7, 0);
            session.commit();
            System.out.println("sendeMessage4Destination method run");
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
            //开启事务
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
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
        PriorityProducer producer = null;
        try {
            producer = new PriorityProducer();
            producer.sendMessage4Destination("test-send-params");
            producer.release();
        } catch (InitJMSException e) {
            e.printStackTrace();
        }
    }

}
