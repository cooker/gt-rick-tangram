package com.github.cooker.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * grant
 * 7/7/2020 2:53 下午
 * 描述：
 */
public class ActiveMQTest {

    @Test
    public void init() throws JMSException, InterruptedException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "failover://tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        TextMessage textMessage = session.createTextMessage("hello");
        textMessage.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, "30000");
        MessageProducer producer =session.createProducer(session.createQueue("qh"));

        producer.send(textMessage);
//        textMessage.acknowledge();
        Thread.sleep(3000);
    }
}
