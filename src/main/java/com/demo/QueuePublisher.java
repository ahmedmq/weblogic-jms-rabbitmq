package com.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class QueuePublisher {

    private QueueSender queueSender;
    private Queue queue;
    private TextMessage textMessage;

    public void init(Context ctx, String queueName)throws NamingException, JMSException
    {
        QueueSession queueSession = JmsUtil.getQueueSession(ctx);
        queue = (Queue) ctx.lookup(queueName);
        queueSender = queueSession.createSender(queue);
        textMessage = queueSession.createTextMessage();
    }

    public void send(String message) throws JMSException {
        textMessage.setText(message);
        queueSender.send(textMessage);
    }

    public void close() throws JMSException {
        if(queueSender !=null)
            queueSender.close();
        JmsUtil.cleanUp();
    }

}