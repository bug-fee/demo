package com.wondersgroup.demo.activemq.first.listener;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProducerSend {
	
	public void sendMessage(Object obj){
		ConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		Message message = null;
		try{
			factory = new ActiveMQConnectionFactory("guest","guest","tcp://192.168.42.234:61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			destination = session.createQueue("test-listener");
			producer = session.createProducer(destination);
			connection.start();
			for(int i=0;i<100;i++){
				message = session.createObjectMessage("我是第"+i+"个消息，消息内容是:"+obj);
				producer.send(message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(producer != null){//回收消息生产者
				try {
					producer.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if(session != null){//回收会话对象
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if(connection != null){//回收连接对象
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
 
 
	public static void main(String[] args){
		ProducerSend producer = new ProducerSend();
		producer.sendMessage("this is message!!!");
				
	}
	
 
}