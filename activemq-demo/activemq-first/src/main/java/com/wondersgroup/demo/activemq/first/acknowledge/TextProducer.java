package com.wondersgroup.demo.activemq.first.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


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
public class TextProducer {

    /**
     * 发送消息到ActiveMQ中，具体的消息内容为参数信息
     * 开发JMS相关代码中，使用的接口类型都是javax.jms包下的类型
     *
     * @param datas 消息内容
     */
    public void sendTextMessage(String datas) {
        //连接工厂
        ConnectionFactory factory = null;
        //连接
        Connection connection = null;
        //目的地
        Destination destination = null;
        //会话
        Session session = null;
        //消息发送者
        MessageProducer producer = null;
        //消息对象
        Message message = null;

        try {
            //创建连接工厂，连接ActiveMQ服务的连接工厂
            //创建工厂，构造方法有三个参数，分别是用户名，密码，连接地址
            //无参构造，有默认的连接地址。本地连接
            //单参数构造，无验证模式，没有用户的认证,无需登录验证
            //三参数构造，有认证+指定地址。默认端口是61616.从ActiveMQ的conf/activemq.xml配置文件中查看
//            factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.42.234:61616");
//            主从模式使用如下，方式进行连接（错误自动转移）
            factory = new ActiveMQConnectionFactory("admin", "admin", "failover:(tcp://192.168.42.234:61616,tcp://192.168.42.252:61616,tcp://192.168.42.235:61616)?randomize=false");
            //通过工厂，创建连接对象
            //创建连接的方法有重载，其中有createConnection(String username,String password),可以再创建连接工厂时，只传递连接地址，不传递用户信息
            connection = factory.createConnection();
            //建议启动连接，消息的发送者不是必须启动连接。消息的消费者必须启动连接
            //producer在发送消息的时候，会检查是否启动了连接，如果未启动，自动启动
            //如果有特殊的配置，建议配置完毕后再启动连接
            connection.start();
            //通过连接对象，创建会话对象
            /*创建会话的时候，必须传递两个参数，分别代表的是否支持事务和如何确认消息处理
             * transacted:是否支持事务，数据类型是boolean.true-支持  false-不支持
             * 		true:支持事务，第二个参数对producer来说默认是无效（consumer没有事务）。建议传递的数据是Session.SESSION_TRANSACTED
             * 		false:不支持事务，常用参数。第二个参数必须传递，且必须有效
             *
             * acknowledgeMode:如何确认消息的处理。使用确认机制实现的
             * 		AUTO_ACKNOWLEDGE:自动确认消息。消息的消费者处理消息后，自动确认，常用。仅省事，商业开发不推荐
             * 		CLIENT_ACKNOWLEDGE:客户端手动确认。消息的消费者处理后，必须手工确认。若客户端不确认，则消息支持重复处理。
             * 		DUPS_OK_ACKNOWLEDGE:有副本的客户端手动确认，一个消息可以多次处理，可以降低Session的消耗，在可以容忍重复消息时使用（不推荐使用）。
             * 	                        DUPS_OK_ACKNOWLEDGE适用场景:邮件，发送后多个终端其中一个终端确认后，消息不再是最新消息。
             *通常在批量发送消息时采用事务，事务提供缓冲区，有更高的效率。
             */
            session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            //创建目的地。参数是目的地名称。是目的地的唯一标记
            destination = session.createQueue("first-mq");

            //通过会话对象，创建消息的发送者producer
            //创建的消息发送者，发送的消息一定到指定的目的地中
            //创建producer的时候，可以不提供目的地。在发送消息的时候指定目的地
//            producer = session.createProducer(null);
            producer = session.createProducer(destination);
            //创建文本消息对象，作为具体数据内容的载体
            message = session.createTextMessage(datas);
            //使用producer,发送消息到ActiveMQ中的目的地。如果消息发送失败。抛出异常
            producer.send(message);

            System.out.println("消息已经发送成功....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {//回收消息发送者
                try {
                    producer.close();
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
    }


    public static void main(String[] args) {
        TextProducer producer = new TextProducer();
        producer.sendTextMessage("我是一个测试的ActiveMQ...");
    }
}
