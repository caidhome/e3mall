package com.e3mall.search.activeMqSpring;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


public class ActiveMqSpring {

    @Test
    public void testSendMessage() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        Destination destination = (Destination) applicationContext.getBean("queueDestination");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("send Activemq message");
            }
        });
    }
}
