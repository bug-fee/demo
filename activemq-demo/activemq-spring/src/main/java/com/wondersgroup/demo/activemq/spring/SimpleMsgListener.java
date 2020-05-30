package com.wondersgroup.demo.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

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
@Component(value = "simpleMsgListener")
public class SimpleMsgListener implements MessageListener {
    //收到信息时的动作
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("收到的信息：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}